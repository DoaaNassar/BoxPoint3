package com.example.duaa.boxpoint.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.duaa.boxpoint.Adapter.PackageMeAdapter;
import com.example.duaa.boxpoint.Constant.Constants;
import com.example.duaa.boxpoint.Object.PackageModel;
import com.example.duaa.boxpoint.R;

import java.util.ArrayList;


public class PackageFragment extends android.app.Fragment {

    View view ;
    RecyclerView recyclerView ;
    ImageView back ;

    public static PackageFragment newInstance() {
        PackageFragment fragment = new PackageFragment();
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
        view = inflater.inflate(R.layout.fragment_package, container, false);
        bindView();


        if(Constants.getLanguage().equals("en")){
            back.setRotation(180);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.Fragment homeFragment = new HomeFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, homeFragment).commit();

            }
        });


        final ArrayList<PackageModel> arrayList =new ArrayList<>();
        arrayList.add(new PackageModel("دعاء","دعاء","200","200"));
        arrayList.add(new PackageModel("دعاء","دعاء","200","200"));
        arrayList.add(new PackageModel("دعاء","دعاء","200","200"));
        arrayList.add(new PackageModel("دعاء","دعاء","200","200"));

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        PackageMeAdapter categoryAdapter = new PackageMeAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(categoryAdapter);


        // Inflate the layout for this fragment
        return view ;
    }


    public  void bindView (){
        recyclerView = view .findViewById(R.id.packageMeList);
        back =view.findViewById(R.id.back);



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

                    android.app.Fragment homeFragment = new HomeFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frameContainer, homeFragment).commit();

                    return true;

                }

                return false;
            }
        });
    }

}
