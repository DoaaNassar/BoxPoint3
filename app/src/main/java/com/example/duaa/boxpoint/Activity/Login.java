package com.example.duaa.boxpoint.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.duaa.boxpoint.Application.ApplicationController;
import com.example.duaa.boxpoint.Fragment.LoginFragment;
import com.example.duaa.boxpoint.R;

public class Login extends AppCompatActivity {

    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prefs = getSharedPreferences("dbShared", MODE_PRIVATE);

        Fragment con = new LoginFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameContainerLogin, con).commit();

    }

    public void language(String languageToLoad) {

        ApplicationController.getInstance().changeLang(languageToLoad);
        ApplicationController.getInstance().loadLocale();
        Intent intent = new Intent(getApplicationContext(), Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        Intent refresh = new Intent(this, Login.class);
        startActivity(refresh);
        this.finish();


    }

}
