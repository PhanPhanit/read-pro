package com.project.read_pro.adapter;

import static com.project.read_pro.utils.Utils.priceConverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.read_pro.databinding.CartOrderItemBinding;
import com.project.read_pro.model.Cart;
import com.project.read_pro.model.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    List<Cart> carts;
    private CartAdapter.IncreaseOnclickListener incClickHandler;
    private CartAdapter.DecreaseOnClickListener decClickHandler;

    public CartAdapter(Context context, List<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }

    public interface IncreaseOnclickListener{
        void onClick(Cart cart, int position);
    }
    public interface DecreaseOnClickListener{
        void onClick(Cart cart, int position);
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartOrderItemBinding binding = CartOrderItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = carts.get(position);
        Product product = cart.getProduct();
        holder.binding.cartItemTitle.setText(product.getTitle());
        holder.binding.itemCartPrice.setText(priceConverter(product.getPrice() - product.getDiscount()));
        holder.binding.itemCartQuantity.setText(cart.getQuantity() + "");
        Glide.with(context)
                .load(cart.getImage())
                .into(holder.binding.cartItemImage);
    }

    @Override
    public int getItemCount() {
        if(carts == null){
            return 0;
        }
        return carts.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private CartOrderItemBinding binding;
        public CartViewHolder(CartOrderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.itemCartInc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getBindingAdapterPosition();
                    Cart cart = carts.get(position);
                    incClickHandler.onClick(cart, position);
                }
            });

            binding.itemCartDec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getBindingAdapterPosition();
                    Cart cart = carts.get(position);
                    decClickHandler.onClick(cart, position);
                }
            });


        }
    }
    public void setOnClickInc(CartAdapter.IncreaseOnclickListener incClickHandler){
        this.incClickHandler = incClickHandler;
    }
    public void setOnClickDec(CartAdapter.DecreaseOnClickListener decClickHandler){
        this.decClickHandler = decClickHandler;
    }
}
