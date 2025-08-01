package com.remindpay.service;

import com.remindpay.client.InfobipClient;
import com.remindpay.dto.SmsRequest;
import com.remindpay.dto.SmsResponse;
import com.remindpay.utils.JsonUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class SmsService  implements NotificationService {

    private static final Logger log = LoggerFactory.getLogger(SmsService.class);

    @Inject
    @RestClient
    InfobipClient infobipClient;

    public SmsService() {}

    @Override
    public SmsResponse send(com.remindpay.scheduler.Message m) {
        SmsRequest request = new SmsRequest();

        SmsRequest.Message message = new SmsRequest.Message();
        SmsRequest.Destination destination = new SmsRequest.Destination();
        destination.setTo(m.getTo());

        message.setDestinations(List.of(destination));
        message.setFrom("29175"); // seu remetente Infobip
        message.setText(m.getMessage());

        request.setMessages(List.of(message));
        String json1 = JsonUtil.toJson(request, "");
        log.info(json1);
        SmsResponse response = null; // declarar aqui fora
        try {
            response = infobipClient.sendSms(request);

            String json = JsonUtil.toJson(response, "");
            log.info("SMS Enviado com sucesso!");
            log.info("Resposta Infobip: " + json);
        } catch (Exception e) {
            log.error("Erro ao enviar SMS para Infobip: " + e.getMessage());
            throw e;
        }
        return response;
    }
}

