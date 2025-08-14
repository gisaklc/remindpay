package com.remindpay.scheduler;

import com.remindpay.dto.PhoneUtils;
import com.remindpay.dto.SmsResponse;
import com.remindpay.model.Account;
import com.remindpay.repository.AccountRepository;
import com.remindpay.service.SmsService;
import io.quarkus.scheduler.Scheduled;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class SmsReminderJob {
    private static final Logger log = LoggerFactory.getLogger(SmsReminderJob.class);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Inject
    SmsService smsService;

    @Inject
    AccountRepository accountRepository;

    @Scheduled(cron = "${reminder.cron}")
    public void sendDailyReminders() {

        List<Account> userAccounts = accountRepository.findAll().list();
        LocalDate today = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        for (Account userAccount : userAccounts) {
            Message message = buildReminderMessageWithAlert(userAccount, today);
            if (message != null) {
                 smsService.send(message);
            }
        }
    }


    private Message buildReminderMessageWithAlert(Account account, LocalDate today) {

        LocalDate dueDate = LocalDate.of(today.getYear(), today.getMonth(), account.getDueDay());
        long daysUntilDue = ChronoUnit.DAYS.between(today, dueDate);

        String alertMessage = getAlertMessage(daysUntilDue, dueDate);
        if (alertMessage == null) return null;

        String userName = account.getUser().getName();
        String accountName = account.getName() != null ? account.getName() : "uma conta não especificada";
        String messageText = String.format("Olá, %s! A conta \"%s\" %s.", userName, accountName, alertMessage);


        return new Message() {{
            setDueDay(account.getDueDay());
            setTo(PhoneUtils.format(account.getUser().getPhoneNumber()));
            setMessage(messageText);
        }};
    }


    private String getAlertMessage(long daysUntilDue, LocalDate dueDate) {
        String formattedDate = dueDate.format(SmsReminderJob.DATE_FORMATTER);
        return switch ((int) daysUntilDue) {
            case 1 -> "vence em " + formattedDate;
            case 0 -> "vence hoje " + formattedDate;
            case -1 -> "venceu em " + formattedDate;
            default -> null;
        };
    }

}
