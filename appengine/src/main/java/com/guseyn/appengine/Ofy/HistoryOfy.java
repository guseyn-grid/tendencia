package com.guseyn.appengine.Ofy;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.cmd.Query;
import com.guseyn.appengine.domain.History;
import com.guseyn.appengine.domain.Investment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.guseyn.appengine.Ofy.service.OfyService.ofy;

public class HistoryOfy {

    public static History fetchById(Long id) {
        return ofy().load().key(Key.create(History.class, id)).now();
    }

    public static List<History> fetchByInvestmentId(Long id, Date startDate, Date endDate) {
        Query<History> query = ofy().load().type(History.class).filter("investmentRef", Ref.create(Key.create(Investment.class, id)));
        if (startDate != null) {
            if (endDate != null) {
                return query.filter("logDate >= ", startDate).filter("logDate <= ", endDate).list();
            }
            return query.filter("logDate >=", startDate).list();
        }
        return query.list();
    }

    public static List<History> load(Date startDate, Date endDate, Long userId) {
        return new ArrayList<>();
    }

    public static Key<History> save(History history) {
        return ofy().save().entity(history).now();
    }

}
