package com.lzm.networkcollection.volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.lzm.networkcollection.MainActivity;

import java.util.Map;

/**
 * Created by mm on 2016/11/21.
 */
public class VolleyRequestUtil {

    public static StringRequest stringRequest;
    public static Context context;

    /**
     * GET请求：
     * @param context
     * @param url
     * @param tag
     * @param volleyListenerInterface
     */
    public static void RequestGet(Context context, String url, String tag, VolleyListenerInterface volleyListenerInterface) {
        // 清除请求队列中的tag标记请求
        MainActivity.getRequestQueue().cancelAll(tag);
        // 创建当前的请求
        stringRequest = new StringRequest(Request.Method.GET, url, volleyListenerInterface.responseListener(), volleyListenerInterface.errorListener());
        // 为当前请求添加标记
        stringRequest.setTag(tag);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(2000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // 将当前请求添加到请求队列中
        MainActivity.getRequestQueue().add(stringRequest);
        // 重启当前请求队列
//        MainActivity.getRequestQueue().start();
    }

    /**
     * POST请求：
     * @param context
     * @param url
     * @param tag
     * @param params
     * @param volleyListenerInterface
     */
    public static void RequestPost(Context context, String url, String tag, final Map<String, String> params, VolleyListenerInterface volleyListenerInterface) {

        MainActivity.getRequestQueue().cancelAll(tag);
        // 创建当前的POST请求，并将请求内容写入Map中
        stringRequest = new StringRequest(Request.Method.POST, url, volleyListenerInterface.responseListener(), volleyListenerInterface.errorListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);

        MainActivity.getRequestQueue().add(stringRequest);
    }
}

