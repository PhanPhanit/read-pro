package com.project.read_pro.adapter;

import static com.project.read_pro.utils.Utils.numberWithComma;
import static com.project.read_pro.utils.Utils.priceConverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.read_pro.databinding.BestSellingItemBinding;
import com.project.read_pro.model.Product;

import java.util.List;

public class BestSellingAdapter extends RecyclerView.Adapter<BestSellingAdapter.BestSellingViewHolder> {

    private final Context mContext;
    private List<Product> products;


    public BestSellingAdapter(Context mContext, List<Product> products) {
        this.mContext = mContext;
        this.products = products;
    }

    @NonNull
    @Override
    public BestSellingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BestSellingItemBinding binding = BestSellingItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BestSellingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BestSellingViewHolder holder, int position) {
        Product product = products.get(position);
        Glide.with(mContext)
                .load(product.getImage()[0])
                .into(holder.binding.bestSellingImage);
        holder.binding.bestSellingTitle.setText(product.getTitle());
        holder.binding.bestSellingAuthor.setText(product.getAuthor());
        holder.binding.bestSellingRatingStar.setRating((float) product.getAverageRating());
        holder.binding.bestSellingNumRating.setText(numberWithComma(product.getNumOfReviews()));
        holder.binding.bestSellingNumSold.setText(numberWithComma(product.getSold()));
        holder.binding.bestSellingPrice.setText(priceConverter(product.getPrice() - product.getDiscount()));
        holder.binding.bestSellingDescription.setText(product.getDescription());
    }

    @Override
    public int getItemCount() {
        if(products == null){
            return 0;
        }
        return products.size();
    }

    public class BestSellingViewHolder extends RecyclerView.ViewHolder {
        private BestSellingItemBinding binding;
        public BestSellingViewHolder(BestSellingItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
