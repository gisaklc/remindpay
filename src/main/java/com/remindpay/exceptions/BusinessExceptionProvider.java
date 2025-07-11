package com.remindpay.exceptions;

import com.remindpay.dto.Problem;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionProvider implements ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(BusinessException e) {
        Problem problem = new Problem(e);
        return Response.status(e.getStatus()).entity(problem).type(MediaType.APPLICATION_JSON).build();
    }

}
