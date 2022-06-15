package com.project.read_pro.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.read_pro.databinding.CustomerRatingItemBinding;
import com.project.read_pro.model.StarPercent;

import java.util.List;

public class BookDetailStarPercentAdapter extends RecyclerView.Adapter<BookDetailStarPercentAdapter.StarPercentViewHolder> {
    private List<StarPercent> starPercents;

    public BookDetailStarPercentAdapter(List<StarPercent> starPercents) {
        this.starPercents = starPercents;
    }

    @NonNull
    @Override
    public StarPercentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomerRatingItemBinding binding = CustomerRatingItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new StarPercentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StarPercentViewHolder holder, int position) {
        StarPercent starPercent = starPercents.get(position);
        String numOfStar = starPercent.getStar() == 1 || starPercent.getStar() == 0.5
                ? starPercent.getStar() + " star":starPercent.getStar() + " stars";
        float percent = starPercent.getPercent() / 100;
        holder.binding.numOfStar.setText(numOfStar);
        holder.binding.tvPercent.setText(starPercent.getPercent() + "%");

        holder.binding.progressBarPercent.setProgress((int) (starPercent.getPercent() * 100));



    }

    @Override
    public int getItemCount() {
        if(starPercents == null){
            return 0;
        }
        return starPercents.size();
    }

    public class StarPercentViewHolder extends RecyclerView.ViewHolder {
        private CustomerRatingItemBinding binding;
        public StarPercentViewHolder(CustomerRatingItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
