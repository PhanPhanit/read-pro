package com.project.read_pro.adapter;

import static com.project.read_pro.utils.Utils.priceConverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.read_pro.databinding.RecommendedItemBinding;
import com.project.read_pro.model.Product;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder> {

    private final Context mContext;
    private List<Product> products;

    public RecommendedAdapter(Context mContext, List<Product> products) {
        this.mContext = mContext;
        this.products = products;
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecommendedItemBinding binding = RecommendedItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecommendedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedViewHolder holder, int position) {
        Product product = products.get(position);
        Glide.with(mContext)
                .load(product.getImage()[0])
                .into(holder.binding.recommendedImage);
        holder.binding.recommendedTitle.setText(product.getTitle());
        holder.binding.recommendedAuthor.setText(product.getAuthor());
        holder.binding.recommendedDescription.setText(product.getDescription());
        holder.binding.recommendedPrice.setText(priceConverter(product.getPrice() - product.getDiscount()));
    }

    @Override
    public int getItemCount() {
        if(products == null){
            return 0;
        }
        return products.size();
    }

    public class RecommendedViewHolder extends RecyclerView.ViewHolder {
        private RecommendedItemBinding binding;
        public RecommendedViewHolder(RecommendedItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
