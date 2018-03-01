package com.guseyn.appengine.validation.investment.ofuser;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class InvestmentOfUserModule extends AbstractModule {

    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(InvestmentOfUser.class),
                new InvestmentOfUserInspector());
    }

}
