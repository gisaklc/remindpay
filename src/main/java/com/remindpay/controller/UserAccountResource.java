package com.remindpay.controller;

import com.remindpay.dto.AccountResponseDto;
import com.remindpay.dto.UserAccountRequestDTO;
import com.remindpay.mapper.UserAccountMapper;
import com.remindpay.service.UserAccountService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "jwt")
@Path("/api/user-accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserAccountResource {

    @Inject
    UserAccountService userAccountService;

    @Inject
    UserAccountMapper mapper;

    @POST
    @RolesAllowed({"USER", "ADMIN"})
    public Response create(@Valid UserAccountRequestDTO dto) {

        var userAccount = mapper.toEntity(dto);
        userAccountService.create(userAccount);

        return Response.status(Response.Status.CREATED).build();
    }

}
