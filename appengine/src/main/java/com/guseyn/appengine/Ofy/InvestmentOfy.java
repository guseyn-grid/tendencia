package com.guseyn.appengine.Ofy;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.guseyn.appengine.domain.Currency;
import com.guseyn.appengine.domain.Investment;

import static com.guseyn.appengine.Ofy.service.OfyService.ofy;

public class InvestmentOfy {

    public static Investment fetchById(Long id) {
        return ofy().load().key(Key.create(Investment.class, id)).now();
    }

    public static Key<Investment> save(Investment investment) {
        return ofy().save().entity(investment).now();
    }

    public static void setSavedCurrency(Investment investment) {
        Currency fetchedCurrency = CurrencyOfy.fetchByTitle(investment.getCurrency().getTitle());
        Key<Currency> currencyKey;
        if (fetchedCurrency == null) {
            currencyKey = ofy().save().entity(investment.getCurrency()).now();
        } else {
            currencyKey = Key.create(Currency.class, fetchedCurrency.getId());
        }
        investment.getCurrency().setId(currencyKey.getId());
        investment.setCurrencyRef(Ref.create(currencyKey));
    }

}
