package com.example.myapplication.common;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;

import androidx.annotation.NonNull;

import com.example.myapplication.common.api.BaseUrl;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    public static final String API_GITHUB_BASE_URL = "https://api.github.com";

    public static final String API_REQRES_BASE_URL = "https://reqres.in";

    public static final String API_TAOBAO_IP_BASE_URL = "https://ip.taobao.com";

    private static final Map<String, Retrofit> retrofitMap = new ConcurrentHashMap<>();

    private static final String TAG = ServiceGenerator.class.getSimpleName();

    public static <S> S createService(Class<S> serviceClass) {
        // 获取某api的域名配置
        BaseUrl baseUrlAnno = serviceClass.getAnnotation(BaseUrl.class);
        assert baseUrlAnno != null;
        final String baseUrl = baseUrlAnno.value();

        Retrofit retrofit;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            retrofit = retrofitMap.getOrDefault(baseUrl, null);
        } else {
            retrofit = retrofitMap.get(baseUrl);
        }
        if (retrofit == null) {

            // retrofit
            final Retrofit.Builder BUILDER =
                    new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            // rxjava1
                            // .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            // rxjava2
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

            // 日志
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

            // okhttp
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(@NonNull Chain chain) throws IOException {
                            final Request original = chain.request();
                            String cookie = CookieManager.getInstance().getCookie(baseUrl);
                            if (TextUtils.isEmpty(cookie)) {
                                return chain.proceed(original);
                            }

                            Log.d(TAG, "logging: " + cookie);
                            // 设置cookie
                            final Request requestWithCookie = original.newBuilder()
                                    .addHeader("Cookie", cookie)
                                    .build();
                            return chain.proceed(requestWithCookie);
                        }
                    })
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