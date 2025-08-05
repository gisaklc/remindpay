package com.remindpay.controller;

import com.remindpay.dto.CategoryRequestDto;
import com.remindpay.dto.CategoryResponseDto;
import com.remindpay.exceptions.BusinessException;
import com.remindpay.mapper.AccountMapper;
import com.remindpay.mapper.CategoryMapper;
import com.remindpay.model.Category;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.util.List;

@SecurityRequirement(name = "jwt")
@Path("/api/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    CategoryMapper categoryMapper;

    @POST
    @Transactional
    @RolesAllowed({"ADMIN, USER"})
    public Response createCategory(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());

        // Regra de negócio simples: nome único
        if (Category.find("name", dto.getName()).firstResult() != null) {
            throw new BusinessException("Categoria já cadastrada.", 422);
        }

        category.persist();
        return Response.status(Response.Status.CREATED).entity(category).build();
    }

    @GET
    public Response listAll() {
       return Response.ok(categoryMapper.toDtoList(Category.listAll())).build();
    }

}
