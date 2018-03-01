package com.guseyn.appengine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;

import java.util.Date;

@Entity
public class History {

    @Id
    private Long id;
    @JsonIgnore
    @Index
    private Ref<Investment> investmentRef;
    @Index
    private Date logDate;
    private Double curAmount;
    private Double curExchange;

    @IgnoreSave
    private Long investmentId;
    @IgnoreSave
    private Long currencyId;

    public History() {

    }

    public History(Double curAmount, Double curExchange, Long investmentId, Date logDate) {
        this.curAmount = curAmount;
        this.curExchange = curExchange;
        this.investmentId = investmentId;
        this.logDate = logDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ref<Investment> getInvestmentRef() {
        return investmentRef;
    }

    public void setInvestmentRef(Ref<Investment> investmentRef) {
        this.investmentRef = investmentRef;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public Double getCurAmount() {
        return curAmount;
    }

    public void setCurAmount(Double curAmount) {
        this.curAmount = curAmount;
    }

    public Double getCurExchange() {
        return curExchange;
    }

    public void setCurExchange(Double curExchange) {
        this.curExchange = curExchange;
    }

    public Long getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(Long investmentId) {
        this.investmentId = investmentId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }
}
