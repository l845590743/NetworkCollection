package com.lzm.networkcollection.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lzm on 2017/8/20.
 *
 * url = http://ip.taobao.com/service/getIpInfo.php?ip=[ip地址字串]
 */
public interface IpServices {
    @GET("getIpInfo.php")
    Call<IpModel> getIpMsg(@Query("ip") String ip);
}
