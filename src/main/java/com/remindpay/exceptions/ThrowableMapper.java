package com.remindpay.exceptions;

import com.remindpay.dto.Problem;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.OffsetDateTime;

@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        Problem problem = new Problem();
        problem.status = 500;
        problem.timestamp = OffsetDateTime.now();
        problem.title = "Erro inesperado";
        problem.detail = exception.getMessage();

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(problem)
                .type("application/problem+json")
                .build();
    }

}