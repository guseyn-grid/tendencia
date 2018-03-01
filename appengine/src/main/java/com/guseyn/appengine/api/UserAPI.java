package com.guseyn.appengine.api;

import com.google.inject.Singleton;
import com.guseyn.appengine.Ofy.UserOfy;
import com.guseyn.appengine.domain.GoogleUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Singleton
@Path("/user")
public class UserAPI {

    @POST
    @Path( "/test/login" )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(GoogleUser googleUser, @Context HttpServletRequest request) {
        GoogleUser fetchedUser = UserOfy.fetchByEmail(googleUser.getEmail());
        HttpSession session = request.getSession();
        googleUser = UserOfy.identify(fetchedUser, googleUser);
        session.setAttribute("userId", googleUser.getId());
        return Response.status(Response.Status.OK).entity(googleUser).build();
    }

}
