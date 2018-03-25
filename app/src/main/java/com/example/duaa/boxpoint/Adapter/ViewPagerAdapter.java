package com.example.duaa.boxpoint.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.duaa.boxpoint.Fragment.CategoryFragment;
import com.example.duaa.boxpoint.R;

/**
 * Created by AL-Qema on 11/03/18.
 */

public class ViewPagerAdapter  extends FragmentStatePagerAdapter {

    private final int FRAGMENT_1 = 0;
    private final int FRAGMENT_2 = 1;
    private final int FRAGMENT_3 = 2;
    private final int FRAGMENT_4 = 3;
    private final int FRAGMENT_5 = 4;



    private final int COUNT = 5;
    private final Context context;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
//            case FRAGMENT_1:
//                fragment = new CategoryFragment();
//                break;
//            case FRAGMENT_2:
//                fragment = new CategoryFragment();
//                break;
//            case FRAGMENT_3:
//                fragment = new CategoryFragment();
//                break;
//
//            case FRAGMENT_4:
//                fragment = new CategoryFragment();
//                break;
//            case FRAGMENT_5:
//                fragment = new CategoryFragment();
//                break;


        }
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String fragment = null;
        switch (position) {
            case FRAGMENT_1:
                fragment = context.getString(R.string.restaurant);
                break;

            case FRAGMENT_2:
                fragment = context.getString(R.string.coffee);
                break;

            case FRAGMENT_3:
                fragment = context.getString(R.string.hotel);
                break;

            case FRAGMENT_4:
                fragment = context.getString(R.string.botec);
                break;

            case FRAGMENT_5:
                fragment = context.getString(R.string.hospital);
                break;


        }
        return fragment;
    }
}