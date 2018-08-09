package com.example.naveen.rd_recursivecalender.model;


import com.google.gson.annotations.SerializedName;

public class DateList {

    @SerializedName("startDate")
    public String startDate;
    @SerializedName("endDate")
    public String endDate;
    @SerializedName("pausedDates")
    public PausedDates pausedDates;

    public static class PausedDates {
        @SerializedName("dates")
        public String dates;
    }
}
