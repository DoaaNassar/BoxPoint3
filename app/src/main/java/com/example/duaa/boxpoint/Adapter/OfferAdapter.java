package com.example.duaa.boxpoint.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.duaa.boxpoint.Interface.CustomItemClickListener;
import com.example.duaa.boxpoint.Object.ProductObject;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.view.FontTextViewRegular;

import java.util.List;

import static com.example.duaa.boxpoint.Constant.Constants.getLanguage;

/**
 * Created by AL-Qema on 13/03/18.
 */

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.MyViewHolder> {

    private List<ProductObject> categoryList;
    Context context;
    CustomItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public FontTextViewRegular name,description;
        public ImageView image, back, style;


        public MyViewHolder(View view) {
            super(view);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            back = itemView.findViewById(R.id.back);
            style = itemView.findViewById(R.id.style);
            description= itemView.findViewById(R.id.description);
        }


    }

    public OfferAdapter(Context context, List<ProductObject> categoryList, CustomItemClickListener listener) {
        this.context = context;
        this.categoryList = categoryList;
        this.listener = listener;

    }

    public List<ProductObject> getItems() {
        return categoryList;
    }


    @Override
    public OfferAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_search, parent, false);

        final OfferAdapter.MyViewHolder mViewHolder = new OfferAdapter.MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());

            }
        });

        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(OfferAdapter.MyViewHolder holder, final int position) {
        ProductObject offerModel = categoryList.get(position);

        if (getLanguage().equals("en")) {
            holder.back.setRotation(180);
            holder.name.setText(offerModel.getTitle_en());
            holder.description.setText(offerModel.getDescription_en());

        } else {
            holder.name.setText(offerModel.getTitle_ar());
            holder.description.setText(offerModel.getDescription_ar());
        }

        //
        if (!TextUtils.isEmpty(offerModel.getIs_featured())) {
            if (offerModel.getIs_featured().equals("1")) {
                holder.style.setVisibility(View.GONE);

            } else
                holder.style.setVisibility(View.VISIBLE);
        }else {
                holder.style.setVisibility(View.VISIBLE);

        }


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


