package com.practice.my_jupiter.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoryRequestBody {

    @JsonProperty("user_id")
    public String userId;

    public Order order;
    // public Package favorite;

}