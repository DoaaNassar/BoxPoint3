package com.example.duaa.boxpoint.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.duaa.boxpoint.Constant.Constants;
import com.example.duaa.boxpoint.Object.TabEntity;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.view.CustomTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class BuyPackageFragment extends android.app.Fragment {

    View view ;
    ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    CustomTabLayout tabLayout;
    ImageView back ;

    public static BuyPackageFragment newInstance() {
        BuyPackageFragment fragment = new BuyPackageFragment();
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
        view =inflater.inflate(R.layout.fragment_buy_package, container, false);

        bindView();

        if (Constants.getLanguage().equals("en")) {
            back.setRotation(180);
        }

        mTabEntities.add(new TabEntity(getString(R.string.restaurant), 0, 0));
        mTabEntities.add(new TabEntity(getString(R.string.coffee), 0, 0));
        mTabEntities.add(new TabEntity(getString(R.string.hotel), 0, 0));
        mTabEntities.add(new TabEntity(getString(R.string.botec), 0, 0));
        mTabEntities.add(new TabEntity(getString(R.string.hospital), 0, 0));

        tabLayout.setTabData(mTabEntities);

        Fragment con = new CategoryBuyFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer1, con).commit();


        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                if (position == 0) {

                Fragment con = new CategoryBuyFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer1, con).commit();

//                } else {
//
//
//                }
            }

            @Override
            public void onTabReselect(int position) {
//                if (position == 0) {

                Fragment con = new CategoryBuyFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer1, con).commit();

//                } else {
//
//
//                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.app.Fragment homeFragment = new OfferAvailableFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, homeFragment).commit();
            }
        });
        // Inflate the layout for this fragment
        return view ;
    }

    public void bindView (){

        tabLayout = view.findViewById(R.id.mTabLayout_1);
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

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                    android.app.Fragment homeFragment = new OfferAvailableFragment();
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
