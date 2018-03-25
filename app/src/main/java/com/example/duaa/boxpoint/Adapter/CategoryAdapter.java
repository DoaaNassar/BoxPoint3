package com.example.duaa.boxpoint.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.duaa.boxpoint.Interface.CustomItemClickListener;
import com.example.duaa.boxpoint.Object.ItemObject;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.view.FontTextViewRegular;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.duaa.boxpoint.Constant.Constants.BASE_URL;
import static com.example.duaa.boxpoint.Constant.Constants.getLanguage;

/**
 * Created by AL-Qema on 11/03/18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private List<ItemObject> categoryList;
    Context context;
    CustomItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public FontTextViewRegular name;
        public CircleImageView image;
        public ImageView back ;


        public MyViewHolder(View view) {
            super(view);

            image= itemView.findViewById(R.id.circleView);
            name = itemView.findViewById(R.id.name);
            back = itemView.findViewById(R.id.back);
        }


    }

    public CategoryAdapter(Context context, List<ItemObject> categoryList, CustomItemClickListener listener) {
        this.context = context;
        this.categoryList = categoryList;
        this.listener = listener;

    }

    public List<ItemObject> getItems() {
        return categoryList;
    }


    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_item, parent, false);

        final CategoryAdapter.MyViewHolder mViewHolder = new CategoryAdapter.MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());

            }
        });

        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(CategoryAdapter.MyViewHolder holder, final int position) {
        ItemObject category = categoryList.get(position);


        if (getLanguage().equals("ar")) {
            holder.name.setText(category.getTitle_ar());

        } else {
            holder.back.setRotation(180);
            holder.name.setText(category.getTitle_en());

        }

//            if(getLanguage().equals("en")){
//            }else {
//                holder.name.setText(category.getTitle_ar());
//            }
        if (!TextUtils.isEmpty(category.getImage())){
//        Picasso.with(holder.image.getContext())
//                .load(BASE_URL + "/public/uploads/" + category.getImage()).fit().centerInside()
//                .placeholder(R.drawable.ic_launcher_foreground)
//                .error(R.drawable.ic_launcher_background)
//                .into(holder.image);
//        }

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.user);
        requestOptions.error(R.drawable.user);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
//        requestOptions.apply(RequestOptions.bitmapTransform(new CropCircleTransformation()));


        Glide.with(holder.image.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(BASE_URL + "/public/uploads/" + category.getImage()).thumbnail(.1f).into(holder.image);
    }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

}


