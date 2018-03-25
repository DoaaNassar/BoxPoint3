package com.example.duaa.boxpoint.Fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.duaa.boxpoint.R;

import static com.example.duaa.boxpoint.Constant.Constants.getLanguage;


public class AboutFragment extends android.app.Fragment {
    View view;
    ImageView back;
    LinearLayout arabicBox;
    LinearLayout englishBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about, container, false);
        bindView();

        if (getLanguage().equals("ar")) {

            arabicBox.setVisibility(View.VISIBLE);
            englishBox.setVisibility(View.GONE);


        } else {
            arabicBox.setVisibility(View.GONE);
            englishBox.setVisibility(View.VISIBLE);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.app.Fragment con = new HomeFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, con).commit();


            }
        });


        return view;
    }

    public void bindView() {
        back =view.findViewById(R.id.back);
        arabicBox = view.findViewById(R.id.boxArabic);
        englishBox = view.findViewById(R.id.boxEnglish);


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
