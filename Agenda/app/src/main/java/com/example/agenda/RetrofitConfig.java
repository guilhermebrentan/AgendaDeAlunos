package com.example.agenda;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl("ipEdward:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Service getService(){
        return this.retrofit.create(Service.class);
    }
}
