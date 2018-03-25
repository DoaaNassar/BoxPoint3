package com.example.duaa.boxpoint.API;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.example.duaa.boxpoint.Application.ApplicationController;
import com.example.duaa.boxpoint.Constant.Constants;
import com.example.duaa.boxpoint.Interface.UniversalCallBack;
import com.example.duaa.boxpoint.Model.ResponseError;
import com.example.duaa.boxpoint.Object.ResponseObject;
import com.example.duaa.boxpoint.Object.ResponseResultUser;
import com.example.duaa.boxpoint.Object.ResponseToken;
import com.example.duaa.boxpoint.Object.ResponseUser;
import com.example.duaa.boxpoint.Object.ShopsModel;
import com.example.duaa.boxpoint.Object.UserModel;
import com.example.duaa.boxpoint.Object.UserObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//import duaa.traineeproject.Interface.UniversalCallBack;
//import duaa.traineeproject.JavaObject.TraineeObject;
//import duaa.traineeproject.Model.ResponseAddTrainee;
//import duaa.traineeproject.Model.University;
//import duaa.traineeproject.Model.UniversityListModel;

/**
 * Created by AL-Qema on 08/03/18.
 */

public class UserAPI {

    public void Login(final String Email, final String Password, final UniversalCallBack callBack) {
        String url = Constants.login;
        Log.d("Login: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Login: ", response);
                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseToken responseObject = gson.fromJson(response.toString(), ResponseToken.class);
                    callBack.onResponse(responseObject);
                } catch (JsonSyntaxException e) {
                    callBack.OnError("Server Connection error try again later");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callBack.onFinish();
                String message = null;
                Log.d("onErrorResponse", error.toString() + "");
                String json = null;
                Log.d("error.getMessage()", error.getMessage() + "");
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    callBack.OnError(message);
                } else {
                    try {
                        Gson gson = new Gson();
                        ResponseError ErrorMsg = gson.fromJson(error.getMessage(), ResponseError.class);
                        callBack.onFailure(ErrorMsg);
                    } catch (JsonSyntaxException e) {
                        callBack.OnError("Server Connection error try again later");
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("grant_type", "password");
                params.put("client_id", "2");
                params.put("client_secret", Constants.client_secret);
                params.put("username", Email);
                params.put("password", Password);
                params.put("scope", "*");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
//                String bearer = "Bearer ".concat(token);
                Map<String, String> headersSys = super.getHeaders();
                Map<String, String> headers = new HashMap<String, String>();
                headersSys.remove("Authorization");
                headers.put("Accept", "application/json");
//                headers.put("Authorization", bearer);
                headers.putAll(headersSys);
                return headers;
            }
        };

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");

    }


    public void AddNewUser(final UserModel item, final UniversalCallBack callBack) {
        String url = Constants.user;
        Log.d("addUser: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("addUser: ", response);

                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseResultUser responseObject = gson.fromJson(response.toString(), ResponseResultUser.class);
                    callBack.onResponse(responseObject);
                } catch (JsonSyntaxException e) {
                    callBack.OnError("Server Connection error try again later");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callBack.onFinish();
                String message = null;
                Log.d("onErrorResponse", error.toString() + "");
                String json = null;
                Log.d("error.getMessage()", error.getMessage() + "");
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    callBack.OnError(message);
                } else {
                    try {
                        Gson gson = new Gson();
                        ResponseError ErrorMsg = gson.fromJson(error.getMessage(), ResponseError.class);
                        callBack.onFailure(ErrorMsg);
                    } catch (JsonSyntaxException e) {
                        callBack.OnError("Server Connection error try again later");
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("grant_type", "password");
                params.put("client_id", "1");
                params.put("client_secret", Constants.client_secret);
                params.put("email", item.getEmail());
                params.put("password", item.getPassword());
                params.put("scope", "*");
                params.put("username", item.getUsername()+"");
                params.put("phone", item.getPhone()+"");
                params.put("first_name", item.getFirst_name()+"");
                params.put("last_name", item.getLast_name()+"");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
//                String bearer = "Bearer ".concat(token);
                Map<String, String> headersSys = super.getHeaders();
                Map<String, String> headers = new HashMap<String, String>();
                headersSys.remove("Authorization");
                headers.put("Accept", "application/json");
//                headers.put("Authorization", bearer);
                headers.putAll(headersSys);
                return headers;
            }
        };

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");

    }




    public void getUserData(final UniversalCallBack callBack) {

        String url = Constants.user;
        Log.d("Setting: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Setting: ", response);
                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    UserObject responseObject = gson.fromJson(response.toString(), UserObject.class);
                    callBack.onResponse(responseObject);
                } catch (JsonSyntaxException e) {
                    Log.d("ss", e.toString());
                    callBack.OnError("Server Connection error try again later");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callBack.onFinish();
                String message = null;
                Log.d("onErrorResponse", error.toString() + "");
                String json = null;
                Log.d("error.getMessage()", error.getMessage() + "");
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    callBack.OnError(message);
                } else {
                    try {
                        Gson gson = new Gson();
                        ResponseError ErrorMsg = gson.fromJson(error.getMessage(), ResponseError.class);
                        callBack.onFailure(ErrorMsg);
                    } catch (JsonSyntaxException e) {
                        callBack.OnError("Server Connection error try again later");
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String bearer = "Bearer ".concat(ApplicationController.getInstance().token());
                Map<String, String> headersSys = super.getHeaders();
                Map<String, String> headers = new HashMap<String, String>();
                headersSys.remove("Authorization");
                headers.put("Authorization", bearer);
                headers.putAll(headersSys);
                return headers;
            }

        };
        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");
    }


//////////////

    public void UpdateUser(final String userName, final String firstName, final String lastName , final String email , final String phone,
                           final String token , final UniversalCallBack callBack) {
        String url = Constants.UPDATE_USER;
        Log.d("UpdateUser: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("UpdateUser: ", response);
                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseUser responseObject = gson.fromJson(response.toString(), ResponseUser.class);
                    callBack.onResponse(responseObject);
                } catch (JsonSyntaxException e) {
                    callBack.OnError("Server Connection error try again later");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callBack.onFinish();
                String message = null;
                Log.d("onErrorResponse", error.toString() + "");
                String json = null;
                Log.d("error.getMessage()", error.getMessage() + "");
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    callBack.OnError(message);
                } else {
                    try {
                        Gson gson = new Gson();
                        ResponseError ErrorMsg = gson.fromJson(error.getMessage(), ResponseError.class);
                        callBack.onFailure(ErrorMsg);
                    } catch (JsonSyntaxException e) {
                        callBack.OnError("Server Connection error try again later");
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("first_name", firstName);
                params.put("last_name", lastName);
                params.put("username",userName);
                params.put("phone", phone);
                params.put("email",email);
//                params.put("address_address",userDataResponse.getA);


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String bearer = "Bearer ".concat(token);
                Map<String, String> headersSys = super.getHeaders();
                Map<String, String> headers = new HashMap<String, String>();
                headersSys.remove("Authorization");
                headers.put("Accept", "application/json");
                headers.put("Authorization", bearer);
                headers.putAll(headersSys);
                return headers;
            }
        };

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");

    }

//

    public void UpdatePassword(final String token,final String oldPassword , final String newPassword, final UniversalCallBack callBack) {
        String url = Constants.UPDATE_PASSWORD;
        Log.d("UpdatePassword: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("UpdatePassword: ", response);
                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseUser responseObject = gson.fromJson(response.toString(), ResponseUser.class);
                    callBack.onResponse(responseObject);
                } catch (JsonSyntaxException e) {
                    callBack.OnError("Server Connection error try again later");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callBack.onFinish();
                String message = null;
                Log.d("onErrorResponse", error.toString() + "");
                String json = null;
                Log.d("error.getMessage()", error.getMessage() + "");
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    callBack.OnError(message);
                } else {
                    try {
                        Gson gson = new Gson();
                        ResponseError ErrorMsg = gson.fromJson(error.getMessage(), ResponseError.class);
                        callBack.onFailure(ErrorMsg);
                    } catch (JsonSyntaxException e) {
                        callBack.OnError("Server Connection error try again later");
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("oldpassword",oldPassword);
                params.put("newpassword",newPassword);


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String bearer = "Bearer ".concat(token);
                Map<String, String> headersSys = super.getHeaders();
                Map<String, String> headers = new HashMap<String, String>();
                headersSys.remove("Authorization");
                headers.put("Accept", "application/json");
                headers.put("Authorization", bearer);
                headers.putAll(headersSys);
                return headers;
            }
        };

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");

    }


//
//
    public void getCategory(final UniversalCallBack callBack,int id) {
        String url = Constants.SHOPS+id;
        Log.d("Categories: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Categories: ", response);

                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    Log.d("vvvvv","vvvvv");
                    ShopsModel responseObject = gson.fromJson(response.toString(), ShopsModel.class);
                    Log.d("mmmmm","mmmmm");
                    callBack.onResponse(responseObject);
                } catch (JsonSyntaxException e) {
                    callBack.OnError("Server Connection error try again later");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callBack.onFinish();
                String message = null;
                Log.d("onErrorResponse", error.toString() + "");
                String json = null;
                Log.d("error.getMessage()", error.getMessage() + "");
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    callBack.OnError(message);
                } else {
                    try {
                        Gson gson = new Gson();
                        ResponseError ErrorMsg = gson.fromJson(error.getMessage(), ResponseError.class);
                        callBack.onFailure(ErrorMsg);
                    } catch (JsonSyntaxException e) {
                        callBack.OnError("Server Connection error try again later");
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String bearer = "Bearer ".concat(ApplicationController.getInstance().token());
                Map<String, String> headersSys = super.getHeaders();
                Map<String, String> headers = new HashMap<String, String>();
                headersSys.remove("Authorization");
                headers.put("Accept", "application/json");
                headers.put("Authorization", bearer);
                headers.putAll(headersSys);
                return headers;
            }
        };

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");

    }




    public void refresh_token(final String refresh_token, final UniversalCallBack callBack) {
        String url = Constants.REFRESH_TOKEN;
        Log.d("refresh_token: ", url);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "refresh_token");
        params.put("client_id", "2");
        params.put("client_secret", "VHUp8v3DTAssLLqudweCwFF5xfmW1WDVzJxBeKif");
        params.put("refresh_token", refresh_token);
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("refresh_token: ", response.toString());
                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseToken responseObject = gson.fromJson(response.toString(), ResponseToken.class);
                    callBack.onResponse(responseObject);
                } catch (JsonSyntaxException e) {
                    callBack.OnError("Server Connection error try again later");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callBack.onFinish();
                String message = null;
                Log.d("onErrorResponse", error.toString() + "");
                String json = null;
                Log.d("error.getMessage()", error.getMessage() + "");
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    callBack.OnError(message);
                } else {
                    try {
                        Gson gson = new Gson();
                        ResponseError ErrorMsg = gson.fromJson(error.getMessage(), ResponseError.class);
                        callBack.onFailure(ErrorMsg);
                    } catch (JsonSyntaxException e) {
                        callBack.OnError("Server Connection error try again later");
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("grant_type", "refresh_token");
                params.put("client_id", "2");
                params.put("client_secret", "VHUp8v3DTAssLLqudweCwFF5xfmW1WDVzJxBeKif");
                params.put("refresh_token", refresh_token);
                return params;
            }


        };
        future.setRequest(stringRequest);
        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");
    }

    public void OauthUser(final String token, final UniversalCallBack callBack) {
        String url = Constants.OAUTH_USER;
        Log.d("OauthUser", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("OauthUser", response);
                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseUser responseObject = gson.fromJson(response.toString(), ResponseUser.class);
                    callBack.onResponse(responseObject);
                } catch (JsonSyntaxException e) {
                    callBack.OnError("Server Connection error try again later");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callBack.onFinish();
                String message = null;
                Log.d("onErrorResponse", error.toString() + "");
                String json = null;
                Log.d("error.getMessage()", error.getMessage() + "");
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    callBack.OnError(message);
                } else {
                    try {
                        Gson gson = new Gson();
                        ResponseError ErrorMsg = gson.fromJson(error.getMessage(), ResponseError.class);
                        callBack.onFailure(ErrorMsg);
                    } catch (JsonSyntaxException e) {
                        callBack.OnError("Server Connection error try again later");
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String bearer = "Bearer ".concat(token);
                Map<String, String> headersSys = super.getHeaders();
                Map<String, String> headers = new HashMap<String, String>();
                headersSys.remove("Authorization");
                headers.put("Accept", "application/json");
                headers.put("Authorization", bearer);
                headers.putAll(headersSys);
                return headers;
            }
        };

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");
    }

    public void ResetPassword(final String email, final UniversalCallBack callBack) {
        String url = Constants.RESET_PASSWORD;
        Log.d("ResetPassword: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ResetPassword: ", response);
                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseObject responseObject = gson.fromJson(response.toString(), ResponseObject.class);
                    callBack.onResponse(responseObject);
                } catch (JsonSyntaxException e) {
                    callBack.OnError("Server Connection error try again later");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callBack.onFinish();
                String message = null;
                Log.d("onErrorResponse", error.toString() + "");
                String json = null;
                Log.d("error.getMessage()", error.getMessage() + "");
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    callBack.OnError(message);
                } else {
                    try {
                        Gson gson = new Gson();
                        ResponseError ErrorMsg = gson.fromJson(error.getMessage(), ResponseError.class);
                        callBack.onFailure(ErrorMsg);
                    } catch (JsonSyntaxException e) {
                        callBack.OnError("Server Connection error try again later");
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("client_id", "2");
                params.put("client_secret", Constants.client_secret);


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
//                String bearer = "Bearer ".concat(token);
                Map<String, String> headersSys = super.getHeaders();
                Map<String, String> headers = new HashMap<String, String>();
                headersSys.remove("Authorization");
                headers.put("Accept", "application/json");
//                headers.put("Authorization", bearer);
                headers.putAll(headersSys);
                return headers;
            }
        };

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");

    }




    public void Logout(final String token, final UniversalCallBack callBack) {
        String url = Constants.LOGOUT;
        Log.d("Logout: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Logout: ", response);
                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseObject responseObject = gson.fromJson(response.toString(), ResponseObject.class);
                    callBack.onResponse(responseObject);
                } catch (JsonSyntaxException e) {
                    callBack.OnError("Server Connection error try again later");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callBack.onFinish();
                String message = null;
                Log.d("onErrorResponse", error.toString() + "");
                String json = null;
                Log.d("error.getMessage()", error.getMessage() + "");
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    callBack.OnError(message);
                } else {
                    try {
                        Gson gson = new Gson();
                        ResponseError ErrorMsg = gson.fromJson(error.getMessage(), ResponseError.class);
                        callBack.onFailure(ErrorMsg);
                    } catch (JsonSyntaxException e) {
                        callBack.OnError("Server Connection error try again later");
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String bearer = "Bearer ".concat(token);
                Map<String, String> headersSys = super.getHeaders();
                Map<String, String> headers = new HashMap<String, String>();
                headersSys.remove("Authorization");
                headers.put("Accept", "application/json");
                headers.put("Authorization", bearer);
                headers.putAll(headersSys);
                return headers;
            }
        };
        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");

    }


}
