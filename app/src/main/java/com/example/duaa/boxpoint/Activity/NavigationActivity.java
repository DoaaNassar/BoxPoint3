package com.example.duaa.boxpoint.Activity;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import android.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.duaa.boxpoint.Application.ApplicationController;
import com.example.duaa.boxpoint.Fragment.AboutFragment;
import com.example.duaa.boxpoint.Fragment.AddComplaintFragment;
import com.example.duaa.boxpoint.Fragment.EditProfileClientFragment;
import com.example.duaa.boxpoint.Fragment.FavFragment;
import com.example.duaa.boxpoint.Fragment.HomeFragment;
import com.example.duaa.boxpoint.Fragment.PackageFragment;
import com.example.duaa.boxpoint.Interface.UniversalCallBack;
import com.example.duaa.boxpoint.Object.ResponseObject;
import com.example.duaa.boxpoint.Object.UserDataResponse;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.Model.ResponseError;
import com.example.duaa.boxpoint.API.UserAPI;
import com.example.duaa.boxpoint.view.FontTextViewRegular;

import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout main, fav, editMyProfile, packages, addComplaint ,logout;
    FontTextViewRegular aboutApp, mainText, favText, editProfileText, packagesText, addComplaintText;
    ImageView mainImage, favImage, editProfileImage, packageImage, addComplaintImage, listBtn;
    ///// header

    CircleImageView circleImageView;
    FontTextViewRegular userName, address;
    AlertDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        bindView();

        setData();
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            public void onDrawerClosed(View view) {
                setData();
                invalidateOptionsMenu(); // creates call to
            }

            public void onDrawerOpened(View drawerView) {
                setData();
                invalidateOptionsMenu(); // creates call to
            }


        };

        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        Fragment con = new HomeFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, con).commit();
        home();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment con = new HomeFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, con).commit();
                drawer.closeDrawer(GravityCompat.START);
                home();

            }
        });

        fav.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment con = new FavFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, con).commit();
                drawer.closeDrawer(GravityCompat.START);
                fav();
            }
        });

        editMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment con = new EditProfileClientFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, con).commit();
                drawer.closeDrawer(GravityCompat.START);
                editProfile();

            }
        });

        packages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment con = new PackageFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, con).commit();
                drawer.closeDrawer(GravityCompat.START);
                packages();

            }
        });

        addComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment con = new AddComplaintFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, con).commit();
                drawer.closeDrawer(GravityCompat.START);
                addComplaint();

            }
        });


        aboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment con = new AboutFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, con).commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            moveTaskToBack(true);
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void bindView() {

        main = findViewById(R.id.home);
        fav = findViewById(R.id.fav);
        editMyProfile = findViewById(R.id.editProfile);
        packages = findViewById(R.id.packageMe);
        addComplaint = findViewById(R.id.addComplaint);
        aboutApp = findViewById(R.id.aboutApp);
        logout = findViewById(R.id.out);

        mainText = findViewById(R.id.textHome);
        favText = findViewById(R.id.favText);
        editProfileText = findViewById(R.id.textEditProfile);
        packagesText = findViewById(R.id.textPackageMe);
        addComplaintText = findViewById(R.id.textAddComplaint);

        mainImage = findViewById(R.id.imageHome);
        favImage = findViewById(R.id.favImage);
        editProfileImage = findViewById(R.id.imageEditProfile);
        packageImage = findViewById(R.id.imagePackageMe);
        addComplaintImage = findViewById(R.id.imageAddComplaint);
        listBtn = findViewById(R.id.listBtn);

        userName = findViewById(R.id.userName);
        circleImageView = findViewById(R.id.circleView);
        address = findViewById(R.id.address);

        dialog = new SpotsDialog(this, R.style.CustomLogout);



    }

    private void home() {

        main.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        fav.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
        editMyProfile.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
        packages.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
        addComplaint.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
    }

    private void fav() {

        main.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
        fav.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        editMyProfile.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
        packages.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
        addComplaint.setBackgroundColor(getResources().getColor(R.color.silverNavigation));

    }

    private void editProfile() {

        main.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
        fav.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
        editMyProfile.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        packages.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
        addComplaint.setBackgroundColor(getResources().getColor(R.color.silverNavigation));

    }

    private void packages() {

        main.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
        fav.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
        editMyProfile.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
        packages.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        addComplaint.setBackgroundColor(getResources().getColor(R.color.silverNavigation));

    }

    private void addComplaint() {

        main.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
        fav.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
        editMyProfile.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
        packages.setBackgroundColor(getResources().getColor(R.color.silverNavigation));
        addComplaint.setBackgroundColor(getResources().getColor(R.color.colorAccent));

    }

    public void Logout(){
        Logout(ApplicationController.getInstance().token());

    }

   public void setData (){
       UserDataResponse user = ApplicationController.getInstance().getLoginUser();
       userName.setText(user.getUsername());
       address.setText(user.getAddress_address());
   }

    public void Logout(final String token) {
        dialog.show();
        new UserAPI().Logout(token , new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ResponseObject responseObject = (ResponseObject) result;

                if (responseObject != null) {
                    ApplicationController.getInstance().userLogin(new UserDataResponse());
                    ApplicationController.getInstance().login_token("");
                    NavigationActivity.this.finish();
                    Intent intent = new Intent(NavigationActivity.this ,Login.class);
                    startActivity(intent);
                    dialog.hide();
                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
//                    Alerter.create(LoginActivity.this)
//                            .setText(responseError.getMessage())
//                            .hideIcon()
//                            .setBackgroundColorRes(R.color.colorPrimary)
//                            .show();
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
//                Alerter.create(LoginActivity.this)
//                        .setText(message)
//                        .hideIcon()
//                        .setBackgroundColorRes(R.color.colorPrimary)
//                        .show();
            }
        });
    }


}
