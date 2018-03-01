package com.guseyn.appengine.api;

import com.google.inject.Singleton;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.guseyn.appengine.Ofy.InvestmentOfy;
import com.guseyn.appengine.domain.GoogleUser;
import com.guseyn.appengine.domain.Investment;
import com.guseyn.appengine.service.UserService;
import com.guseyn.appengine.validation.investment.notfound.ExistingInvestment;
import com.guseyn.appengine.validation.investment.ofuser.InvestmentOfUser;
import com.guseyn.appengine.validation.user.notfound.SystemUser;
import com.guseyn.appengine.validation.user.unauth.AuthorizedUser;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/investment")
public class InvestmentAPI {

    @GET
    @Path( "/{id}/" )
    @Produces(MediaType.APPLICATION_JSON)
    @AuthorizedUser
    @ExistingInvestment
    @InvestmentOfUser
    public Response getInvestment(@PathParam("id") Long id, @Context HttpServletRequest request) {
        Investment fetchedInvestment = InvestmentOfy.fetchById(id);
        fetchedInvestment.setCurrency(fetchedInvestment.getCurrencyRef().get());
        return Response.status(Response.Status.OK).entity(fetchedInvestment).build();
    }

    @PUT
    @Path( "/" )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @AuthorizedUser
    @SystemUser
    public Response addInvestment(Investment investment, @Context HttpServletRequest request) {
        Long userId = UserService.getAuthId(request);
        investment.setGoogleUserRef(Ref.create(Key.create(GoogleUser.class, userId)));
        InvestmentOfy.setSavedCurrency(investment);
        Key investmentKey = InvestmentOfy.save(investment);
        investment.setId(investmentKey.getId());
        return Response.status(Response.Status.OK).entity(investment).build();
    }

    @POST
    @Path( "/" )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @AuthorizedUser
    @SystemUser
    @ExistingInvestment
    @InvestmentOfUser
    public Response updateInvestment(Investment investment, @Context HttpServletRequest request) {
        Long userId = UserService.getAuthId(request);
        investment.setGoogleUserRef(Ref.create(Key.create(GoogleUser.class, userId)));
        Investment fetchedInvestment = InvestmentOfy.fetchById(investment.getId());
        investment.setId(fetchedInvestment.getId());
        InvestmentOfy.setSavedCurrency(investment);
        InvestmentOfy.save(investment);
        return Response.status(Response.Status.OK).entity(investment).build();
    }



}
