package com.guseyn.appengine.validation.user.unauth;

import com.guseyn.appengine.domain.dto.ResponseError;
import com.guseyn.appengine.service.UserService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.servlet.http.HttpServletRequest;

public class AuthorizedUserInspector implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Long sessionUserId = UserService.getAuthId((HttpServletRequest) invocation.getArguments()[1]);
        if (sessionUserId == null) {
            throw new NotAuthorizedUserException(new ResponseError("User is not authorized"));
        }
        return invocation.proceed();
    }

}
