package com.guseyn.appengine.api;

import com.google.inject.Singleton;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.guseyn.appengine.Ofy.CurrencyOfy;
import com.guseyn.appengine.Ofy.HistoryOfy;
import com.guseyn.appengine.Ofy.InvestmentOfy;
import com.guseyn.appengine.domain.Currency;
import com.guseyn.appengine.domain.History;
import com.guseyn.appengine.domain.Investment;
import com.guseyn.appengine.validation.investment.notfound.ExistingInvestment;
import com.guseyn.appengine.validation.investment.ofuser.InvestmentOfUser;
import com.guseyn.appengine.validation.user.notfound.SystemUser;
import com.guseyn.appengine.validation.user.unauth.AuthorizedUser;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Singleton
@Path("/history")
public class HistoryAPI {

    @GET
    @Path( "/{id}/" )
    @Produces(MediaType.APPLICATION_JSON)
    @AuthorizedUser
    @SystemUser
    public Response getHistory(@PathParam("id") Long id, @Context HttpServletRequest request) {
        History history = HistoryOfy.fetchById(id);
        Investment investment = history.getInvestmentRef().get();
        history.setInvestmentId(investment.getId());
        history.setCurrencyId(investment.getCurrencyRef().get().getId());
        history.setInvestmentId(history.getInvestmentRef().get().getId());
        return Response.status(Response.Status.OK).entity(history).build();
    }

    @GET
    @Path( "/all/{id}" )
    @Produces(MediaType.APPLICATION_JSON)
    @AuthorizedUser
    @SystemUser
    @ExistingInvestment
    @InvestmentOfUser
    public Response getAllHistoryOfInvestment(
            @PathParam("id") Long id,
           // @PathParam("startDate") Date startDate,
           // @PathParam("endDate") Date endDate,
            @Context HttpServletRequest request
    ) {
        //List<History> history = HistoryOfy.fetchByInvestmentId(id, startDate, endDate);
        List<History> history = HistoryOfy.fetchByInvestmentId(id, null, null);
        for (History h: history) {
            Investment investment = h.getInvestmentRef().get();
            h.setInvestmentId(investment.getId());
            h.setCurrencyId(investment.getCurrencyRef().get().getId());
            h.setInvestmentId(h.getInvestmentRef().get().getId());
        }
        return Response.status(Response.Status.OK).entity(history).build();
    }

    @PUT
    @Path( "/" )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @AuthorizedUser
    @SystemUser
    @ExistingInvestment
    @InvestmentOfUser
    public Response log(History history, @Context HttpServletRequest request) {
        if (history.getLogDate() == null) {
            history.setLogDate(new Date());
        }
        Investment investment = InvestmentOfy.fetchById(history.getInvestmentId());
        Currency currency = CurrencyOfy.fetchById(history.getCurrencyId());
        history.setCurExchange(currency.getExchange());
        history.setCurAmount(investment.getAmount());
        history.setInvestmentRef(Ref.create(Key.create(Investment.class, investment.getId())));
        HistoryOfy.save(history);
        return Response.status(Response.Status.OK).entity(history).build();
    }

}
