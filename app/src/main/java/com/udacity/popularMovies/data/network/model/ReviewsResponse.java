package com.udacity.popularMovies.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

public class ReviewsResponse {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("page")
    private int page;

    @Expose
    @SerializedName("results")
    private List<ReviewsResponse.Review> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ReviewsResponse.Review> getResults() {
        return results;
    }

    public void setResults(List<ReviewsResponse.Review> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewsResponse that = (ReviewsResponse) o;

        if (id != that.id) return false;
        return results != null ? results.equals(that.results) : that.results == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (results != null ? results.hashCode() : 0);
        return result;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Parcel(analyze = { ReviewsResponse.Review.class })
    public static class Review {

        @SerializedName("id")
        private String id;

        @SerializedName("author")
        private String author;

        @SerializedName("content")
        private String content;

        @SerializedName("url")
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Review review = (Review) o;

            if (id != null ? !id.equals(review.id) : review.id != null) return false;
            if (author != null ? !author.equals(review.author) : review.author != null)
                return false;
            if (content != null ? !content.equals(review.content) : review.content != null)
                return false;
            return url != null ? url.equals(review.url) : review.url == null;
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (author != null ? author.hashCode() : 0);
            result = 31 * result + (content != null ? content.hashCode() : 0);
            result = 31 * result + (url != null ? url.hashCode() : 0);
            return result;
        }
    }
}
