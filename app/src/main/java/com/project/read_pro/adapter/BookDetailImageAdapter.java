package com.project.read_pro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.project.read_pro.databinding.BookDetailImageItemBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class BookDetailImageAdapter extends SliderViewAdapter<BookDetailImageAdapter.ImageViewHolder> {

    private Context mContext;
    private String[] images;

    public BookDetailImageAdapter(Context mContext, String[] images) {
        this.mContext = mContext;
        this.images = images;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent) {
        BookDetailImageItemBinding binding = BookDetailImageItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder viewHolder, int position) {
        String image = images[position];
        Glide.with(mContext)
                .load(image)
                .into(viewHolder.binding.bookDetailImage);
    }

    @Override
    public int getCount() {
        if(images == null){
            return 0;
        }
        return images.length;
    }

    public class ImageViewHolder extends ViewHolder {
        private BookDetailImageItemBinding binding;
        public ImageViewHolder(BookDetailImageItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
