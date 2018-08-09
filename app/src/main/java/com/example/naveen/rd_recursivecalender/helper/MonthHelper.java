package com.example.naveen.rd_recursivecalender.helper;


import java.util.HashMap;

public class MonthHelper {

    public static String getMonth(String month) {
        HashMap<String, String> Month = new HashMap<>();
        Month.put("1", "JAN");
        Month.put("2", "FEB");
        Month.put("3", "MAR");
        Month.put("4", "APR");
        Month.put("5", "MAY");
        Month.put("6", "JUN");
        Month.put("7", "JUL");
        Month.put("8", "AUG");
        Month.put("9", "SEP");
        Month.put("10", "OCT");
        Month.put("11", "NOV");
        Month.put("12", "DEC");
        return Month.get(month);
    }
}
