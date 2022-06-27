package com.project.read_pro.adapter;

import static com.project.read_pro.utils.Utils.timeAgo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.read_pro.databinding.CustomerSingleReviewBinding;
import com.project.read_pro.model.Review;
import com.project.read_pro.model.User;

import java.util.List;

public class CustomerReviewAdapter extends RecyclerView.Adapter<CustomerReviewAdapter.CustomerReviewViewHolder> {
    private List<Review> reviews;

    public CustomerReviewAdapter(List<Review> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public CustomerReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomerSingleReviewBinding binding = CustomerSingleReviewBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CustomerReviewViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerReviewViewHolder holder, int position) {
        Review review = reviews.get(position);
        User user = review.getUser();
        holder.binding.tvUserName.setText(user.getName());
        holder.binding.rvRatingStar.setRating(review.getRating());
        holder.binding.rvDateRating.setText(timeAgo(review.getCreatedAt()));
        holder.binding.rvTextDetail.setText(review.getComment());
    }

    @Override
    public int getItemCount() {
        if(reviews == null){
            return 0;
        }
        return reviews.size();
    }

    public class CustomerReviewViewHolder extends RecyclerView.ViewHolder {
        private CustomerSingleReviewBinding binding;
        public CustomerReviewViewHolder(CustomerSingleReviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
