package com.project.read_pro.view;

import static com.project.read_pro.utils.Utils.priceConverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;

import com.project.read_pro.R;
import com.project.read_pro.adapter.CartAdapter;
import com.project.read_pro.databinding.ActivityCartBinding;
import com.project.read_pro.model.Cart;
import com.project.read_pro.response.CartAddResponse;
import com.project.read_pro.storage.LoginUtils;
import com.project.read_pro.utils.CartUtils;
import com.project.read_pro.view_model.CartViewModel;

import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import ru.nikartm.support.BadgePosition;

public class CartActivity extends AppCompatActivity {
    private ActivityCartBinding binding;
    private List<Cart> carts;
    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    private CartViewModel cartViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        carts = CartUtils.getInstance().getCarts();
        orderSummary();
        initial();
        cartSetItemInit();
        setUpListener();
        setUpCart();
        
    }

    private void setUpCart() {
        recyclerViewCart = binding.recyclerViewCart;
        recyclerViewCart.setHasFixedSize(true);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        cartAdapter = new CartAdapter(this, carts);
        recyclerViewCart.setAdapter(cartAdapter);

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerViewCart);

        cartAdapter.setOnClickInc(new CartAdapter.IncreaseOnclickListener() {
            @Override
            public void onClick(Cart cart, int position) {
                int incQuantity = cart.getQuantity() + 1;
                String token = "Bearer " + LoginUtils.getInstance(CartActivity.this).getUserToken();
                int id = cart.getId();
                cartViewModel.updateCartQuantity(token, id, incQuantity).observe(CartActivity.this, new Observer<CartAddResponse>() {
                    @Override
                    public void onChanged(CartAddResponse cartAddResponse) {
                        if(cartAddResponse != null){
                            int incQuantity = cartAddResponse.getCart().getQuantity();
                            int cartId = cartAddResponse.getCart().getId();
                            CartUtils.getInstance().updateProductQuantity(cartId, incQuantity);
                            carts = CartUtils.getInstance().getCarts();
                            cartSetItemInit();
                            orderSummary();
                            cartAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
        cartAdapter.setOnClickDec(new CartAdapter.DecreaseOnClickListener() {
            @Override
            public void onClick(Cart cart, int position) {
                int decQuantity = cart.getQuantity() - 1;
                String token = "Bearer " + LoginUtils.getInstance(CartActivity.this).getUserToken();
                int id = cart.getId();
                if(decQuantity == 0){
                    return;
                }
                cartViewModel.updateCartQuantity(token, id, decQuantity).observe(CartActivity.this, new Observer<CartAddResponse>() {
                    @Override
                    public void onChanged(CartAddResponse cartAddResponse) {
                        if(cartAddResponse != null){
                            int decQuantity = cartAddResponse.getCart().getQuantity();
                            int cartId = cartAddResponse.getCart().getId();
                            CartUtils.getInstance().updateProductQuantity(cartId, decQuantity);
                            carts = CartUtils.getInstance().getCarts();
                            cartSetItemInit();
                            orderSummary();
                            cartAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getBindingAdapterPosition();
            Cart cart = carts.get(position);
            String token = "Bearer " + LoginUtils.getInstance(CartActivity.this).getUserToken();
            cartViewModel.deleteCartItem(token, cart.getId()).observe(CartActivity.this, responseBody -> {
                if(responseBody != null){
                    int cartId = cart.getId();
                    CartUtils.getInstance().deleteCartItem(cartId);
                    carts = CartUtils.getInstance().getCarts();
                    cartSetItemInit();
                    orderSummary();
                    cartAdapter.notifyDataSetChanged();
                    Toast.makeText(CartActivity.this, "Item has been removed.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(ContextCompat.getColor(CartActivity.this, R.color.white))
                    .addActionIcon(R.drawable.ic_delete)
                    .addSwipeLeftLabel("Delete")
                    .setSwipeLeftLabelTextSize(TypedValue.COMPLEX_UNIT_SP, 18)
                    .setSwipeLeftLabelColor(ContextCompat.getColor(CartActivity.this, R.color.black))
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };




    private void cartSetItemInit() {
        int countProducts = CartUtils.getInstance().countProductsInCart();
        binding.cartBadgeView.setBadgeOvalAfterFirst(true)
                .setBadgePosition(BadgePosition.TOP_RIGHT)
                .setShowCounter(true)
                .setBadgePadding(4)
                .setBadgeValue(countProducts);
    }

    private void initial() {
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
    }

    private void clearCart(){
        String token = "Bearer " + LoginUtils.getInstance(this).getUserToken();
        cartViewModel.clearCart(token).observe(this, responseBody -> {
            if(responseBody != null){
                CartUtils.getInstance().clearProductInCart();
                carts.clear();
                cartAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setUpListener() {
        binding.cartArrowBack.setOnClickListener(view -> finish());
        binding.clearCart.setOnClickListener(view -> clearCart());
    }

    private void orderSummary(){
        binding.cartSubtotal.setText(priceConverter(CartUtils.getInstance().getTotalPrice()));
        binding.cartTotal.setText(priceConverter(CartUtils.getInstance().getTotalPrice() + 2));
    }
}