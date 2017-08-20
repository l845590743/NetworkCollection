package com.lzm.networkcollection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lzm.networkcollection.retrofit.IpModel;
import com.lzm.networkcollection.retrofit.IpServices;
import com.lzm.networkcollection.volley.Constants;
import com.lzm.networkcollection.volley.ResponseBean;
import com.lzm.networkcollection.volley.VolleyListenerInterface;
import com.lzm.networkcollection.volley.VolleyRequestUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static RequestQueue volleyQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initData();
//        volleyRequest();
//        okhttpRequest();
//        retrofitRequest("192.168.5.8");
    }

    private void retrofitRequest(String ip) {
        String url = "http://ip.taobao.com/service/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IpServices ipService = retrofit.create(IpServices.class);
        Call<IpModel> call = ipService.getIpMsg(ip);
        call.enqueue(new Callback<IpModel>() {
            @Override
            public void onResponse(Call<IpModel> call, Response<IpModel> response) {
                String country = response.body().getData().getCountry();
                Log.i("zhimin","country"+country);
                Toast.makeText(getApplicationContext(),country,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<IpModel> call, Throwable t) {

            }
        });
    }

    /**
     * 网络请求参考文档： http://liuwangshu.cn/application/network/6-okhttp3.html
     */
    private void okhttpRequest() {
        //https://github.com/pengjianbo/OkHttpFinal
    }

    private void volleyRequest() {
        //主线程发起请求 回调主线程
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("serialno", "0123456");
        String url = Constants.httpRequestURl(Constants.ONEKEY_REGISTER, paramMap, this);
        VolleyRequestUtil.RequestGet(this, url, "register", new VolleyListenerInterface(ResponseBean.class,
                VolleyListenerInterface.mListener,VolleyListenerInterface.mErrorListener) {
            @Override
            public void onMySuccess(Object result) {
                ResponseBean responseBean = (ResponseBean) result;
            }

            @Override
            public void onMyError(VolleyError error) {

            }
        });
    }

    private void initData() {
        volleyQueue = Volley.newRequestQueue(this);
    }

    public static RequestQueue getRequestQueue() {
        return volleyQueue;
    }
}
