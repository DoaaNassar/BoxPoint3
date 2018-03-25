package com.example.duaa.boxpoint.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.duaa.boxpoint.Constant.Constants;
import com.example.duaa.boxpoint.Object.FavModel;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.view.FontTextViewRegular;

import java.util.List;

/**
 * Created by AL-Qema on 11/03/18.
 */

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.MyViewHolder> {

    private List<FavModel> categoryList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public FontTextViewRegular name;
        public ImageView image,back;




        public MyViewHolder(View view) {
            super(view);

            image =itemView.findViewById(R.id.imageCategory);
            name = itemView.findViewById(R.id.name);
            back = itemView.findViewById(R.id.back);
        }


    }

    public FavAdapter(Context context, List<FavModel> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    public List<FavModel> getItems() {
        return categoryList;
    }


    @Override
    public FavAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_item, parent, false);

        final FavAdapter.MyViewHolder mViewHolder = new FavAdapter.MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });
        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(FavAdapter.MyViewHolder holder, final int position) {
        FavModel category = categoryList.get(position);
//            if(getLanguage().equals("en")){
        holder.name.setText(category.getName());
        if (Constants.getLanguage().equals("en")) {
            holder.back.setRotation(180);
        }

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


