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

public class NewUsersFragment extends android.app.Fragment {
    ImageView back;
    View view;

    public static NewUsersFragment newInstance() {
        NewUsersFragment fragment = new NewUsersFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_users, container, false);
        bindView();
        if (Constants.getLanguage().equals("en")) {
            back.setRotation(180);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



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
}
