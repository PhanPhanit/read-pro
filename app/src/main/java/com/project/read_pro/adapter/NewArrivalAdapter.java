package com.project.read_pro.adapter;


import static com.project.read_pro.utils.Utils.priceConverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.read_pro.databinding.NewArrivalItemBinding;
import com.project.read_pro.model.Product;

import java.util.List;

public class NewArrivalAdapter extends RecyclerView.Adapter<NewArrivalAdapter.NewArrivalViewHolder> {

    private Context context;
    private List<Product> products;

    public NewArrivalAdapter(Context context, List<Product> products){
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public NewArrivalAdapter.NewArrivalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewArrivalItemBinding newArrivalItemBinding = NewArrivalItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NewArrivalViewHolder(newArrivalItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewArrivalAdapter.NewArrivalViewHolder holder, int position) {
        Product product = products.get(position);
        Glide.with(context)
                .load(product.getImage()[0])
                .into(holder.binding.newArrivalImage);
        holder.binding.newArrivalTitle.setText(product.getTitle());
        holder.binding.newArrivalAuthor.setText(product.getAuthor());
        holder.binding.newArrivalDescription.setText(product.getDescription());
        holder.binding.newArrivalPrice.setText(priceConverter(product.getPrice() - product.getDiscount()));
    }

    @Override
    public int getItemCount() {
        if(products == null){
            return 0;
        }
        return products.size();
    }

    public class NewArrivalViewHolder extends RecyclerView.ViewHolder {
        private final NewArrivalItemBinding binding;
        public NewArrivalViewHolder(NewArrivalItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
