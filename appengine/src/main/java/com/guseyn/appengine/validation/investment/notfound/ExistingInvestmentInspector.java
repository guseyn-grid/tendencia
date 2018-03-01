package com.guseyn.appengine.validation.investment.notfound;

import com.guseyn.appengine.domain.Investment;
import com.guseyn.appengine.domain.dto.ResponseError;
import com.guseyn.appengine.service.InvestmentService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ExistingInvestmentInspector implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object investmentIdentity = invocation.getArguments()[0];
        Investment fetchedInvestment = InvestmentService.getByIdentity(investmentIdentity);
        if (fetchedInvestment == null) {
            throw new NotExistingInvestmentException(new ResponseError("Investment is not found"));
        }
        return invocation.proceed();
    }

}
