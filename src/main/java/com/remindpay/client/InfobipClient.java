package com.remindpay.client;

import com.remindpay.dto.SmsRequest;
import com.remindpay.dto.SmsResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/sms/2/text")
@RegisterRestClient(configKey = "infobip-api")
@ClientHeaderParam(name = "Authorization", value = "App 5ea0c3e82ffb459753754aa9a40a266b-439b6965-f19a-48b0-beec-5a82f5037a67")
public interface InfobipClient {

    @POST
    @Path("/advanced")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    SmsResponse sendSms(SmsRequest smsRequest);

}
