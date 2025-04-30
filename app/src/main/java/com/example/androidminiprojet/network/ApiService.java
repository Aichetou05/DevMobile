package com.example.androidminiprojet.network;

import java.util.List;

import com.example.androidminiprojet.models.User;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
public  interface ApiService {
     @POST("auth/signup")
     Call<User> signup(@Body User user);

     @POST("auth/login")
     Call<User> login(@Body User user);

    Call<User> createUser(User user);
}