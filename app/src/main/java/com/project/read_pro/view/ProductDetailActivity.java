package com.project.read_pro.view;

import static com.project.read_pro.utils.AppConstant.PRODUCT;
import static com.project.read_pro.utils.Utils.numberWithComma;
import static com.project.read_pro.utils.Utils.priceConverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.project.read_pro.adapter.BookDetailImageAdapter;
import com.project.read_pro.adapter.BookDetailStarPercentAdapter;
import com.project.read_pro.adapter.CustomerReviewAdapter;
import com.project.read_pro.databinding.ActivityProductDetailBinding;
import com.project.read_pro.model.Cart;
import com.project.read_pro.model.Product;
import com.project.read_pro.model.Review;
import com.project.read_pro.model.StarPercent;
import com.project.read_pro.response.CartAddResponse;
import com.project.read_pro.response.StarPercentResponse;
import com.project.read_pro.storage.LoginUtils;
import com.project.read_pro.utils.CartUtils;
import com.project.read_pro.view_model.CartViewModel;
import com.project.read_pro.view_model.ReviewViewModel;
import com.project.read_pro.view_model.StarPercentViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ru.nikartm.support.BadgePosition;

public class ProductDetailActivity extends AppCompatActivity {

    ActivityProductDetailBinding binding;
    private Product product;
    // Image slider
    private SliderView imageSlider;
    private BookDetailImageAdapter bookDetailImageAdapter;
    // Star rating progress
    private RecyclerView starRatingRecyclerView;
    private StarPercentViewModel starPercentViewModel;
    private BookDetailStarPercentAdapter bookDetailStarPercentAdapter;
    private List<StarPercent> starPercents;
    // review
    private RecyclerView recyclerViewReview;
    private ReviewViewModel reviewViewModel;
    private List<Review> reviews;
    private CustomerReviewAdapter customerReviewAdapter;
    private CartViewModel cartViewModel;

    @Override
    protected void onResume() {
        super.onResume();
        cartSetItemInit();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        product = getIntent().getParcelableExtra(PRODUCT);

        Toast.makeText(this, "Product ID: " + product.getId(), Toast.LENGTH_SHORT).show();

        initialViewModel();
        cartSetItemInit();
        setUpListener();
        setUpSlideImage();
        setUpBookDetail();
        setUpStarRatingProgress();
        setUpCustomerReview();





    }

    private void initialViewModel() {
        starPercentViewModel = new ViewModelProvider(this).get(StarPercentViewModel.class);
        reviewViewModel = new ViewModelProvider(this).get(ReviewViewModel.class);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
    }

    private void cartSetItemInit() {
        binding.cartBadgeView.setBadgeOvalAfterFirst(true)
                .setBadgePosition(BadgePosition.TOP_RIGHT)
                .setShowCounter(true)
                .setBadgePadding(4);

        if(LoginUtils.getInstance(this).isLoggedIn()){
            int countProducts = CartUtils.getInstance().countProductsInCart();
            binding.cartBadgeView.setBadgeValue(countProducts);
        }else{
            binding.cartBadgeView.setBadgeValue(0);
        }

    }

    private void updateItemCountCart(){
        int countProducts = CartUtils.getInstance().countProductsInCart();
        binding.cartBadgeView.setBadgeValue(countProducts);
    }

    private void setUpStarRatingProgress() {
        starRatingRecyclerView = binding.bookDetailStarRatingProgressRecyclerView;
        starRatingRecyclerView.setHasFixedSize(true);
        starRatingRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        starPercentViewModel.getProductStarPercent(product.getId()).observe(this, new Observer<StarPercentResponse>() {
            @Override
            public void onChanged(StarPercentResponse starPercentResponse) {
                if(starPercentResponse != null){
                    starPercents = starPercentResponse.getStarPercents();

                    Collections.sort(starPercents, new Comparator<StarPercent>() {
                        @Override
                        public int compare(StarPercent o1, StarPercent o2) {
                            return o2.getStar() > o1.getStar()?1:-1;
                        }
                    });


                    bookDetailStarPercentAdapter = new BookDetailStarPercentAdapter(starPercents);
                    starRatingRecyclerView.setAdapter(bookDetailStarPercentAdapter);
                }
            }
        });

    }

    private void setUpListener() {
        binding.bookDetailArrowBack.setOnClickListener(v -> {
            finish();
        });
        binding.bookDetailAddToCart.setOnClickListener(view -> handleAddToCart());
        binding.cartBadgeView.setOnClickListener(view -> gotoCartActivity());
    }

    private void handleAddToCart() {
        if(!LoginUtils.getInstance(ProductDetailActivity.this).isLoggedIn()){
            gotoLoginActivity();
        }else{
            String token = "Bearer " + LoginUtils.getInstance(this).getUserToken();
            JsonObject jsonBody = new JsonObject();
            jsonBody.addProperty("image", product.getImage()[0]);
            jsonBody.addProperty("quantity", 1);
            jsonBody.addProperty("product", product.getId());
            cartViewModel.addProductToCart(token, jsonBody).observe(this, cartAddResponse -> {
                if(cartAddResponse != null){
                    Cart cart = cartAddResponse.getCart();
                    CartUtils.getInstance().addToCart(cart);
                    updateItemCountCart();
                }
            });
        }
    }

    private void setUpBookDetail() {
        binding.bookDetailTitle.setText(product.getTitle());
        binding.bookDetailAuthor.setText(product.getAuthor());
        binding.bookDetailRatingStar.setRating((float) product.getAverageRating());
        binding.bookDetailNumRating.setText(numberWithComma(product.getNumOfReviews()));
        binding.bookDetailNumSold.setText(numberWithComma(product.getSold()));
        binding.bookDetailPrice.setText(priceConverter(product.getPrice() - product.getDiscount()));
        binding.bookDetailLanguage.setText(product.getLanguage());
        binding.bookDetailDescription.setText(product.getDescription());
        binding.bookDetailNumCusRating.setText(numberWithComma(product.getNumOfReviews()));
        binding.bookDetailRatingNum.setText(product.getAverageRating() + " ratiing");
    }

    private void setUpSlideImage() {
        imageSlider = binding.bookDetailImageSlider;
        bookDetailImageAdapter = new BookDetailImageAdapter(this, product.getImage());
        imageSlider.setSliderAdapter(bookDetailImageAdapter);
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        imageSlider.startAutoCycle();
    }

    private void setUpCustomerReview() {
        recyclerViewReview = binding.bookDetailReviewRecyclerView;
        recyclerViewReview.setHasFixedSize(true);
        recyclerViewReview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        reviewViewModel.getReview(20, 1, product.getId()).observe(this, reviewResponse -> {
            if(reviewResponse != null){
                reviews = reviewResponse.getReviews();
                customerReviewAdapter = new CustomerReviewAdapter(reviews);
                recyclerViewReview.setAdapter(customerReviewAdapter);
            }
        });
    }
    private void gotoLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private void gotoCartActivity(){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}