package com.example.duaa.boxpoint.Application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.example.duaa.boxpoint.Activity.Login;
import com.example.duaa.boxpoint.Object.ResponseToken;
import com.example.duaa.boxpoint.Object.User;
import com.example.duaa.boxpoint.Object.UserDataResponse;
import com.example.duaa.boxpoint.API.SharedPrefSingleton;
import com.example.duaa.boxpoint.API.VolleySingleton;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

import io.realm.Realm;

/**
 * Created by AL-Qema on 08/03/18.
 */

public class  ApplicationController extends Application {

    private static ApplicationController mInstance;
    private static Context context;
    Locale myLocale;
    SharedPreferences sharedPreferences ;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        sharedPreferences = getSharedPreferences("db",MODE_PRIVATE);

        mInstance = this;
        SharedPrefSingleton.init(this);
        VolleySingleton.getInstance();

//        loadLocale();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    public static synchronized ApplicationController getInstance() {
        if (mInstance == null) {
            return mInstance = new ApplicationController();
        } else
            return mInstance;
    }


    public static byte[] getFileDataFromDrawable(Context context, int id) {
        Drawable drawable = ContextCompat.getDrawable(context, id);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

//    /**
//     * Turn drawable into byte array.
//     *
//     * @param drawable data
//     * @return byte array
//     */
//    public static byte[] getFileDataFromDrawable(Context context, Drawable drawable) {
//        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
//        return byteArrayOutputStream.toByteArray();
//    }


//    public static byte[] getStringFromInputStream(InputStream is) {
//        BufferedReader br = null;
//        StringBuilder sb = new StringBuilder();
//        byte[] bReturn = new byte[0];
//
//        String line;
//        try {
//
//            br = new BufferedReader(new InputStreamReader(is, "Big5"));
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
//            String sContent = sb.toString();
//            bReturn = sContent.getBytes();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return bReturn;
//    }

//    public static byte[] readBytes(Uri uri) throws IOException {
//        // this dynamically extends to take the bytes you read
//        InputStream inputStream = getAppContext().getContentResolver().openInputStream(uri);
//        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
//
//        // this is storage overwritten on each iteration with bytes
//        int bufferSize = 1024;
//        byte[] buffer = new byte[bufferSize];
//
//        // we need to know how may bytes were read to write them to the byteBuffer
//        int len = 0;
//        while ((len = inputStream.read(buffer)) != -1) {
//            byteBuffer.write(buffer, 0, len);
//        }
//
//        // and then we can return your byte array.
//        return byteBuffer.toByteArray();
//    }
    public static Context getAppContext() {
        return ApplicationController.context;
    }


    public User getUser() {
        return Realm.getDefaultInstance().where(User.class).findFirst();
    }

    public String token(){
        SharedPreferences sharedPreferences = getSharedPreferences("access_token",MODE_PRIVATE);
        String token = sharedPreferences.getString("access_token","");
        return token ;

    }


    public  void  login_token(String access_token){

        SharedPreferences.Editor editor = ApplicationController.getAppContext().
                getSharedPreferences("access_token", Context.MODE_PRIVATE).edit();
        editor.putString("access_token", access_token);
        editor.commit();

    }

    public void userLogin(final UserDataResponse user){

        SharedPreferences.Editor editor = ApplicationController.getAppContext().
                getSharedPreferences("access_token", Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("User", json);
        editor.commit();

    }

    public UserDataResponse getLoginUser (){
        SharedPreferences sharedPreferences = getSharedPreferences("access_token",MODE_PRIVATE);
        String response = sharedPreferences.getString("User","");
        Gson gson = new Gson();
        UserDataResponse user =gson.fromJson(response ,UserDataResponse.class);

        return user;
    }

    public void loginUser(final User user) {
//        SharedPreferences.Editor editor = ApplicationController.getAppContext().
//                getSharedPreferences("access_token", Context.MODE_PRIVATE).edit();
//        editor.putString("access_token", user.getAccess_token());
//        Gson gson = new Gson();
//        String json = gson.toJson(user);
//        editor.putString("User", json);
//        editor.commit();



        if (Realm.getDefaultInstance().where(User.class).findFirst() == null) {

            Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(user);
                }
            });
        } else {
            logout();
            loginUser(user);
        }
    }

    public void logout() {
//        Logout(ApplicationController.getInstance().getUser().getAccess_token());
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(User.class);
            }
        });
    }


    public boolean IsUserLoggedIn() {
        if (Realm.getDefaultInstance().where(User.class).findFirst() == null) {
            return false;
        } else {
            return true;
        }
    }

    public void RefreshToken(ResponseToken responseToken) {
        User toEdit = Realm.getDefaultInstance().where(User.class)
                .findFirst();
        Realm.getDefaultInstance().beginTransaction();
        toEdit.setAccess_token(responseToken.getAccess_token());
        toEdit.setRefresh_token(responseToken.getRefresh_token());
        toEdit.setExpires_in(responseToken.getExpires_in());
        toEdit.setToken_type(responseToken.getToken_type());
        Realm.getDefaultInstance().commitTransaction();
    }


    public void Logout() {
        logout();
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void loadLocale() {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "ar");
        changeLang(language);
    }

    public void changeLang(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

    }

    public void saveLocale(String lang) {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

//    public void Logout(final String token) {
//        new UserAPI().Logout(token, new UniversalCallBack() {
//            @Override
//            public void onResponse(Object UniversityListModel) {
//
//            }
//
//            @Override
//            public void onFailure(Object UniversityListModel) {
//
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//
//            @Override
//            public void OnError(String message) {
//
//            }
//        });
//    }
//

}