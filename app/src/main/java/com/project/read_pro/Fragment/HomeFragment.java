package com.project.read_pro.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.read_pro.adapter.HomeSliderAdapter;
import com.project.read_pro.databinding.FragmentHomeBinding;
import com.project.read_pro.model.Slide;
import com.project.read_pro.response.SlideResponse;
import com.project.read_pro.view_model.SlideViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import ru.nikartm.support.BadgePosition;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private SliderView sliderView;
    private HomeSliderAdapter homeSliderAdapter;
    private SlideViewModel slideViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_home, container, false);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.cartBadgeView.setBadgeValue(200)
                .setBadgeOvalAfterFirst(true)
                .setBadgePosition(BadgePosition.TOP_RIGHT)
                .setShowCounter(true)
                .setBadgePadding(4);
        homeFragmentInit(view);
        setSlideView();
        getSlides();
    }

    private void getSlides() {
        slideViewModel.getAllSlides().observe(getActivity(), new Observer<SlideResponse>() {
            @Override
            public void onChanged(SlideResponse slideResponse) {
                List<Slide> slides = slideResponse.getSlides();
                homeSliderAdapter.renewItems(slides);
            }
        });
    }

    private void homeFragmentInit(View view) {
        slideViewModel = new ViewModelProvider(getActivity()).get(SlideViewModel.class);
        sliderView = binding.homeSlider;
        homeSliderAdapter = new HomeSliderAdapter(getContext());
    }

    private void setSlideView() {
        sliderView.setSliderAdapter(homeSliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }
}