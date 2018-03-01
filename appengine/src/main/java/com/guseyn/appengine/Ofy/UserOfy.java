package com.guseyn.appengine.Ofy;

import com.googlecode.objectify.Key;
import com.guseyn.appengine.domain.GoogleUser;
import com.guseyn.appengine.domain.Investment;

import static com.guseyn.appengine.Ofy.service.OfyService.ofy;

public class UserOfy {

    public static GoogleUser fetchById(Long id) {
        return ofy().load().key(Key.create(GoogleUser.class, id)).now();
    }

    public static GoogleUser fetchByEmail(String email) {
        return ofy().load().type(GoogleUser.class).filter("email = ", email).first().now();
    }

    public static GoogleUser fetchByInvestment(Investment investment) {
        return investment.getGoogleUserRef().get();
    }

    public static GoogleUser identify(GoogleUser fetchedUser, GoogleUser googleUser) {
        if (fetchedUser == null) {
            Key key = ofy().save().entity(googleUser).now();
            googleUser.setId(key.getId());
        } else {
            googleUser.setId(fetchedUser.getId());
        }
        return googleUser;
    }

}
