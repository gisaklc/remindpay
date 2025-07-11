package com.remindpay.scheduler;

import com.remindpay.dto.PhoneUtils;
import com.remindpay.model.UserAccount;
import com.remindpay.repository.UserAccountRepository;
import com.remindpay.service.SmsService;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@ApplicationScoped
public class SmsReminderJob {

    @Inject
    SmsService smsService;

    @Inject
    UserAccountRepository userAccountRepository;

    @Scheduled(cron = "0 0 8 * * ?") // todos os dias às 08:00
    public void sendDailyReminders() {
        LocalDate today = LocalDate.now();

        List<UserAccount> userAccounts = userAccountRepository.findAll().list();

        for (UserAccount userAccount : userAccounts) {
            LocalDate dueDate = getDueDateForCurrentMonth(today, userAccount.getDueDay());
            if (dueDate == null) continue;

            String alertMessage = getDueDateAlertMessage(today, dueDate);
            if (alertMessage == null) continue;

            Message message = buildReminderMessage(userAccount, alertMessage);

            smsService.send(message);
        }

    }
    private LocalDate getDueDateForCurrentMonth(LocalDate today, int dueDay) {
        try {
            return LocalDate.of(today.getYear(), today.getMonth(), dueDay);
        } catch (DateTimeException e) {
            return null; // data inválida (ex: 31/02)
        }
    }

    private String getDueDateAlertMessage(LocalDate today, LocalDate dueDate) {
        long daysUntilDue = ChronoUnit.DAYS.between(today, dueDate);//ver quantos dias faltam para o vencimento
        String formattedDate = dueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        if (daysUntilDue == 1) {
            return "vence em " + formattedDate + " (faltam 1 dia)";
        } else if (daysUntilDue == 0) {
            return "vence hoje (" + formattedDate + ")";
        } else if (daysUntilDue == -1) {
            return "venceu em " + formattedDate + " (há 1 dia)";
        }
        return null;
    }

    private Message buildReminderMessage(UserAccount agenda, String alertMessage) {
        String accountName = (agenda.getAccount() != null)
                ? agenda.getAccount().getName()
                : "uma conta não especificada";

        Message message = new Message();
        message.setEmailNotify(agenda.getEmailNotify());
        message.setDueDay(agenda.getDueDay());
        message.setTo(PhoneUtils.format(agenda.getUser().getPhoneNumber())); // número de telefone para SMS

        message.setMessage("Ola, " + agenda.getUser().getName() + "!  A conta \"" + accountName + "\" " + alertMessage + ".");

        return message;
    }

}