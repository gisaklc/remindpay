package com.remindpay.scheduler;

import com.remindpay.model.Account;
import com.remindpay.model.User;
import com.remindpay.model.UserAccount;
import com.remindpay.repository.UserAccountRepository;
import com.remindpay.service.SmsService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@QuarkusTest
class SmsReminderJobTest {
    @Inject
    SmsReminderJob reminderService; // sua classe com @Scheduled
    @InjectMock
    UserAccountRepository userAccountRepository;
    @InjectMock
    SmsService smsService;

    //@Test
    public void testSendDailyReminders() {
        // dado um usuário com vencimento hoje
        UserAccount account = new UserAccount();
        account.setDueDay(LocalDate.now().getDayOfMonth());
        account.setEmailNotify("usuario@exemplo.com");


        User user = new User();
        user.setName("João");
        user.setPhoneNumber("21978049990");
        account.setUser(user);

        Account acc = new Account();
        acc.setName("Conta de Luz");
        account.setAccount(acc);

        PanacheQuery<UserAccount> query = mock(PanacheQuery.class);

        when(query.list()).thenReturn(List.of(account));
        when(userAccountRepository.findAll()).thenReturn(query);

        // executa manualmente o método agendado
        reminderService.sendDailyReminders();

        // verifica se o email foi enviado
        verify(smsService, times(1)).send(any());
    }

}