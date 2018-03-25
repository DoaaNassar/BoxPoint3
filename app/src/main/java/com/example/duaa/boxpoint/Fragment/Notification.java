package com.example.duaa.boxpoint.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.duaa.boxpoint.Adapter.NotificationAdapter;
import com.example.duaa.boxpoint.Constant.Constants;
import com.example.duaa.boxpoint.Interface.CustomItemClickListener;
import com.example.duaa.boxpoint.Object.NotificationModel;
import com.example.duaa.boxpoint.R;

import java.util.ArrayList;


public class Notification extends android.app.Fragment {
    View view;
    RecyclerView recyclerView;
    ImageView back ;

    public static Notification newInstance(String param1, String param2) {
        Notification fragment = new Notification();
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
        view = inflater.inflate(R.layout.fragment_notification, container, false);

        bindView();

        if(Constants.getLanguage().equals("en")){
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
        final ArrayList<NotificationModel> categoryList = new ArrayList<>();
        categoryList.add(new NotificationModel("دعاء", "duaa", "200"));
        categoryList.add(new NotificationModel("دعاء", "duaa", "200"));
        categoryList.add(new NotificationModel("دعاء", "duaa", "200"));
        categoryList.add(new NotificationModel("دعاء", "duaa", "200"));



        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        NotificationAdapter categoryAdapter = new NotificationAdapter(getActivity(), categoryList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {


            }
        });

        recyclerView.setAdapter(categoryAdapter);

        return view;

    }

    public void bindView() {
        recyclerView = view.findViewById(R.id.notificationList);
        back =view.findViewById(R.id.back);
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

}
