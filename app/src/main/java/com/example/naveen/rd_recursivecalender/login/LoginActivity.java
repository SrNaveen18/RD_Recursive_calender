package com.example.naveen.rd_recursivecalender.login;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.naveen.rd_recursivecalender.R;

public class LoginActivity extends Activity implements LoginView{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
