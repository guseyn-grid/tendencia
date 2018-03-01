package com.guseyn.appengine.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.io.Serializable;

@Entity
public class Currency implements Serializable {

    @Id
    private Long id;
    @Index
    private String title;
    private String abbreviation;
    private Double exchange;

    public Currency() {
    }

    public Currency(String title, String abbreviation, Double exchange) {
        this.title = title;
        this.abbreviation = abbreviation;
        this.exchange = exchange;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Double getExchange() {
        return exchange;
    }

    public void setExchange(Double exchange) {
        this.exchange = exchange;
    }
}
