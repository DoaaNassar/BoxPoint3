package com.example.duaa.boxpoint.Fragment;

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
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.view.FontEditTextViewRegular;


public class NewShopsFragment extends android.app.Fragment {

    View view;
    ImageView back ;
    byte[] imageLoad;
    FontEditTextViewRegular companyName,email,password,userName,phoneNumber,notes;
    ImageView image;

    public static NewShopsFragment newInstance() {
        NewShopsFragment fragment = new NewShopsFragment();

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
        view = inflater.inflate(R.layout.fragment_new_shops, container, false);
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



        return view ;
    }

    public void bindView (){

        back =view.findViewById(R.id.back);
        companyName=view.findViewById(R.id.companyName);
        phoneNumber= view.findViewById(R.id.phoneNumber);
        userName =view.findViewById(R.id.userName);
        password=view.findViewById(R.id.password);
        email =view.findViewById(R.id.email);
        image =view.findViewById(R.id.imageShop);
        notes =view.findViewById(R.id.notes);

    }

    public boolean ValidationShopSignup() {


        if (TextUtils.isEmpty(companyName.getText().toString())){
            return false;
        }else if (TextUtils.isEmpty(phoneNumber.getText().toString()))
            return false;
        else if (TextUtils.isEmpty(userName.getText().toString()))
            return false;
        else if (TextUtils.isEmpty(password.getText().toString()))
            return false;
        else if (TextUtils.isEmpty(email.getText().toString()))
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

                    android.app.Fragment homeFragment = new NewUserFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frameContainerLogin, homeFragment).commit();

                    return true;

                }

                return false;
            }
        });
    }

}
