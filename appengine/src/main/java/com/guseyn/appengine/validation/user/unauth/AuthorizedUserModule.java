package com.guseyn.appengine.validation.user.unauth;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class AuthorizedUserModule extends AbstractModule {

    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(AuthorizedUser.class),
                new AuthorizedUserInspector());
    }

}
