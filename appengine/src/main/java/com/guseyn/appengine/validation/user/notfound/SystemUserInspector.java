package com.guseyn.appengine.validation.user.notfound;

import com.guseyn.appengine.domain.GoogleUser;
import com.guseyn.appengine.domain.dto.ResponseError;
import com.guseyn.appengine.service.UserService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.servlet.http.HttpServletRequest;

public class SystemUserInspector implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        GoogleUser googleUser = UserService.getAuthorizedUser((HttpServletRequest) invocation.getArguments()[1]);
        if (googleUser == null) {
            throw new NotSystemUserException(new ResponseError("User is not found"));
        }
        return invocation.proceed();
    }

}
