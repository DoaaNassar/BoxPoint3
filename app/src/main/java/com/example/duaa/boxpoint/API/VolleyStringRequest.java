package com.example.duaa.boxpoint.API;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.duaa.boxpoint.API.UserAPI;
import com.example.duaa.boxpoint.Application.ApplicationController;
import com.example.duaa.boxpoint.Interface.UniversalCallBack;
import com.example.duaa.boxpoint.Object.ResponseToken;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by AL-Qema on 08/03/18.
 */

public class VolleyStringRequest extends Request<String> {

    Response.Listener<String> successListener;

    public VolleyStringRequest(int method, String url, Response.Listener<String> successListener,
                               Response.ErrorListener listener) {
        super(method, url, listener);
        this.successListener = successListener;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);

        }

        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {
        try {
            String s = URLEncoder.encode(response, "ISO-8859-1");
            response = URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        successListener.onResponse(response);
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
            try {
                volleyError = new VolleyError(new String(volleyError.networkResponse.data ,"utf-8"));
                if (volleyError.getMessage()!= null&& volleyError.getMessage().contains("Unauthenticated")) {

                    new UserAPI().refresh_token(
                            "def502009bdb94501d98ffc9387f1d7e85657f73096ca8fb2e793b5caaff76fbaaf0773665dddd59bbae5630628f393ceaecd5791139085687e6f10c931af80f62277ffa61c31767480305b26dc44a805cdc1cc496b3b3274ab5cf3735d45cd06651289f8c53eb8f76fb2ddbb528853b7ba6902d265f0309c71e5b28a8391031df27662ea1bb9ecc1fd0ed3c53ac20a6d30e898fe9fe91e4de24f63d407501b3f458da1573ed6da0791a5baca8800bee3e3334d9d34bfc05336b8065ed837b3c291486e818c16fcf6db0ac43dedec0cc95ac80af717bb53f81186e8082c4fda3606d247261b83dd819c8611c341ab185f87a9bd15c47e19388276cb1aceb73e0443d9985ef27e66e02fec4709aa49286c798cb8da4f7a3e024a18e008387d056b39c3f83606c15701626945250ebd0b8e5a42e1c3a03c6c11b83af0e00a98021ccb7610ec93d7540a9df68d572e19afa986d9c61f388c7bcf39861d0cc5fba73cb9f9847",
                            new UniversalCallBack() {
                        @Override
                        public void onResponse(Object UniversityListModel) {
                            ResponseToken responseToken = (ResponseToken) UniversityListModel;
                            ApplicationController.getInstance().RefreshToken(responseToken);

                        }

                        @Override
                        public void onFailure(Object UniversityListModel) {
//                            ApplicationController.getInstance().Logout();
                        }

                        @Override
                        public void onFinish() {

                        }

                        @Override
                        public void OnError(String message) {
//                            ApplicationController.getInstance().Logout();

                        }
                    }

                    );
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return volleyError;
    }




}
