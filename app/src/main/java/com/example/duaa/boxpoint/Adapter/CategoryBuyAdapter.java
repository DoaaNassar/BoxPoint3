package com.example.duaa.boxpoint.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.duaa.boxpoint.Interface.CustomItemClickListener;
import com.example.duaa.boxpoint.Object.CategoryBuyModel;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.view.FontTextViewRegular;

import java.util.List;

import static com.example.duaa.boxpoint.Constant.Constants.getLanguage;

/**
 * Created by AL-Qema on 13/03/18.
 */

public class CategoryBuyAdapter extends RecyclerView.Adapter<CategoryBuyAdapter.MyViewHolder> {

    private List<CategoryBuyModel> categoryList;
    Context context;
    CustomItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public FontTextViewRegular name;
        public ImageView image, back;


        public MyViewHolder(View view) {
            super(view);

            image = itemView.findViewById(R.id.imageCategory);
            name = itemView.findViewById(R.id.name);
            back = itemView.findViewById(R.id.back);
        }


    }

    public CategoryBuyAdapter(Context context, List<CategoryBuyModel> categoryList, CustomItemClickListener listener) {
        this.context = context;
        this.categoryList = categoryList;
        this.listener = listener;

    }

    public List<CategoryBuyModel> getItems() {
        return categoryList;
    }


    @Override
    public CategoryBuyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);

        final CategoryBuyAdapter.MyViewHolder mViewHolder = new CategoryBuyAdapter.MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());

            }
        });

        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(CategoryBuyAdapter.MyViewHolder holder, final int position) {
        CategoryBuyModel category = categoryList.get(position);


        if (getLanguage().equals("en")) {
            holder.name.setText(category.getName());
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


