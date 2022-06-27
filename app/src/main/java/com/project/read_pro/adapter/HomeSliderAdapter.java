package com.project.read_pro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.read_pro.R;
import com.project.read_pro.model.Slide;
import com.project.read_pro.model.SlideProduct;
import com.smarteist.autoimageslider.SliderViewAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeSliderAdapter extends SliderViewAdapter<HomeSliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<Slide> mSliderItems = new ArrayList<>();
    private HomeSliderAdapter.SlideOnClickListener slideOnClickListener;

    public HomeSliderAdapter(Context context){
        this.context = context;
    }

    public interface SlideOnClickListener{
        void onClick(Slide slide, int position);
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, null);
        return new SliderAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        Slide slide = mSliderItems.get(position);
        SlideProduct slideProduct = slide.getProduct();
        Glide.with(viewHolder.itemView)
                .load(slideProduct.getImage()[0])
                .fitCenter()
                .into(viewHolder.sliderImage);
        viewHolder.sliderTitle.setText(slide.getTitle());
        viewHolder.sliderAuthor.setText(slideProduct.getAuthor());
        //viewHolder.sliderSubtitle.setText(slide.getSubtitle());

        viewHolder.itemView.setOnClickListener(view -> {
            slideOnClickListener.onClick(slide, position);
        });


    }

    public void renewItems(List<Slide> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    public class SliderAdapterVH extends ViewHolder {
        ImageView sliderImage;
        TextView sliderTitle;
        TextView sliderAuthor;
        //TextView sliderSubtitle;
        public SliderAdapterVH(View itemView) {
            super(itemView);
            sliderImage = itemView.findViewById(R.id.slider_item_image);
            sliderTitle = itemView.findViewById(R.id.slider_item_title);
            sliderAuthor = itemView.findViewById(R.id.slider_item_author);
            //sliderSubtitle = itemView.findViewById(R.id.slider_item_subtitle);
        }
    }

    public void setOnClickSlide(HomeSliderAdapter.SlideOnClickListener slideOnClickListener){
        this.slideOnClickListener = slideOnClickListener;
    }
}
