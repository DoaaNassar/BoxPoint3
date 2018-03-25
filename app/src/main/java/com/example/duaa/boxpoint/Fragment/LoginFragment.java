package com.example.duaa.boxpoint.Fragment;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.duaa.boxpoint.Activity.NavigationActivity;
import com.example.duaa.boxpoint.Application.ApplicationController;
import com.example.duaa.boxpoint.Interface.UniversalCallBack;
import com.example.duaa.boxpoint.Object.ResponseToken;
import com.example.duaa.boxpoint.Object.UserObject;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.Model.ResponseError;
import com.example.duaa.boxpoint.API.UserAPI;
import com.example.duaa.boxpoint.view.FontButtonRegular;
import com.example.duaa.boxpoint.view.FontEditTextViewRegular;
import com.example.duaa.boxpoint.view.FontTextViewRegular;

import dmax.dialog.SpotsDialog;

import static com.example.duaa.boxpoint.Constant.Constants.getLanguage;

public class LoginFragment extends android.app.Fragment {
    View view;
    LinearLayout arabicBox;
    LinearLayout englishBox;
    FontButtonRegular loginBtn;
    AlertDialog dialog;
    FontEditTextViewRegular userName, password;
    FontTextViewRegular forgetPassword ;


    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        bindView();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ValidationInput()) {
                    loginBtn.setEnabled(false);
                    Login(userName.getText().toString(), password.getText().toString());
                } else {
                    Toast.makeText(getActivity(), "fill all field", Toast.LENGTH_SHORT).show();
                }

            }
        });

        if (getLanguage().equals("ar")) {

            arabicBox.setVisibility(View.VISIBLE);
            englishBox.setVisibility(View.GONE);


        } else {
            arabicBox.setVisibility(View.GONE);
            englishBox.setVisibility(View.VISIBLE);
        }

        view.findViewById(R.id.newUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.app.Fragment con = new NewUserFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainerLogin, con).commit();


            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.Fragment con = new ResetFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainerLogin, con).commit();


            }
        });

        return view;
    }

    public void bindView() {
        arabicBox =  view.findViewById(R.id.boxArabic);
        englishBox = view.findViewById(R.id.boxEnglish);
        loginBtn = view.findViewById(R.id.loginBtn);
        userName = view.findViewById(R.id.userName);
        password = view.findViewById(R.id.password);
        forgetPassword = view.findViewById(R.id.forgetPassword);
        dialog = new SpotsDialog(getActivity(), R.style.Custom);

    }


    public boolean ValidationInput() {

        if (TextUtils.isEmpty(userName.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            return false;
        }
        return true;

    }


    public void Login(final String Email, final String Password) {
        dialog.show();
        new UserAPI().Login(Email, Password, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseToken responseToken = (ResponseToken) result;

                if (responseToken != null) {

                    ApplicationController.getInstance().login_token(responseToken.getAccess_token());
                    getUserData();
                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
//                    Alerter.create(LoginActivity.this)
//                            .setText(responseError.getMessage())
//                            .hideIcon()
//                            .setBackgroundColorRes(R.color.colorPrimary)
//                            .show();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
//                Alerter.create(LoginActivity.this)
//                        .setText(message)
//                        .hideIcon()
//                        .setBackgroundColorRes(R.color.colorPrimary)
//                        .show();
            }
        });
    }

//    public void OauthUser(final ResponseToken responseToken) {
//        new UserAPI().OauthUser(responseToken.getAccess_token(), new UniversalCallBack() {
//            @Override
//            public void onResponse(Object result) {
//                ResponseUser responseUser = (ResponseUser) result;
//                Log.d("ffff", "fffff");
//                if (responseUser.isStatus()) {
//
//                    responseUser.getItems().setAccess_token(responseToken.getAccess_token());
//                    responseUser.getItems().setRefresh_token(responseToken.getRefresh_token());
//                    responseUser.getItems().setExpires_in(responseToken.getExpires_in());
//                    responseUser.getItems().setToken_type(responseToken.getToken_type());
//                    ApplicationController.getInstance().loginUser(responseUser.getItems());
//                    getUserData();
//                }
//            }
//
//            @Override
//            public void onFailure(Object result) {
//                if (result != null) {
//                    ResponseError responseError = (ResponseError) result;
////                    Alerter.create(LoginActivity.this)
////                            .setText(responseError.getMessage())
////                            .hideIcon()
////                            .setBackgroundColorRes(R.color.colorPrimary)
////                            .show();
//                }
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//
//            @Override
//            public void OnError(String message) {
////                Alerter.create(LoginActivity.this)
////                        .setText(message)
////                        .hideIcon()
////                        .setBackgroundColorRes(R.color.colorPrimary)
////                        .show();
//            }
//        });
//    }


    private void getUserData() {
        new UserAPI().getUserData(new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                UserObject response = (UserObject) result;
                if (response.isStatus()) {

                    ApplicationController.getInstance().userLogin(response.getUserDataResponse());

                    Intent intent = new Intent(getActivity(), NavigationActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    dialog.hide();
                    loginBtn.setEnabled(true);

                }
            }

            @Override
            public void onFailure(Object result) {

            }

            @Override
            public void onFinish() {
            }

            @Override
            public void OnError(String message) {

            }
        });
    }

}
