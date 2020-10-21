package com.example.agendaaluno;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.137:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Service getService(){
        return this.retrofit.create(Service.class);
    }
}
