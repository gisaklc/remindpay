package com.remindpay.service;


import com.remindpay.scheduler.Message;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class MailService implements NotificationService {
    @Inject
    Mailer mailer;

    @Override
    public void send(Message message) {
        String destinatario = message.getEmailNotify();
        String assunto = "Lembrete de Conta";  // assunto não pode ser vazio
        String corpo = message.getMessage();   // corpo com texto que você já monta

        mailer.send(Mail.withText("gisele.screen@gmail.com", assunto, corpo));
    }


}
