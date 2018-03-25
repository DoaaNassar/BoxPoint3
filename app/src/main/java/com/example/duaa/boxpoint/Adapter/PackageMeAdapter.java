package com.example.duaa.boxpoint.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.duaa.boxpoint.Object.PackageModel;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.view.FontTextViewRegular;

import java.util.List;

/**
 * Created by AL-Qema on 11/03/18.
 */

public class PackageMeAdapter extends RecyclerView.Adapter<PackageMeAdapter.MyViewHolder> {

        private List<PackageModel> categoryList;
        Context context;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public FontTextViewRegular name;
            public ImageView image;



            public MyViewHolder(View view) {
                super(view);

                image=itemView.findViewById(R.id.image);
                name = itemView.findViewById(R.id.name);
            }


        }

        public PackageMeAdapter(Context context, List<PackageModel> categoryList) {
            this.context = context;
            this.categoryList = categoryList;
        }

        public List<PackageModel> getItems() {
            return categoryList;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_recycle, parent, false);

            final MyViewHolder mViewHolder = new MyViewHolder(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                }
            });
            return mViewHolder;
        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            PackageModel category = categoryList.get(position);

//            if(getLanguage().equals("en")){
//                holder.name.setText(category.getTitle_en());
//            }else {
//                holder.name.setText(category.getTitle_ar());
//            }
//            Picasso.with(holder.image.getContext())
//                    .load(category.getUrlicon()).fit().centerInside()
//                    .placeholder(R.drawable.placeholder)
//                    .error(R.drawable.placeholder)
//                    .into(holder.image);



        }

        @Override
        public int getItemCount() {
            return categoryList.size();
        }

    }


