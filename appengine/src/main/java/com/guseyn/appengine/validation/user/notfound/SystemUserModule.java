package com.guseyn.appengine.validation.user.notfound;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class SystemUserModule extends AbstractModule {

    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(SystemUser.class),
                new SystemUserInspector());
    }

}
