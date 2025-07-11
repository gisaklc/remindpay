package com.remindpay.dto;

import com.remindpay.exceptions.BusinessException;
import jakarta.validation.ConstraintViolationException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Problem {

    public int status;
    public OffsetDateTime timestamp;
    public String title;
    public String detail;
    public List<ProblemObject> messages;

    public Problem() {
    }

    //para regras de negocio
    public Problem(BusinessException e){
        this.status = 422;
        this.timestamp = OffsetDateTime.now();
        this.title = "Business";
        this.detail = e.getLocalizedMessage();
    }

    // bean validation
    public Problem(ConstraintViolationException e){
        this.status = 400;
        this.timestamp = OffsetDateTime.now();
        this.title = "Invalid data";
        this.detail  = "Dados inválidos";
        this.messages = new ArrayList<>();
    }
}
