package com.prokhorov.styleruhw4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {
    private final ArrayList<Product> goods = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.goods_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = goods.get(position);
        String title = product.getTitle();
        int price = product.getPrice();
        String imageUrl = product.getImgUrl();
        String emptyImgHolder = "http://askon-agro.ru/assets/images/no-photo.png";

        holder.titleTV.setText(title);
        holder.priceTV.setText(String.valueOf(price));
        try{
            Glide
                    .with(holder.imageView.getContext())
                    .load(imageUrl)
                    .into(holder.imageView);
        } catch(Exception e){
            Glide
                    .with(holder.imageView.getContext())
                    .load(emptyImgHolder)
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }

    public void replaceGoods(ArrayList<Product> goods) {
        this.goods.clear();
        this.goods.addAll(goods);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView titleTV;
        @BindView(R.id.price)
        TextView priceTV;
        @BindView(R.id.image)
        ImageView imageView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
