package com.example.androidminiprojet.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.androidminiprojet.network.ApiService;

public class ApiClient {
    private static final String BASE_URL = "http://localhost:8080/api/auth/";
    private static Retrofit retrofit;

    public static ApiService getService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}
