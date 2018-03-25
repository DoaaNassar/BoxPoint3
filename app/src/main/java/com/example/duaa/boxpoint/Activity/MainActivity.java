package com.example.duaa.boxpoint.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.example.duaa.boxpoint.Application.ApplicationController;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.view.FontButtonRegular;

import static com.example.duaa.boxpoint.Constant.Constants.getLanguage;

public class MainActivity extends AppCompatActivity {

    FontButtonRegular arabicLang, englishLang;
    String languageToLoad = "en"; // your language
    SharedPreferences prefs ;
    SharedPreferences.Editor editor;
    LinearLayout arabicBox;
    LinearLayout englishBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arabicBox = findViewById(R.id.boxArabic);
        englishBox=findViewById(R.id.boxEnglish);
        prefs = getSharedPreferences("dbShared", MODE_PRIVATE);
        editor = getSharedPreferences("dbShared", MODE_PRIVATE).edit();

        bindView();

        Intent intent = new Intent(MainActivity.this ,NavigationActivity.class);
        if (!TextUtils.isEmpty(ApplicationController.getInstance().token())){
            MainActivity.this.finish();
            startActivity(intent);
//            languageNa(languageToLoad);


        }

        if (getLanguage().equals("ar")) {

            arabicLang.setBackgroundResource(R.drawable.button_not_select_english);
            englishLang.setBackgroundResource(R.drawable.button_select_arabic);
            languageToLoad="ar";
            arabicBox.setVisibility(View.VISIBLE);
            englishBox.setVisibility(View.GONE);


        }else{
            arabicBox.setVisibility(View.GONE);
            englishBox.setVisibility(View.VISIBLE);
        }



        arabicLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArabicLanguage();


            }
        });

        englishLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EnglishLanguage();


            }
        });

    }

    public void bindView() {
        arabicLang = findViewById(R.id.arabicLang);
        englishLang = findViewById(R.id.englishLang);

    }

    public void ArabicLanguage() {

        if (getLanguage().equals("ar")) {

            arabicLang.setBackgroundResource(R.drawable.button_select_english);
            englishLang.setBackgroundResource(R.drawable.button_not_not_select_arabic);

        } else {

            englishLang.setBackgroundResource(R.drawable.button_not_select_english);
            arabicLang.setBackgroundResource(R.drawable.button_select_arabic);

        }

        languageToLoad = "ar";

        editor.putString("languageSS",languageToLoad);
        editor.apply();
        language(languageToLoad);

        Intent refresh = new Intent(this, Login.class);
        startActivity(refresh);
    }

    public void EnglishLanguage() {

        if (getLanguage().equals("ar")) {

            arabicLang.setBackgroundResource(R.drawable.button_not_select_english);
            englishLang.setBackgroundResource(R.drawable.button_select_arabic);

        } else {

            englishLang.setBackgroundResource(R.drawable.button_select_english);
            arabicLang.setBackgroundResource(R.drawable.button_not_not_select_arabic);

        }

        languageToLoad = "en";


        editor.putString("languageSS",languageToLoad);
        editor.apply();

        language(languageToLoad);

        Intent refresh = new Intent(this, Login.class);
        startActivity(refresh);
        this.finish();


    }
    public  void  language(String languageToLoad){

        ApplicationController.getInstance().changeLang(languageToLoad);
        ApplicationController.getInstance().loadLocale();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        this.finish();

    }



}
