package com.remindpay.controller;

import com.remindpay.scheduler.SmsReminderJob;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/manual-job")
@ApplicationScoped
public class ManualEmailJobEndpoint {

    @Inject
    SmsReminderJob job;

    @GET
    @RolesAllowed("ADMIN")
    public Response sendReminderNow() {
        job.sendDailyReminders(); // executa com injeção real
        return Response.ok("Executado").build();
    }
}