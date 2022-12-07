package com.example.myapplication.common.api.taobao;

import com.example.myapplication.common.Result;
import com.example.myapplication.common.ServiceGenerator;
import com.example.myapplication.common.api.BaseUrl;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author Chris
 * @since 1.0
 */
@BaseUrl(ServiceGenerator.API_TAOBAO_IP_BASE_URL)
public interface TaobaoApi {
    // https://ip.taobao.com/outGetIpInfo?ip=59.82.61.62&accessKey=alibaba-inc
    @FormUrlEncoded
    @POST("/outGetIpInfo")
    Call<Result<IpInfo>> outGetIpInfo(@Field("ip") String ip, @Field("accessKey") String accessKey);
}
