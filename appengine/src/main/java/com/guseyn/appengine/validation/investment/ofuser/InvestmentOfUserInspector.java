package com.guseyn.appengine.validation.investment.ofuser;

import com.guseyn.appengine.Ofy.UserOfy;
import com.guseyn.appengine.domain.Investment;
import com.guseyn.appengine.domain.dto.ResponseError;
import com.guseyn.appengine.service.InvestmentService;
import com.guseyn.appengine.service.UserService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.servlet.http.HttpServletRequest;

public class InvestmentOfUserInspector implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object investmentIdentity = invocation.getArguments()[0];
        HttpServletRequest request = (HttpServletRequest) invocation.getArguments()[1];
        Investment fetchedInvestment = InvestmentService.getByIdentity(investmentIdentity);
        if (!UserOfy.fetchByInvestment(fetchedInvestment).getId().equals(UserService.getAuthorizedUser(request).getId())) {
            throw new NotUserInvestmentException(new ResponseError("Investment does not belong to the user"));
        }

        return invocation.proceed();
    }

}
