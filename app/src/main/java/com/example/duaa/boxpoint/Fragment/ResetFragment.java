package com.example.duaa.boxpoint.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duaa.boxpoint.Interface.UniversalCallBack;
import com.example.duaa.boxpoint.Object.ResponseObject;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.API.UserAPI;
import com.example.duaa.boxpoint.view.FontEditTextViewRegular;

public class ResetFragment extends android.app.Fragment {



    View view ;
    FontEditTextViewRegular email ;


    public static ResetFragment newInstance() {
        ResetFragment fragment = new ResetFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reset, container, false);
        bindView();

        view.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ResetPassword(email.getText().toString());

            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void bindView (){

        email = view.findViewById(R.id.email);
    }
    public void ResetPassword(final String email){
//        dialog.show();
        new UserAPI().ResetPassword(email,new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseObject responseObject = (ResponseObject) result;
                if(responseObject.isStatus()){
//
                }else {
//
                }

            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
//
                }
            }

            @Override
            public void onFinish() {
//
            }

            @Override
            public void OnError(String message) {
            }
        });
    }



}
