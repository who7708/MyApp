package com.example.myapplication.common.api;


import com.example.myapplication.common.Result;
import com.example.myapplication.common.api.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {
    // get请求
    @GET("api/users")
    Call<Result<User>> userList(@Query("page") int page);

    // get请求
    @GET("api/users/{id}")
    Call<Result<User>> userDetail(@Path("id") Long id);


}