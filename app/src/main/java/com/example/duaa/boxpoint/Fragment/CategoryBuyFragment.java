package com.example.duaa.boxpoint.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.duaa.boxpoint.Adapter.CategoryAdapter;
import com.example.duaa.boxpoint.Adapter.CategoryBuyAdapter;
import com.example.duaa.boxpoint.Interface.CustomItemClickListener;
import com.example.duaa.boxpoint.Object.CategoryBuyModel;
import com.example.duaa.boxpoint.Object.CategoryModel;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.view.FontTextViewRegular;

import java.util.ArrayList;


public class CategoryBuyFragment extends Fragment {

    View view;
    RecyclerView recyclerView;

    ImageView back;

    public static CategoryBuyFragment newInstance(String param1, String param2) {
        CategoryBuyFragment fragment = new CategoryBuyFragment();

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

        view = inflater.inflate(R.layout.fragment_category_buy, container, false);
        bindView();

        final ArrayList<CategoryBuyModel> arrayList = new ArrayList<>();
        arrayList.add(new CategoryBuyModel("دعاء",  "200", "100"));
        arrayList.add(new CategoryBuyModel("دعاء",  "200", "100"));
        arrayList.add(new CategoryBuyModel("دعاء",  "200", "100"));
        arrayList.add(new CategoryBuyModel("دعاء",  "200", "100"));

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        CategoryBuyAdapter categoryAdapter = new CategoryBuyAdapter(getActivity(), arrayList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });

        recyclerView.setAdapter(categoryAdapter);

        return view;
    }

    public void bindView() {
        recyclerView = view.findViewById(R.id.recycleView);
        back =view.findViewById(R.id.back);

    }

}
