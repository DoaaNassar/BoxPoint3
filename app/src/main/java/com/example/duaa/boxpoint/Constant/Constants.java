package com.example.duaa.boxpoint.Constant;

import android.content.SharedPreferences;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by AL-Qema on 06/03/18.
 */

public class Constants {

   public static final String FONTS_APP = "fonts/din-next-lt-arabic-medium.ttf";
   public static final String BASE_URL = "http://142.4.4.149/~alaa/boxpoint";
   public static final String client_secret ="iH82yp6OBpJBZaiPuMOwSElwUlKof4V2YTaDJTEQ";
   public static final String REFRESH_TOKEN = BASE_URL+"/oauth/token";

   public static final String user = BASE_URL+"/api/v1/user";
   public static final String login = BASE_URL+"/oauth/token";
   public static final String LOGOUT = BASE_URL + "/api/v1/logout";
   public static final String OAUTH_USER = BASE_URL + "/oauth/token";
   public static final String UPDATE_USER =BASE_URL +"/api/v1/updateuser";
   public static final String UPDATE_PASSWORD =BASE_URL +"/api/v1/updatepassword";
   public static final String SHOPS = BASE_URL+"/api/v1/shops/";
   public static final String RESET_PASSWORD = BASE_URL +"/api/v1/resetpassword";










   public static String getLanguage() {

      if (Locale.getDefault().getLanguage().equalsIgnoreCase("en")) {
         return "en";
      } else {
         return "ar";
      }
   }
}
