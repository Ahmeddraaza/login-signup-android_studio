package com.example.crypto_app;

import android.content.Context;
import android.graphics.Color;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.majorik.sparklinelibrary.SparkLineLayout;

import java.util.ArrayList;

public class cryptoadapter extends RecyclerView.Adapter<cryptoadapter.ViewHolder> {
    private Context context;
    private ArrayList<CryptoModel> CryptoList;






    public cryptoadapter(Context context, ArrayList<CryptoModel> CryptoList) {
        this.context = context;
        this.CryptoList = CryptoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_wallet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CryptoModel crypto = CryptoList.get(position);

        Glide.with(context).load(

                "https://s2.coinmarketcap.com/static/img/coins/64x64/"+ crypto.getid() + ".png").thumbnail(Glide.with(context).load(R.drawable.bitcoin)).into(holder.imageView);


        holder.nameTextView.setText(crypto.getName());
        holder.priceTextView.setText(String.format("$%.2f", crypto.getPrice()));
        holder.priceTextView2.setText(String.format("$%.2f", crypto.getPrice()));
        if (crypto.getPercentChange24h() < 0) {
            holder.percentChangeTextView.setTextColor(Color.RED);
            holder.percentChangeTextView.setText(String.format("%.2f%%", crypto.getPercentChange24h()));
        } else {
            holder.percentChangeTextView.setText(String.format("%.2f%%", crypto.getPercentChange24h()));
            holder.percentChangeTextView.setTextColor(Color.GREEN);
        }
        // Format percent change
        holder.quantityTextView.setText(String.valueOf(crypto.getTurnover()));
        int[] array_spark = {900, 1000, 1100,1000};
        ArrayList<Integer> dummyDataList = convertToArrayList(array_spark);
        holder.sparklineLayout.setData(dummyDataList);
    }

    private ArrayList<Integer> convertToArrayList(int[] data) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int value : data) {
            arrayList.add(value);
        }
        return arrayList;
    }


    @Override
    public int getItemCount() {
        if (CryptoList != null) {
            return CryptoList.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView,priceTextView2, percentChangeTextView, quantityTextView ;
        SparkLineLayout sparklineLayout;;

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textView16);
            priceTextView = itemView.findViewById(R.id.textView17);
            priceTextView2 = itemView.findViewById(R.id.textView19);
            percentChangeTextView = itemView.findViewById(R.id.textView18);
            quantityTextView = itemView.findViewById(R.id.textView20);
            imageView = itemView.findViewById(R.id.imageView6);
            sparklineLayout = itemView.findViewById(R.id.sparklineLayout);
        }
    }

}
