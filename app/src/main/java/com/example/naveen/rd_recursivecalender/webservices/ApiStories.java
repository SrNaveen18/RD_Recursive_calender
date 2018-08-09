package com.example.naveen.rd_recursivecalender.webservices;


import com.example.naveen.rd_recursivecalender.model.DateList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiStories {
    @GET("datelist.php")
    Call<DateList> getDateList();

}
