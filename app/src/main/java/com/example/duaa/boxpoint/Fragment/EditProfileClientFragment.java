package com.example.duaa.boxpoint.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duaa.boxpoint.Application.ApplicationController;
import com.example.duaa.boxpoint.Constant.Constants;
import com.example.duaa.boxpoint.Interface.UniversalCallBack;
import com.example.duaa.boxpoint.Object.ResponseUser;
import com.example.duaa.boxpoint.Object.UserDataResponse;
import com.example.duaa.boxpoint.Object.UserObject;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.Model.ResponseError;
import com.example.duaa.boxpoint.API.UserAPI;
import com.example.duaa.boxpoint.view.FontButtonRegular;
import com.example.duaa.boxpoint.view.FontEditTextViewRegular;
import com.example.duaa.boxpoint.view.FontTextViewRegular;


public class EditProfileClientFragment extends android.app.Fragment {

    View view;
    ImageView back,uploadImage;
    FontEditTextViewRegular userName,firstName ,lastName  ,email,phoneNumber;
    FontButtonRegular backData ,saveUpdate ;
    FontTextViewRegular changePassword ;
    FrameLayout loadingLayout ;

    public static EditProfileClientFragment newInstance(String param1, String param2) {
        EditProfileClientFragment fragment = new EditProfileClientFragment();

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
        view = inflater.inflate(R.layout.fragment_edit_client_profile, container, false);
        // Inflate the layout for this fragment
        bindView();
        if (Constants.getLanguage().equals("en")) {
            back.setRotation(180);
        }

        final UserDataResponse user = ApplicationController.getInstance().getLoginUser();

        firstName.setText(user.getFirst_name());
        lastName.setText(user.getLast_name());
        userName.setText(user.getUsername());
        email.setText(user.getEmail());
        phoneNumber.setText(user.getPhone()+"");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.app.Fragment homeFragment = new HomeFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, homeFragment).commit();

            }
        });

        saveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String userNameText = userName.getText().toString();
                String phoneText = phoneNumber.getText().toString();
                String firstNameText =firstName.getText().toString();
                String lastNameText =lastName.getText().toString();

                saveUpdate.setEnabled(false);
                loadingLayout.setVisibility(View.VISIBLE);

                if (ValidationClientSignup()){
                    UpdateUser(userNameText,firstNameText,lastNameText,emailText,phoneText,
                            ApplicationController.getInstance().token());
                } else {
                    Toast.makeText(getActivity(), getString(R.string.addAll), Toast.LENGTH_SHORT).show();
                }


            }
        });

        backData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName.setText(user.getFirst_name());
                lastName.setText(user.getLast_name());
                userName.setText(user.getUsername());
                email.setText(user.getEmail());
                phoneNumber.setText(user.getPhone()+"");
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.app.Fragment homeFragment = new ChangePasswordFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, homeFragment).commit();

            }
        });

        return view;
    }

    public void bindView() {
        back = view.findViewById(R.id.back);
        backData=view.findViewById(R.id.backData);
        saveUpdate=view.findViewById(R.id.saveUpdate);
        firstName=view.findViewById(R.id.firstName);
        lastName =view.findViewById(R.id.lastName);
        userName=view.findViewById(R.id.userName);
        email =view.findViewById(R.id.email);
        changePassword =view.findViewById(R.id.changePassword);
        uploadImage=view.findViewById(R.id.uploadImage);
        phoneNumber=view.findViewById(R.id.phoneNumber);
        loadingLayout = view.findViewById(R.id.loadingLayout);

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

                    android.app.Fragment homeFragment = new HomeFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frameContainer, homeFragment).commit();

                    return true;

                }

                return false;
            }
        });
    }


    public void UpdateUser(final String userName, final String firstName, final String lastName , final String email , final String phone,
                           final String token ) {
        new UserAPI().UpdateUser(userName,firstName,lastName,email,phone,token, new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseUser responseUser = (ResponseUser) result;
                if (responseUser.isStatus()) {

                    saveUpdate.setEnabled(true);
                    getUserData();

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
            }});
    }

    private void getUserData() {
        new UserAPI().getUserData(new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                UserObject response = (UserObject) result;
                if (response.isStatus()) {

                    ApplicationController.getInstance().userLogin(response.getUserDataResponse());
                    android.app.Fragment editProfileClientFragment = new EditProfileClientFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frameContainer, editProfileClientFragment).commit();
                    loadingLayout.setVisibility(View.GONE);
//                    Log.d("meeeeh", response.toString());
//                    if(response.getUserDataResponse().getIs_verified() == 1){
//                        Intent intent = new Intent(context, SplashSliderActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
//                        finish();
//                    }else if (response.getUserDataResponse().getIs_verified() == 0){
//                        resendcode();
//                        Intent intent = new Intent(context, VerficationActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
//                        finish();
//                    }
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

    public boolean ValidationClientSignup() {


        if (TextUtils.isEmpty(firstName.getText().toString())) {
            return false;
        } else if (TextUtils.isEmpty(lastName.getText().toString()))
            return false;
        else if (TextUtils.isEmpty(userName.getText().toString()))
            return false;
        else if (TextUtils.isEmpty(email.getText().toString()))
            return false;

        else if (TextUtils.isEmpty(phoneNumber.getText().toString()))
            return false;

        return true;
    }

}
