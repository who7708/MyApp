package com.example.myapplication.common;

import android.os.Build;

import com.example.myapplication.common.api.BaseUrl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    public static final String API_GITHUB_BASE_URL = "https://api.github.com";

    public static final String API_REQRES_BASE_URL = "https://reqres.in";

    private static final Map<String, Retrofit> retrofitMap = new ConcurrentHashMap<>();

    public static <S> S createService(Class<S> serviceClass) {
        BaseUrl baseUrlAnno = serviceClass.getAnnotation(BaseUrl.class);
        assert baseUrlAnno != null;
        String baseUrl = baseUrlAnno.value();
        Retrofit retrofit;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            retrofit = retrofitMap.getOrDefault(baseUrl, null);
        } else {
            retrofit = retrofitMap.get(baseUrl);
        }
        if (retrofit == null) {
            final Retrofit.Builder BUILDER =
                    new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            // rxjava1
                            // .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            // rxjava2
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
            retrofit = BUILDER.client(client).build();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                retrofitMap.putIfAbsent(baseUrl, retrofit);
            } else {
                retrofitMap.put(baseUrl, retrofit);
            }
        }
        return retrofit.create(serviceClass);
    }
}