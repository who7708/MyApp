package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.common.Result;
import com.example.myapplication.common.ServiceGenerator;
import com.example.myapplication.common.api.github.GitHubApi;
import com.example.myapplication.common.api.github.GithubEndpoints;
import com.example.myapplication.common.api.reqres.User;
import com.example.myapplication.common.api.reqres.UserApi;
import com.example.myapplication.common.api.taobao.IpInfo;
import com.example.myapplication.common.api.taobao.TaobaoApi;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final Gson GSON = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create();

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        // setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());

        binding.btnUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // test1();
                test2();
            }
        });

        binding.btnUserDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // final String wvOpenUrl = "https://www.baidu.com";
        // final String wvOpenUrl = "https://gitee.com/login";
        final String wvOpenUrl = ServiceGenerator.API_TAOBAO_IP_BASE_URL;

        binding.btnUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得控件
                // WebView webView = findViewById(R.id.wv_login);
                final WebView webView = binding.wvLogin;
                //访问网页
                webView.loadUrl(wvOpenUrl);
                // 系统默认会通过手机浏览器打开网页，为了能够直接通过WebView显示网页，则必须设置
                webView.setWebChromeClient(new WebChromeClient() {
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        super.onProgressChanged(view, newProgress);
                        binding.tvShowUser.setText("newProgress: " + newProgress);
                        if (newProgress == 100) {
                            String cookies = CookieManager.getInstance().getCookie(view.getUrl());
                            // save cookies or call new fun to handle actions
                            //  newCookies(cookies);
                            Log.d(TAG, "onProgressChanged#getCookie: " + cookies);
                            // Toast.makeText(MainActivity.this, "onProgressChanged#getCookie: " + cookies, Toast.LENGTH_SHORT).show();
                            binding.tvShowUser.setText("onProgressChanged#getCookie: " + cookies);
                        }
                    }
                });
                // webView.setWebViewClient(new WebViewClient() {
                //
                //     @Override
                //     public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //         // 使用WebView加载显示url
                //         view.loadUrl(url);
                //         Log.d(TAG, "url: " + url);
                //         // Toast.makeText(MainActivity.this, "url: " + url, Toast.LENGTH_SHORT).show();
                //         binding.tvShowUser.setText("url: " + url);
                //         // 返回true
                //         return true;
                //     }
                //
                //     @Override
                //     public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //         String url = request.getUrl().toString();
                //         return this.shouldOverrideUrlLoading(view, url);
                //     }
                //
                //     @Override
                //     public void onPageFinished(WebView view, String url) {
                //         super.onPageFinished(view, url);
                //         String cookies = CookieManager.getInstance().getCookie(view.getUrl());
                //         // save cookies or call new fun to handle actions
                //         //  newCookies(cookies);
                //         Log.d(TAG, "onPageFinished#getCookie: " + cookies);
                //         // Toast.makeText(MainActivity.this, "onPageFinished#getCookie: " + cookies, Toast.LENGTH_SHORT).show();
                //         binding.tvShowUser.setText("onPageFinished#getCookie: " + cookies);
                //     }
                // });
            }
        });

        binding.btnUserCookie.setOnClickListener(v -> {
            final CookieManager cookieManager = CookieManager.getInstance();
            boolean hasCookies = cookieManager.hasCookies();
            Log.d(TAG, "hasCookies: " + hasCookies);
            String cookie = cookieManager.getCookie(wvOpenUrl);
            Log.d(TAG, "setOnClickListener#getCookie: " + cookie);
            // Toast.makeText(MainActivity.this, "setOnClickListener#getCookie: " + cookie, Toast.LENGTH_SHORT).show();
            binding.tvShowUser.setText("setOnClickListener#getCookie: " + cookie);
        });

        binding.btnIpQuery.setOnClickListener(v -> {
            Editable text = binding.tvIpInput.getText();
            if (TextUtils.isEmpty(text)) {
                return;
            }
            queryIp(text.toString());
        });

    }

    private void test2() {
        //创建网络请求接口对象实例
        UserApi userApi = ServiceGenerator.createService(UserApi.class);
        //对发送请求进行封装
        final Call<Result<User>> dataCall = userApi.userDetail(2L);
        // 异步请求
        dataCall.enqueue(new Callback<Result<User>>() {
            //请求成功回调
            @Override
            public void onResponse(@NonNull Call<Result<User>> call, @NonNull Response<Result<User>> response) {
                showUser(response);
            }

            //请求失败回调
            @Override
            public void onFailure(@NonNull Call<Result<User>> call, @NonNull Throwable t) {
                Log.e(TAG, "回调失败：", t);
                Toast.makeText(MainActivity.this, "回调失败", Toast.LENGTH_SHORT).show();
            }
        });

        // new FutureTask<Void>(new Runnable() {
        //     @Override
        //     public void run() {
        //         // 同步请求
        //         try {
        //             Response<Result<User>> response = dataCall.execute();
        //             showUser(response);
        //         } catch (IOException t) {
        //             Log.e(TAG, "回调失败：", t);
        //             Toast.makeText(MainActivity.this, "回调失败", Toast.LENGTH_SHORT).show();
        //         }
        //     }
        // }, null).run();

        // new Thread(new Runnable() {
        //     // // NetworkOnMainThreadException 异常
        //     // runOnUiThread(new Runnable() {
        //     // Executors.newSingleThreadExecutor().submit(new Runnable() {
        //     @Override
        //     public void run() {
        //         // 同步请求
        //         try {
        //             Response<Result<User>> response = dataCall.execute();
        //             showUser(response);
        //         } catch (IOException t) {
        //             Log.e(TAG, "回调失败：", t);
        //             runOnUiThread(new Runnable() {
        //                 @Override
        //                 public void run() {
        //                     Toast.makeText(MainActivity.this, "回调失败", Toast.LENGTH_SHORT).show();
        //                 }
        //             });
        //         }
        //     }
        // })
        //         .start()
        // ;

    }

    private void showUser(Response<Result<User>> response) {
        Toast.makeText(MainActivity.this, "get回调成功:异步执行", Toast.LENGTH_SHORT).show();
        Result<User> body = response.body();
        Log.d(TAG, "onResponse: " + body);
        if (body == null) {
            return;
        }
        User info = body.getData();
        if (info == null) {
            return;
        }
        String json = new Gson().toJson(info);
        Toast.makeText(MainActivity.this, json, Toast.LENGTH_SHORT).show();
    }

    private void test1() {
        GitHubApi gitHubService = ServiceGenerator.createService(GitHubApi.class);
        Call<GithubEndpoints> endpointsCall = gitHubService.getAllEndpoints("");
        endpointsCall.enqueue(new Callback<GithubEndpoints>() {
            public void onResponse(@NonNull Call<GithubEndpoints> call, @NonNull Response<GithubEndpoints> response) {
                GithubEndpoints githubEndpoints = response.body();
                assert githubEndpoints != null;
                String repositoryUrl = githubEndpoints.getRepositoryUrl();
                Log.d(TAG, "onResponse: " + repositoryUrl);
                Toast.makeText(MainActivity.this, repositoryUrl, Toast.LENGTH_SHORT).show();
            }

            public void onFailure(@NonNull Call<GithubEndpoints> call, @NonNull Throwable t) {
                Log.e(TAG, "回调失败：", t);
                Toast.makeText(MainActivity.this, "回调失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 查询ip
     */
    private void queryIp(String ip) {
        TaobaoApi taobaoApi = ServiceGenerator.createService(TaobaoApi.class);
        Call<Result<IpInfo>> resultCall = taobaoApi.outGetIpInfo(ip, "alibaba-inc");
        resultCall.enqueue(new Callback<Result<IpInfo>>() {
            public void onResponse(@NonNull Call<Result<IpInfo>> call, @NonNull Response<Result<IpInfo>> response) {
                Result<IpInfo> ipInfoResult = response.body();
                Log.d(TAG, "onResponse: " + ipInfoResult);
                String json = GSON.toJson(ipInfoResult);
                // Toast.makeText(MainActivity.this, json, Toast.LENGTH_SHORT).show();
                binding.tvShowUser.setText("onResponse: " + json);
            }

            public void onFailure(@NonNull Call<Result<IpInfo>> call, @NonNull Throwable t) {
                Log.e(TAG, "回调失败：", t);
                Toast.makeText(MainActivity.this, "回调失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}