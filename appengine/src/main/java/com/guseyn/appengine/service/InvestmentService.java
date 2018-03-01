package com.guseyn.appengine.service;

import com.guseyn.appengine.Ofy.InvestmentOfy;
import com.guseyn.appengine.domain.History;
import com.guseyn.appengine.domain.Investment;

public class InvestmentService {

    public static Investment getByIdentity(Object investmentIdentity) {
        Investment fetchedInvestment = null;

        if (investmentIdentity instanceof Long) {
            fetchedInvestment = InvestmentOfy.fetchById((Long) investmentIdentity);
        } else if (investmentIdentity instanceof Investment) {
            fetchedInvestment = InvestmentOfy.fetchById(((Investment) investmentIdentity).getId());
        } else if (investmentIdentity instanceof History) {
            fetchedInvestment = InvestmentOfy.fetchById(((History) investmentIdentity).getInvestmentId());
        }

        return fetchedInvestment;
    }

}
