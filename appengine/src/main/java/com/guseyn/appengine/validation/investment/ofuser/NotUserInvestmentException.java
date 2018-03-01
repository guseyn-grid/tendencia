package com.guseyn.appengine.validation.investment.ofuser;

import com.guseyn.appengine.domain.dto.ResponseError;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NotUserInvestmentException extends WebApplicationException {

    public NotUserInvestmentException(ResponseError responseError) {
        super(Response.status(Response.Status.NOT_ACCEPTABLE)
                .entity(responseError).type(MediaType.APPLICATION_JSON).build());
    }

}
