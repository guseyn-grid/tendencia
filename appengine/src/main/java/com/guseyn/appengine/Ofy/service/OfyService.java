package com.guseyn.appengine.Ofy.service;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.guseyn.appengine.domain.Currency;
import com.guseyn.appengine.domain.GoogleUser;
import com.guseyn.appengine.domain.History;
import com.guseyn.appengine.domain.Investment;

public class OfyService {

    static {
        ObjectifyService.register(GoogleUser.class);
        ObjectifyService.register(Investment.class);
        ObjectifyService.register(Currency.class);
        ObjectifyService.register(History.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }

}