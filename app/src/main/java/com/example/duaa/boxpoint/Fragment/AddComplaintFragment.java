package com.example.duaa.boxpoint.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.duaa.boxpoint.Constant.Constants;
import com.example.duaa.boxpoint.R;


public class AddComplaintFragment extends android.app.Fragment {

    View view;
    ImageView back;

    public static AddComplaintFragment newInstance(String param1, String param2) {
        AddComplaintFragment fragment = new AddComplaintFragment();

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
        view = inflater.inflate(R.layout.fragment_add_complaint, container, false);
        bindView();
        if (Constants.getLanguage().equals("en")) {
            back.setRotation(180);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.Fragment homeFragment = new HomeFragment();
                FragmentManager fm = getFragmentManager();

                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, homeFragment).commit();


            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void bindView() {
        back = view.findViewById(R.id.back);

    }

    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

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


}
