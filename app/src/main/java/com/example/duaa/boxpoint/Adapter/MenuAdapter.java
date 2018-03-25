package com.example.duaa.boxpoint.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.duaa.boxpoint.Interface.CustomItemClickListener;
import com.example.duaa.boxpoint.Object.MenuObject;
import com.example.duaa.boxpoint.R;
import com.example.duaa.boxpoint.view.FontTextViewRegular;

import java.util.List;

/**
 * Created by AL-Qema on 15/03/18.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private List<MenuObject> menuItems;
    private Context context;
    private CustomItemClickListener listener ;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public FontTextViewRegular name;
        public ImageView image;





        public MyViewHolder(View view) {
            super(view);

            image =itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);

        }


    }

    public MenuAdapter(Context context, List<MenuObject> menuItems, CustomItemClickListener listener) {
        this.context = context;
        this.menuItems = menuItems;
        this.listener =listener ;
    }

    public List<MenuObject> getItems() {
        return menuItems;
    }


    @Override
    public MenuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);

        final MenuAdapter.MyViewHolder mViewHolder = new MenuAdapter.MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());

            }
        });

        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(MenuAdapter.MyViewHolder holder, final int position) {
        MenuObject menuItem = menuItems.get(position);

        holder.name.setText(menuItem.getName());
        holder.image.setImageResource(menuItem.getImage());

    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

}


