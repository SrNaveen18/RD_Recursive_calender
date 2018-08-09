package com.example.naveen.rd_recursivecalender;


import com.example.naveen.rd_recursivecalender.model.DateList;
import com.example.naveen.rd_recursivecalender.webservices.ApiClient;
import com.example.naveen.rd_recursivecalender.webservices.ApiStories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


class MainPresenter {

    private  MainView mainView;

   MainPresenter(MainView mainView){
        this.mainView = mainView;
    }


    void getDateList(){
        ApiStories ApiStories = ApiClient.getRetrofit().create(ApiStories.class);
        Call<DateList> apiClientCall = ApiStories.getDateList();

        apiClientCall.enqueue(new Callback<DateList>() {
            @Override
            public void onResponse(Call<DateList> call, Response<DateList> response) {
                DateList dateList = response.body();
                mainView.displaySubcription(dateList);
            }

            @Override
            public void onFailure(Call<DateList> call, Throwable t) {

            }
        });
    }
}
