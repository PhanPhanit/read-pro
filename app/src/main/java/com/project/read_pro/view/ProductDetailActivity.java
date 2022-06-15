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

import com.project.read_pro.R;
import com.project.read_pro.adapter.BookDetailImageAdapter;
import com.project.read_pro.adapter.BookDetailStarPercentAdapter;
import com.project.read_pro.databinding.ActivityProductDetailBinding;
import com.project.read_pro.model.Product;
import com.project.read_pro.model.Review;
import com.project.read_pro.model.StarPercent;
import com.project.read_pro.response.StarPercentResponse;
import com.project.read_pro.view_model.ReviewViewModel;
import com.project.read_pro.view_model.StarPercentViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpListener();
        product = getIntent().getParcelableExtra(PRODUCT);
        setUpSlideImage();
        setUpBookDetail();
        initialViewModel();
        setUpStarRatingProgress();


        Toast.makeText(this, "Product id: " + product.getId(), Toast.LENGTH_SHORT).show();





    }

    private void initialViewModel() {
        starPercentViewModel = new ViewModelProvider(this).get(StarPercentViewModel.class);
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
}