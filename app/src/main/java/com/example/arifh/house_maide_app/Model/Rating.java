package com.example.arifh.house_maide_app.Model;

public class Rating {

    public   int ratingId;
    public   String rating;

    public Rating(String rating) {

    }

    public Rating(int ratingId, String rating) {
        this.ratingId = ratingId;
        this.rating = rating;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
