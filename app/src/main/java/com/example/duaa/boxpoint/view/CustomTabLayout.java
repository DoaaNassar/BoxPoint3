package com.example.duaa.boxpoint.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.flyco.tablayout.CommonTabLayout;

import static com.example.duaa.boxpoint.Constant.Constants.FONTS_APP;

/**
 * Created by AL-Qema on 11/03/18.
 */


public class CustomTabLayout  extends CommonTabLayout {

    private Typeface mTypeface;

    public CustomTabLayout(Context context) {
        super(context);
        init();
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTypeface = Typeface.createFromAsset(getContext().getAssets(), FONTS_APP);
    }

    public void setmTypeface(Typeface mTypeface) {
        this.mTypeface = mTypeface;
    }



    //    @Override
//    public void addTab(TabLayout.Tab tab) {
//        super.addTab(tab);
//
//        ViewGroup mainView = (ViewGroup) getChildAt(0);
//        ViewGroup tabView = (ViewGroup) mainView.getChildAt(tab.getPosition());
//
//        int tabChildCount = tabView.getChildCount();
//        for (int i = 0; i < tabChildCount; i++) {
//            View tabViewChild = tabView.getChildAt(i);
//            if (tabViewChild instanceof TextView) {
//                ((TextView) tabViewChild).setTypeface(mTypeface, Typeface.NORMAL);
//            }
//        }
//    }
}
