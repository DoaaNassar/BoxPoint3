package com.example.duaa.boxpoint.Fragment;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.duaa.boxpoint.Adapter.CategoryAdapter;
import com.example.duaa.boxpoint.Interface.CustomItemClickListener;
import com.example.duaa.boxpoint.Interface.UniversalCallBack;
import com.example.duaa.boxpoint.Object.ItemObject;
import com.example.duaa.boxpoint.Object.ShopsModel;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.Model.ResponseError;
import com.example.duaa.boxpoint.API.UserAPI;
import com.example.duaa.boxpoint.view.FontButtonRegular;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends android.app.Fragment {

    View view;
    RecyclerView recyclerView;
    List<ItemObject> categoryList;
    CategoryAdapter categoryAdapter;
    AVLoadingIndicatorView avLoadingIndicatorView ;
    LinearLayout loading ,noInternet;
    FontButtonRegular returnLoading ;


    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();

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
        view = inflater.inflate(R.layout.fragment_category, container, false);
        bindView();

        categoryList =new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        Offer();

        noInternet.setVisibility(View.GONE);
        categoryAdapter = new CategoryAdapter(getActivity(), categoryList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                android.app.Fragment con = new OfferAvailableFragment();
                android.app.FragmentManager fm = getFragmentManager();
                android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
                Bundle args = new Bundle();
                args.putInt("number",categoryList.get(position).getId());
                con.setArguments(args);
                fragmentTransaction.replace(R.id.frameContainer, con).commit();



            }
        });

        returnLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.app.Fragment con = new CategoryFragment();
                android.app.FragmentManager fm = getFragmentManager();
                android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainerr, con).commit();

            }
        });

        recyclerView.setAdapter(categoryAdapter);


        return view;
    }

    public void bindView(){

        recyclerView = view.findViewById(R.id.categoryList);
        avLoadingIndicatorView= view.findViewById(R.id.avi);
        loading = view.findViewById(R.id.loading);
        noInternet = view.findViewById(R.id.noInternet);
        returnLoading = view.findViewById(R.id.returnLoading);



    }
    public void Offer() {
        new UserAPI().getCategory(new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ShopsModel responseCategories = (ShopsModel) result;
                if (responseCategories.isStatus()) {
                    Log.d("zzzz",responseCategories.getItems().toString()+"ggg");
                    categoryList.clear();
                    categoryList.add(responseCategories.getItems());
                    categoryAdapter.notifyDataSetChanged();
                    loading.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
                    loading.setVisibility(View.GONE);
                    noInternet.setVisibility(View.VISIBLE);



                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
                loading.setVisibility(View.GONE);
                noInternet.setVisibility(View.VISIBLE);

//                Alerter.create(getActivity())
//                        .setText(message)
//                        .hideIcon()
//                        .setBackgroundColorRes(R.color.colorPrimary)
//                        .show();


            }
        },1);
    }





}
