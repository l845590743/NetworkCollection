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

        /**
         *     重复请求的问题
         * 1.DefaultRetryPolicy实现于RetryPolicy,RetryPolicy是一个接口;
         2.DefaultRetryPolicy构造函数中的参数1是设置超时时长,默认为2500，可以设置稍微长点；
         3.DefaultRetryPolicy构造函数中的参数2是设置最大重复请求次数，默认为1，可以设置为0；
         4.DefaultRetryPolicy构造函数中的参数3为设置 “允许你指定一个退避乘数可以用来 实现<指数退
         避>来从RESTful服务器请求数据”,默认值为1，当取1时，即可以简单理解为 ” 每次超时请求时长都是 <前一次超时请求时长 *2> “，以此类推
         */

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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

