package com.example.mheljohn.qrsys;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mhel John on 5/3/2017.
 */

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter2(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ListItem listItem = listItems.get(position);
        Picasso.with(context).load("http://jamthesis.com/dressImages/"+listItem.getDressUrlFront())
                .placeholder(R.mipmap.ic_launcher)
                .resize(320,480)
                .into(holder.clothes);
        holder.name.setText(listItem.getItemName());
        holder.price.setText(listItem.getPrice());
        holder.stock.setText(listItem.getNoStock());
        holder.currentItem = listItems.get(position);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, price, stock;
        public ImageView clothes;
        public CardView cardView;
        public View view;
        public ListItem currentItem;
        

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.clothes_name);
            price = (TextView) itemView.findViewById(R.id.clothes_price);
            stock = (TextView) itemView.findViewById(R.id.clothes_stock);
            clothes = (ImageView) itemView.findViewById(R.id.clothes_image);
            cardView = (CardView)itemView.findViewById(R.id.cardView);

            view = itemView;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "You clicked: "+currentItem.getItemName(),
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(),ViewDetailsActivity.class);
                    intent.putExtra("nameDetail",currentItem.getItemName());
                    intent.putExtra("genderDetail",currentItem.getGender());
                    intent.putExtra("sizeDetail",currentItem.getSize());
                    intent.putExtra("ageDetail",currentItem.getAge());
                    intent.putExtra("heightDetail",currentItem.getHeight());
                    intent.putExtra("weightDetail",currentItem.getWeight());
                    intent.putExtra("chestDetail",currentItem.getChest());
                    intent.putExtra("waistDetail",currentItem.getWaist());
                    intent.putExtra("fabricDetail",currentItem.getFabricType());
                    intent.putExtra("priceDetail",currentItem.getPrice());
                    intent.putExtra("qrDetail",currentItem.getQrCode());
                    intent.putExtra("stockDetail",currentItem.getNoStock());
                    intent.putExtra("imageDetail",currentItem.getDressUrlFront());
                    intent.putExtra("locDetail",currentItem.getLocation());
                    //intent.putExtra("username",username);
                    v.getContext().startActivity(intent);
                    ((MainActivity)v.getContext()).finish();
                }
            });
        }
    }
}