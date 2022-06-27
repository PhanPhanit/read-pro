package com.project.read_pro.adapter;


import static com.project.read_pro.utils.Utils.priceConverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.read_pro.R;
import com.project.read_pro.databinding.NewArrivalItemBinding;
import com.project.read_pro.model.Product;
import com.project.read_pro.storage.LoginUtils;
import com.project.read_pro.utils.SaveProductUtils;
import com.project.read_pro.view.LoginActivity;

import java.util.List;

public class NewArrivalAdapter extends RecyclerView.Adapter<NewArrivalAdapter.NewArrivalViewHolder> {

    private Context context;
    private List<Product> products;
    private Product currentProduct;
    private NewArrivalAdapter.NewArrivalAdapterOnclickHandler clickHandler;
    private NewArrivalAdapter.SaveProductOnClickHandler saveProductClickHandler;

    public NewArrivalAdapter(Context context, List<Product> products){
        this.context = context;
        this.products = products;
    }


    /**
     * Handle item click listener
     */
    public interface NewArrivalAdapterOnclickHandler{
        void onClick(Product product);
    }
    public interface SaveProductOnClickHandler{
        void onClick(Product product, int position);
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

        if(LoginUtils.getInstance(context).isLoggedIn()){
            if(SaveProductUtils.getInstance().isProductSaved(product)){
                holder.binding.newArrivalSave.setImageResource(R.drawable.ic_bookmark_fill);
            }else{
                holder.binding.newArrivalSave.setImageResource(R.drawable.ic_bookmark);
            }
        }
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    currentProduct = products.get(position);
                    clickHandler.onClick(currentProduct);
                }
            });
            binding.newArrivalSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    Product product = products.get(position);
                    saveProductClickHandler.onClick(product, position);
                }
            });
        }
    }


    public void setOnclickNewArrival(NewArrivalAdapter.NewArrivalAdapterOnclickHandler clickHandler){
        this.clickHandler = clickHandler;
    }

    public void setOnClickSaveProduct(NewArrivalAdapter.SaveProductOnClickHandler saveProductClickHandler){
        this.saveProductClickHandler = saveProductClickHandler;
    }

}
