package com.guseyn.appengine.validation.investment.notfound;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class ExistingInvestmentModule extends AbstractModule {

    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(ExistingInvestment.class),
                new ExistingInvestmentInspector());
    }

}
