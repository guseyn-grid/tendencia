package com.guseyn.appengine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.IgnoreSave;

@Entity
public class Investment {

    @Id
    private Long id;
    @JsonIgnore
    private Ref<GoogleUser> googleUserRef;
    @JsonIgnore
    private Ref<Currency> currencyRef;
    private String title;
    private Double amount;

    @IgnoreSave
    @JsonIgnore
    private GoogleUser googleUser;
    @IgnoreSave
    private Currency currency;

    public Investment() {
    }

    public Investment(Long id) {
        this.id = id;
    }

    public Investment(Currency currency, String title, Double amount) {
        this.currency = currency;
        this.title = title;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ref<GoogleUser> getGoogleUserRef() {
        return googleUserRef;
    }

    public void setGoogleUserRef(Ref<GoogleUser> googleUserRef) {
        this.googleUserRef = googleUserRef;
    }

    public Ref<Currency> getCurrencyRef() {
        return currencyRef;
    }

    public void setCurrencyRef(Ref<Currency> currencyRef) {
        this.currencyRef = currencyRef;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public GoogleUser getGoogleUser() {
        return googleUser;
    }

    public void setGoogleUser(GoogleUser googleUser) {
        this.googleUser = googleUser;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
