package com.project.read_pro.adapter;

import static com.project.read_pro.utils.Utils.numberWithComma;
import static com.project.read_pro.utils.Utils.priceConverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.read_pro.databinding.SaveProductItemBinding;
import com.project.read_pro.model.Product;
import com.project.read_pro.model.SaveProduct;

import java.util.List;

public class SaveProductAdapter extends RecyclerView.Adapter<SaveProductAdapter.SaveProductViewHolder> {
    private Context context;
    private List<SaveProduct> saveProducts;
    private SaveProductAdapter.SaveProductRemoveOnClickHandler saveProductRemoveOnClickHandler;
    private SaveProductAdapter.SaveProductOnClickHandler saveProductOnClickHandler;

    public SaveProductAdapter(Context context, List<SaveProduct> saveProducts) {
        this.context = context;
        this.saveProducts = saveProducts;
    }

    public interface SaveProductRemoveOnClickHandler{
        void onClick(SaveProduct saveProduct, int position);
    }
    public interface SaveProductOnClickHandler{
        void onClick(SaveProduct saveProduct, int position);
    }

    @NonNull
    @Override
    public SaveProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SaveProductItemBinding binding = SaveProductItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SaveProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SaveProductViewHolder holder, int position) {
        SaveProduct saveProduct = saveProducts.get(position);
        Product product = saveProduct.getProduct();
        Glide.with(context)
                .load(product.getImage()[0])
                .into(holder.binding.saveProductImage);
        holder.binding.saveProductTitle.setText(product.getTitle());
        holder.binding.saveProductAuthor.setText(product.getAuthor());
        holder.binding.saveProductRatingStar.setRating((float) product.getAverageRating());
        holder.binding.saveProductNumRating.setText(numberWithComma(product.getNumOfReviews()));
        holder.binding.saveProductNumSold.setText(numberWithComma(product.getSold()));
        holder.binding.saveProductPrice.setText(priceConverter(product.getPrice() - product.getDiscount()));
        holder.binding.saveProductDescription.setText(product.getDescription());
    }

    @Override
    public int getItemCount() {
        if(saveProducts == null){
            return 0;
        }
        return saveProducts.size();
    }

    public class SaveProductViewHolder extends RecyclerView.ViewHolder {
        private SaveProductItemBinding binding;
        public SaveProductViewHolder(SaveProductItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.saveProductRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getBindingAdapterPosition();
                    SaveProduct saveProduct = saveProducts.get(position);
                    saveProductRemoveOnClickHandler.onClick(saveProduct, position);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getBindingAdapterPosition();
                    SaveProduct saveProduct = saveProducts.get(position);
                    saveProductOnClickHandler.onClick(saveProduct, position);
                }
            });
        }
    }

    public void setOnClickSaveProductRemove(SaveProductAdapter.SaveProductRemoveOnClickHandler saveProductRemoveOnClickHandler){
        this.saveProductRemoveOnClickHandler = saveProductRemoveOnClickHandler;
    }
    public void setOnClickSaveProduct(SaveProductAdapter.SaveProductOnClickHandler saveProductOnClickHandler){
        this.saveProductOnClickHandler = saveProductOnClickHandler;
    }
}
