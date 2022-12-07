package com.example.myapplication.common.api.github;

import com.example.myapplication.common.ServiceGenerator;
import com.example.myapplication.common.api.BaseUrl;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

@BaseUrl(ServiceGenerator.API_GITHUB_BASE_URL)
public interface GitHubApi {

    @GET
    Call<GithubEndpoints> getAllEndpoints(@Url String url);

    @GET("users/{user}")
    Call<GithubUser> getUser(@Path("user") String user);

    @GET("users/{user}/following")
    Call<List<GithubUser>> getUserFollowing(@Path("user") String user);

}