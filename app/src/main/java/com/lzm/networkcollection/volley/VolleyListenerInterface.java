package com.lzm.networkcollection.volley;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * 请求回调：
 * Created by mm on 2016/11/21.
 */
public abstract class VolleyListenerInterface {
    int retStatus;
    public static Response.Listener<String> mListener;
    public static Response.ErrorListener mErrorListener;
    private Class mResponseClass;

    public VolleyListenerInterface(Class responseClass,Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this.mErrorListener = errorListener;
        this.mListener = listener;
        this.mResponseClass = responseClass;
    }

    // 请求成功时的回调函数
    public abstract void onMySuccess(Object result);

    // 请求失败时的回调函数
    public abstract void onMyError(VolleyError error);

    // 创建请求的事件监听
    public Response.Listener<String> responseListener() {
        mListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
//                System.out.println("=="+s);
                if (s == null) {
                    return;
                }
                Object responseObj = GsonUtil.parseJsonToBean(s, mResponseClass);
                if (responseObj != null) {
                    onMySuccess(responseObj);
                }
            }
        };
        return mListener;
    }

    // 创建请求失败的事件监听
    public Response.ErrorListener errorListener() {
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onMyError(volleyError);
            }
        };
        return mErrorListener;
    }
}
