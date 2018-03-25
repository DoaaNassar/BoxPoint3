package com.example.duaa.boxpoint.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duaa.boxpoint.Application.ApplicationController;
import com.example.duaa.boxpoint.Interface.UniversalCallBack;
import com.example.duaa.boxpoint.Object.ResponseUser;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.Model.ResponseError;
import com.example.duaa.boxpoint.API.UserAPI;
import com.example.duaa.boxpoint.view.FontButtonRegular;
import com.example.duaa.boxpoint.view.FontEditTextViewRegular;

public class ChangePasswordFragment extends android.app.Fragment {

    View view;
    FontEditTextViewRegular oldPassword, newPassword, confirmPassword;
    FontButtonRegular save;

    public static ChangePasswordFragment newInstance() {
        ChangePasswordFragment fragment = new ChangePasswordFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_change_password, container, false);
        bindView();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String oldPasswordText = oldPassword.getText().toString();
                String newPasswordText = newPassword.getText().toString();
                String confirmPasswordText = confirmPassword.getText().toString();

                if (!(TextUtils.isEmpty(oldPasswordText) || TextUtils.isEmpty(newPasswordText)
                        || TextUtils.isEmpty(confirmPasswordText))) {
                    if (newPasswordText.equals(confirmPasswordText)) {

                        UpdatePassword( ApplicationController.getInstance().token(),oldPasswordText,newPasswordText);

                        android.app.Fragment editProfileClientFragment = new EditProfileClientFragment();
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fm.beginTransaction();
                        fragmentTransaction.replace(R.id.frameContainer, editProfileClientFragment).commit();


                    } else {
                        Toast.makeText(getActivity(), "not equal ", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(getActivity(), "fill all field", Toast.LENGTH_SHORT).show();
                }


            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void bindView() {
        oldPassword = view.findViewById(R.id.oldPassword);
        newPassword = view.findViewById(R.id.newPassword);
        confirmPassword = view.findViewById(R.id.confirmPassword);
        save = view.findViewById(R.id.save);

    }

    public void UpdatePassword(final String token,final  String oldPassword ,final String newPassword) {
        new UserAPI().UpdatePassword(token, oldPassword,newPassword, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseUser responseUser = (ResponseUser) result;
                if (responseUser.isStatus()) {
                    Log.d("ggggg", "ggg");

                } else {
//                    pDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
//                    pDialog.setTitleText(getString(R.string.operation_failed));
//                    pDialog.setCancelable(false);
//                    pDialog.setConfirmText(getString(R.string.ok))
//                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sDialog) {
//                                    sDialog.dismissWithAnimation();
//                                    finish();
//                                }
//                            });
//                    pDialog.show();
                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
//                    Alerter.create(EditProfileActivity.this)
//                            .setText(responseError.getMessage())
//                            .hideIcon()
//                            .setBackgroundColorRes(R.color.colorPrimary)
//                            .show();
//                }
//                if (pDialog.isShowing()) {
//                    pDialog.dismissWithAnimation();
//                }
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
//                Alerter.create(EditProfileActivity.this)
//                        .setText(message)
//                        .hideIcon()
//                        .setBackgroundColorRes(R.color.colorPrimary)
//                        .show();
//                if (pDialog.isShowing()) {
//                    pDialog.dismissWithAnimation();
//                }
//            }
//        });
            }
        });
    }

}
