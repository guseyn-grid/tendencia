package com.guseyn.appengine;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.guseyn.appengine.api.HistoryAPI;
import com.guseyn.appengine.api.InvestmentAPI;
import com.guseyn.appengine.api.UserAPI;
import com.guseyn.appengine.validation.investment.notfound.ExistingInvestmentModule;
import com.guseyn.appengine.validation.investment.ofuser.InvestmentOfUserModule;
import com.guseyn.appengine.validation.user.notfound.SystemUserModule;
import com.guseyn.appengine.validation.user.unauth.AuthorizedUserModule;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

public class AppInitilizer extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new JerseyServletModule() {
                    @Override
                    protected void configureServlets() {
                        serve("/*").with(GuiceContainer.class);
                        bind(UserAPI.class);
                        bind(InvestmentAPI.class);
                        bind(HistoryAPI.class);

                        bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
                        bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);
                    }
                },
                new AuthorizedUserModule(),
                new SystemUserModule(),
                new ExistingInvestmentModule(),
                new InvestmentOfUserModule()
        );
    }

}
