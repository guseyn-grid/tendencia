package com.guseyn.appengine.validation.user.unauth;

import com.guseyn.appengine.domain.dto.ResponseError;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NotAuthorizedUserException extends WebApplicationException {

    public NotAuthorizedUserException(ResponseError responseError) {
        super(Response.status(Response.Status.UNAUTHORIZED)
                .entity(responseError).type(MediaType.APPLICATION_JSON).build());
    }

}
