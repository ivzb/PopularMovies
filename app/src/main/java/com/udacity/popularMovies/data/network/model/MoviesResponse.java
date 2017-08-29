package com.udacity.popularMovies.data.network.model;

import android.database.sqlite.SQLiteDatabase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class MoviesResponse {

    @Expose
    @SerializedName("page")
    private int page;

    @Expose
    @SerializedName("total_results")
    private int totalResults;

    @Expose
    @SerializedName("total_pages")
    private int totalPages;

    @Expose
    @SerializedName("results")
    private List<Movie> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoviesResponse that = (MoviesResponse) o;

        if (page != that.page) return false;
        if (totalResults != that.totalResults) return false;
        if (totalPages != that.totalPages) return false;
        return results != null ? results.equals(that.results) : that.results == null;

    }

    @Override
    public int hashCode() {
        int result = page;
        result = 31 * result + totalResults;
        result = 31 * result + totalPages;
        result = 31 * result + (results != null ? results.hashCode() : 0);
        return result;
    }
}
