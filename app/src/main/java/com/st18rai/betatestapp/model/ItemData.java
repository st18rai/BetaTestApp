package com.st18rai.betatestapp.model;

import com.google.gson.annotations.SerializedName;

public class ItemData {

    @SerializedName("Id")
    private int id;

    @SerializedName("ActualTime")
    private long actualTime;

    @SerializedName("Comment")
    private String comment;

    @SerializedName("Pair")
    private String pair;

    @SerializedName("Cmd")
    private int cmd;

    @SerializedName("TradingSystem")
    private int tradingSystem;

    @SerializedName("Period")
    private String period;

    @SerializedName("Price")
    private float price;

    @SerializedName("Sl")
    private float sl;

    @SerializedName("Tp")
    private float tp;


    public int getId() {
        return id;
    }

    public long getActualTime() {
        return actualTime;
    }

    public String getComment() {
        return comment;
    }

    public String getPair() {
        return pair;
    }

    public int getCmd() {
        return cmd;
    }

    public int getTradingSystem() {
        return tradingSystem;
    }

    public String getPeriod() {
        return period;
    }

    public float getPrice() {
        return price;
    }

    public float getSl() {
        return sl;
    }

    public float getTp() {
        return tp;
    }
}
