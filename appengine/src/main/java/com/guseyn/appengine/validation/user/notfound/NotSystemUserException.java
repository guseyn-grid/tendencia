package com.guseyn.appengine.validation.user.notfound;

import com.guseyn.appengine.domain.dto.ResponseError;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NotSystemUserException extends WebApplicationException {

    public NotSystemUserException(ResponseError responseError) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(responseError).type(MediaType.APPLICATION_JSON).build());
    }

}
