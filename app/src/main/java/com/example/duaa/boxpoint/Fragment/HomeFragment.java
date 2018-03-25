package com.example.duaa.boxpoint.Fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.duaa.boxpoint.Activity.SearchActivity;
import com.example.duaa.boxpoint.Object.TabEntity;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.view.CustomTabLayout;
import com.example.duaa.boxpoint.view.FontButtonRegular;
import com.example.duaa.boxpoint.view.FontTextViewRegular;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;


public class HomeFragment extends android.app.Fragment {

    View view;
    ImageView notificationBtn ,search,listBtn;
    int selectedPosition =0 ;
    FontTextViewRegular country ;
    String [] array;



    ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        array = new String[]{"الكويت","فلسطين", "الاردن", "السعودية"};


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        bindView();

        CustomTabLayout tabLayout = view.findViewById(R.id.mTabLayout_1);
        mTabEntities.add(new TabEntity(getString(R.string.restaurant), 0, 0));
        mTabEntities.add(new TabEntity(getString(R.string.coffee), 0, 0));
        mTabEntities.add(new TabEntity(getString(R.string.hotel), 0, 0));
        mTabEntities.add(new TabEntity(getString(R.string.botec), 0, 0));
        mTabEntities.add(new TabEntity(getString(R.string.hospital), 0, 0));

        tabLayout.setTabData(mTabEntities);

        Fragment con = new CategoryFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameContainerr, con).commit();

        final DrawerLayout drawer = (DrawerLayout)getActivity().findViewById(R.id.drawer_layout);

        view.findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer.openDrawer(GravityCompat.START);

            }
        });

        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                if (position == 0) {

                Fragment con = new CategoryFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainerr, con).commit();

//                } else {
//
//
//                }
            }

            @Override
            public void onTabReselect(int position) {
//                if (position == 0) {

                Fragment con = new CategoryFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainerr, con).commit();

//                } else {
//
//
//                }
            }
        });
        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment con = new Notification();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, con).commit();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {///

                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);

            }
        });


        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog =createListFile(array);
                dialog.show();
                country.setText(array[selectedPosition]);

            }
        });



        return view;
    }

    public void bindView() {
        notificationBtn = view.findViewById(R.id.notification);
        search = view.findViewById(R.id.search);
        listBtn= view.findViewById(R.id.listBtn);
        country= view.findViewById(R.id.countryText);
    }


    private final Dialog createListFile(final String[] fileList ) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("");


        builder.setSingleChoiceItems(fileList, selectedPosition, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Log.d("","The wrong button was tapped: " + fileList[whichButton]);
                selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                country.setText(array[selectedPosition]);
                dialog.cancel();
            }
        });

        return builder.create();
    }

}
