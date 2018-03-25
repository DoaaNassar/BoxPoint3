package com.example.duaa.boxpoint.Fragment;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.duaa.boxpoint.Constant.Constants;
import com.example.duaa.boxpoint.Interface.UniversalCallBack;
import com.example.duaa.boxpoint.Object.ResponseResultUser;
import com.example.duaa.boxpoint.Object.UserModel;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.API.UserAPI;
import com.example.duaa.boxpoint.view.FontButtonRegular;
import com.example.duaa.boxpoint.view.FontEditTextViewRegular;

public class AddClientFragment extends android.app.Fragment {

    View view;
    ImageView back;
    FontButtonRegular save;
    FontEditTextViewRegular firstName, lastName, userName, password, email, image,phone;
    Dialog pDialog;



    public static AddClientFragment newInstance() {
        AddClientFragment fragment = new AddClientFragment();

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
        view = inflater.inflate(R.layout.fragment_add_client, container, false);
        bindView();
        if (Constants.getLanguage().equals("en")) {
            back.setRotation(180);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.app.Fragment con = new NewUserFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainerLogin, con).commit();


            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ValidationClientSignup()) {
                    String emailText = email.getText().toString();
                    String passwordText = password.getText().toString();
                    String userNameText = userName.getText().toString();
                    String phoneText = phone.getText().toString();
                    String firstNameText =firstName.getText().toString();
                    String lastNameText =lastName.getText().toString();

          AddUser(new UserModel(emailText,passwordText,userNameText,phoneText,
                  firstNameText,lastNameText));
                }

            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void bindView() {
        back = view.findViewById(R.id.back);
        save = view.findViewById(R.id.save);
        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        userName = view.findViewById(R.id.userName);
        password = view.findViewById(R.id.password);
        email = view.findViewById(R.id.email);
        image = view.findViewById(R.id.imagePhoto);
        phone =view.findViewById(R.id.phoneNumber);


    }

    public boolean ValidationClientSignup() {


        if (TextUtils.isEmpty(firstName.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(lastName.getText().toString()))
            return false;
        else if (TextUtils.isEmpty(userName.getText().toString()))
            return false;
        else if (TextUtils.isEmpty(password.getText().toString()))
            return false;
        else if (TextUtils.isEmpty(email.getText().toString()))
            return false;

        else if (TextUtils.isEmpty(phone.getText().toString()))
            return false;

        return true;
    }

    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                    android.app.Fragment newUserFragment = new NewUserFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frameContainerLogin, newUserFragment).commit();

                    return true;

                }

                return false;
            }
        });
    }


    public void AddUser( final UserModel item){
        new UserAPI().AddNewUser(item, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                ResponseResultUser responseItem = (ResponseResultUser) result;
                if (responseItem.isStatus()){


                }
////                    pDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//                    pDialog.setTitle(getString(R.string.addNew));
//                    pDialog.setCancelable(false);
//                    pDialog.setCancelMessage(getString(R.string.ok))
//                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sDialog) {
//                                    sDialog.dismissWithAnimation();
//                                    finish();
//                                }
//                            });
//                    pDialog.show();
//                }else {
//                    pDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
//                    pDialog.setTitleText(getString(R.string.Failed_add));
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
//                }

            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {

                }

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