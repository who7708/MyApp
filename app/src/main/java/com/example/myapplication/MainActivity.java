package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.common.Result;
import com.example.myapplication.common.api.UserApi;
import com.example.myapplication.common.api.model.User;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;

    //构建Retrofit实例
    Retrofit mRetrofit = new Retrofit.Builder()
            //设置网络请求BaseUrl地址
            .baseUrl("https://reqres.in")
            //设置数据解析器
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        // setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());

        binding.btnUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建网络请求接口对象实例
                UserApi api = mRetrofit.create(UserApi.class);
                //对发送请求进行封装
                Call<Result<User>> dataCall = api.userDetail(2L);
                // 异步请求
                dataCall.enqueue(new Callback<Result<User>>() {
                    //请求成功回调
                    @Override
                    public void onResponse(Call<Result<User>> call, Response<Result<User>> response) {
                        Log.d(TAG, "onResponse: ");
                        Toast.makeText(MainActivity.this, "get回调成功:异步执行", Toast.LENGTH_SHORT).show();
                        Result<User> body = response.body();
                        if (body == null) {
                            return;
                        }
                        User info = body.getData();
                        if (info == null) {
                            return;
                        }
                        String json = new Gson().toJson(info);
                        binding.tvShowUser.setText("返回的数据：" + json);
                    }

                    //请求失败回调
                    @Override
                    public void onFailure(Call<Result<User>> call, Throwable t) {
                        Log.e(TAG, "回调失败：" + t.getMessage() + "," + t);
                        Toast.makeText(MainActivity.this, "回调失败", Toast.LENGTH_SHORT).show();
                    }
                });

                // //同步请求
                // try {
                //     Response<Result<User>> data= dataCall.execute();
                // } catch (IOException e) {
                //     e.printStackTrace();
                // }
            }
        });

        binding.btnUserDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}