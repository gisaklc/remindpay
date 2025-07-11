package com.remindpay.exceptions;

import com.remindpay.dto.Problem;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.jboss.logging.Logger;

import java.time.OffsetDateTime;

public class GenericExceptionMapper  implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = Logger.getLogger(GenericExceptionMapper.class);

    @Override
    public Response toResponse(Exception exception) {

        LOGGER.error("Erro inesperado: ", exception);

        Problem problem = new Problem();
        problem.status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        problem.timestamp = OffsetDateTime.now();
        problem.title = "Internal Server Error";
        problem.detail = exception.getMessage() != null ? exception.getMessage() : "Erro inesperado no servidor.";

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(problem)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
