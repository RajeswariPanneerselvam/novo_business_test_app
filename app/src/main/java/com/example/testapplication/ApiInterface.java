package com.example.testapplication;

import com.example.testapplication.model.UsersModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("users")
    Call<ArrayList<UsersModel>> getAllUsers();
}
