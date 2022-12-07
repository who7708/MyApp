package com.example.myapplication.common.api.reqres;

import com.example.myapplication.common.Result;
import com.example.myapplication.common.ServiceGenerator;
import com.example.myapplication.common.api.BaseUrl;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

@BaseUrl(ServiceGenerator.API_REQRES_BASE_URL)
public interface UserApi {
    // get请求
    @GET("api/users")
    Call<Result<User>> userList(@Query("page") int page);

    // get请求
    @GET("api/users/{id}")
    Call<Result<User>> userDetail(@Path("id") Long id);

}