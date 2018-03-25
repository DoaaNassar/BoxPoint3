package com.example.duaa.boxpoint.Interface;

/**
 * Created by AL-Qema on 10/03/18.
 */

public interface UniversalCallBack {

    void onResponse(Object result);
    void onFailure(Object result);
    void onFinish();
    void OnError(String message);
}
