package com.guseyn.appengine.Ofy;

import com.googlecode.objectify.Key;
import com.guseyn.appengine.domain.Currency;

import static com.guseyn.appengine.Ofy.service.OfyService.ofy;

public class CurrencyOfy {

    public static Currency fetchById(Long id) {
        return ofy().load().key(Key.create(Currency.class, id)).now();
    }

    public static Currency fetchByTitle(String title) {
        return ofy().load().type(Currency.class).filter("title = ", title).first().now();
    }

}
