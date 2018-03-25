package com.example.duaa.boxpoint.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.duaa.boxpoint.R;

import static android.content.Context.MODE_PRIVATE;


public class NewUserFragment extends android.app.Fragment {

    View view;
    SharedPreferences prefs;
    FrameLayout addClient, addShop;


    public static NewUserFragment newInstance() {
        NewUserFragment fragment = new NewUserFragment();

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
        view = inflater.inflate(R.layout.fragment_new_user, container, false);

        prefs = getActivity().getSharedPreferences("dbShared", MODE_PRIVATE);

        bindView();
        addClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment con = new AddClientFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainerLogin, con).commit();
            }
        });

        addShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment con = new NewShopsFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainerLogin, con).commit();

            }
        });

        // Inflate the layout for this fragment
        return view;
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

                    android.app.Fragment homeFragment = new LoginFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frameContainerLogin, homeFragment).commit();

                    return true;

                }

                return false;
            }
        });
    }

    public void bindView() {

        addClient = view.findViewById(R.id.addClient);
        addShop = view.findViewById(R.id.addShop);


    }


}
