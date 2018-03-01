package com.guseyn.appengine.service;

import com.guseyn.appengine.Ofy.UserOfy;
import com.guseyn.appengine.domain.GoogleUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserService {

    public static Long getAuthId(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        return  (Long) httpSession.getAttribute("userId");
    }

    public static GoogleUser getAuthorizedUser(HttpServletRequest request) {
        return UserOfy.fetchById(getAuthId(request));
    }

}
