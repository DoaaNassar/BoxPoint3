package com.example.duaa.boxpoint.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.duaa.boxpoint.Adapter.OfferAdapter;
import com.example.duaa.boxpoint.Interface.CustomItemClickListener;
import com.example.duaa.boxpoint.Interface.UniversalCallBack;
import com.example.duaa.boxpoint.Object.ItemObject;
import com.example.duaa.boxpoint.Object.ProductObject;
import com.example.duaa.boxpoint.Object.ShopsModel;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.Model.ResponseError;
import com.example.duaa.boxpoint.API.UserAPI;
import com.example.duaa.boxpoint.view.FontButtonRegular;
import com.example.duaa.boxpoint.view.FontTextViewRegular;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import static com.example.duaa.boxpoint.Constant.Constants.getLanguage;


public class OfferAvailableFragment extends android.app.Fragment {

    View view;
    FontButtonRegular buyBtn;
    ImageView back, notification ,call ,share ,fav;
    RecyclerView offerList;
    List<ProductObject> shopsList;
    OfferAdapter offerAdapter;
    FontTextViewRegular title ,email,country;
    ItemObject itemObject ;
    int number;
    LinearLayout linearLayout ;
    AVLoadingIndicatorView avLoadingIndicatorView ;
    /// dialog
    FontTextViewRegular  price,discount,countPoint,description
            ,discount1 ,discount2,discount3,discount4,discount5 ,discountPrice  ;
    FontButtonRegular discountBtn ,categoryName;



    public static OfferAvailableFragment newInstance(String param1, String param2) {
        OfferAvailableFragment fragment = new OfferAvailableFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        shopsList = new ArrayList<>();
        number = getArguments().getInt("number");
        itemObject =new ItemObject();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_offer_available, container, false);
        bindView();
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.app.Fragment con = new BuyPackageFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, con).commit();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.app.Fragment homeFragment = new HomeFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.frameContainer, homeFragment).commit();

            }
        });



        Offer();

        offerList.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        offerAdapter = new OfferAdapter(getActivity(), shopsList, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (!TextUtils.isEmpty(shopsList.get(position).getIs_featured()))
                    if (shopsList.get(position).getIs_featured().equals("1"))
                        showDialog(getActivity(),shopsList.get(position));
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "tel:" + itemObject.getPhone() ;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                startActivity(intent); ///// open permission from Android Marshmallow and upper
            }
        });
        offerList.setAdapter(offerAdapter);


        if (getLanguage().equals("en")) {
            back.setRotation(180);
        }


        return view;
    }

    public void showDialog(Activity activity,ProductObject productObject) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog);
        price = dialog.findViewById(R.id.price);
        countPoint =dialog.findViewById(R.id.numberPoint);
        discount = dialog .findViewById(R.id.disCount);
        discount1= dialog.findViewById(R.id.disCount_1);
        discount2= dialog.findViewById(R.id.disCount_2);
        discount3=dialog.findViewById(R.id.disCount_3);
//        discount5=dialog.findViewById(R.id.disCount_5);
        discount4=dialog.findViewById(R.id.disCount_4);
        discountPrice =dialog.findViewById(R.id.disCountPrice);
        discountBtn =dialog.findViewById(R.id.disCountBtn);
        categoryName=dialog.findViewById(R.id.nameCategory);
        description =dialog.findViewById(R.id.description);

        if (getLanguage().equals("ar")){
            categoryName.setText(productObject.getTitle_ar());
            description.setText(productObject.getDescription_ar());
        }
        else {
            categoryName.setText(productObject.getTitle_en());
            description.setText(productObject.getDescription_en());
        }

        price.setText(productObject.getPrice_by_points()+"");
        discountPrice.setText(productObject.getPrice());
        char [] characters = (productObject.getDiscount_code()+"").toCharArray();

        discount1.setText(characters[0]+"");
        discount2.setText(characters[1]+"");
        discount3.setText(characters[2]+"");
        discount4.setText(characters[3]+"");

        dialog.show();

    }


    public void bindView() {

        buyBtn = view.findViewById(R.id.buyBtn);
        back = view.findViewById(R.id.back);
        offerList = view.findViewById(R.id.offerList);
        title =view.findViewById(R.id.titleText);
        email =view.findViewById(R.id.email);
        country=view.findViewById(R.id.address);
        linearLayout = view.findViewById(R.id.layoutCon);
        avLoadingIndicatorView = view.findViewById(R.id.avi);
        call = view.findViewById(R.id.call);

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

    public void Offer() {
        new UserAPI().getCategory(new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {
                ShopsModel responseCategories = (ShopsModel) result;
                if (responseCategories.isStatus()) {
                    Log.d("zzzz", responseCategories.getItems().toString() + "ggg");
                    shopsList.clear();
                    itemObject = responseCategories.getItems();
                    shopsList.addAll(responseCategories.getItems().getProducts());
                    offerAdapter.notifyDataSetChanged();
                    linearLayout.setVisibility(View.VISIBLE);
                    avLoadingIndicatorView.setVisibility(View.GONE);

                    if (getLanguage().equals("en")) {
                        title.setText(itemObject.getTitle_en());
                    }
                    else if (getLanguage().equals("ar")) {
                        title.setText(itemObject.getTitle_ar());
                    }

                    email.setText(itemObject.getEmail());
                    country.setText(itemObject.getAddress_address());

                }
            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
                    avLoadingIndicatorView.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void OnError(String message) {
                avLoadingIndicatorView.setVisibility(View.GONE);

//                Alerter.create(getActivity())
//                        .setText(message)
//                        .hideIcon()
//                        .setBackgroundColorRes(R.color.colorPrimary)
//                        .show();


            }
        }, number);
    }
}
