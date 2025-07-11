package com.remindpay.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vonage.client.VonageClient;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.messages.TextMessage;

@ApplicationScoped
public class SmsService  implements NotificationService {

    private static final Logger log = LoggerFactory.getLogger(SmsService.class);

    @ConfigProperty(name = "vonage.api.key")
    String apiKey;

    @ConfigProperty(name = "vonage.api.secret")
    String apiSecret;

    @ConfigProperty(name = "vonage.from.name")
    String fromName; // pode ser número ou nome curto (ex: "RemindPay")

    public SmsService() {}

    public void send(com.remindpay.scheduler.Message m) {

        try {

            VonageClient client = VonageClient.builder()
                    .apiKey(apiKey)
                    .apiSecret(apiSecret)
                    .build();

            TextMessage message = new TextMessage(
                    fromName,
                    m.getTo(),
                    m.getMessage());
            log.info("Mensagem {}", m.getMessage());

            SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

            var msgResp = response.getMessages().get(0);
            if (msgResp.getStatus() == MessageStatus.OK) {
                log.info("SMS enviado com sucesso para {}", m.getTo());
            } else {
                log.error("Falha ao enviar SMS para {}: {}", m.getTo(), msgResp.getErrorText());
            }


        } catch (Exception e) {
            log.error("Erro ao enviar SMS: {}", e.getMessage(), e);
            throw new RuntimeException("Erro ao enviar SMS via Vonage", e);
        }

    }
}
