package com.project.read_pro.response;

import com.google.gson.annotations.SerializedName;
import com.project.read_pro.model.StarPercent;

import java.util.List;

public class StarPercentResponse {
    @SerializedName("percentStar")
    private List<StarPercent> starPercents;
    private int totalReview;

    public List<StarPercent> getStarPercents() {
        return starPercents;
    }

    public int getTotalReview() {
        return totalReview;
    }
}
