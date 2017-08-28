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

    @SuppressWarnings("SimplifiableIfStatement")
    @Parcel(analyze = { Movie.class })
    public static class Movie {

        @SerializedName("id")
        private int id;

        @SerializedName("vote_count")
        private int voteCount;

        @SerializedName("video")
        private boolean video;

        @SerializedName("vote_average")
        private double voteAverage;

        @SerializedName("title")
        private String title;

        @SerializedName("overview")
        private String overview;

        @SerializedName("popularity")
        private double popularity;

        @SerializedName("poster_path")
        private String posterPath;

        @SerializedName("original_language")
        private String originalLanguage;

        @SerializedName("original_title")
        private String originalTitle;

        @SerializedName("genre_ids")
        private int[] genreIds;

        @SerializedName("backdrop_path")
        private String backdropPath;

        @SerializedName("adult")
        private boolean adult;

        @SerializedName("release_date")
        private String releaseDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(int voteCount) {
            this.voteCount = voteCount;
        }

        public boolean isVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public double getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(double voteAverage) {
            this.voteAverage = voteAverage;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
        }

        public int[] getGenreIds() {
            return genreIds;
        }

        public void setGenreIds(int[] genreIds) {
            this.genreIds = genreIds;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        @SuppressWarnings("SimplifiableIfStatement")
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Movie movie = (Movie) o;

            if (id != movie.id) return false;
            if (voteCount != movie.voteCount) return false;
            if (video != movie.video) return false;
            if (Double.compare(movie.voteAverage, voteAverage) != 0) return false;
            if (Double.compare(movie.popularity, popularity) != 0) return false;
            if (adult != movie.adult) return false;
            if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
            if (overview != null ? !overview.equals(movie.overview) : movie.overview != null)
                return false;
            if (posterPath != null ? !posterPath.equals(movie.posterPath) : movie.posterPath != null)
                return false;
            if (originalLanguage != null ? !originalLanguage.equals(movie.originalLanguage) : movie.originalLanguage != null)
                return false;
            if (originalTitle != null ? !originalTitle.equals(movie.originalTitle) : movie.originalTitle != null)
                return false;
            if (!Arrays.equals(genreIds, movie.genreIds)) return false;
            if (backdropPath != null ? !backdropPath.equals(movie.backdropPath) : movie.backdropPath != null)
                return false;
            return releaseDate != null ? releaseDate.equals(movie.releaseDate) : movie.releaseDate == null;

        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = id;
            result = 31 * result + voteCount;
            result = 31 * result + (video ? 1 : 0);
            temp = Double.doubleToLongBits(voteAverage);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            result = 31 * result + (title != null ? title.hashCode() : 0);
            result = 31 * result + (overview != null ? overview.hashCode() : 0);
            temp = Double.doubleToLongBits(popularity);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            result = 31 * result + (posterPath != null ? posterPath.hashCode() : 0);
            result = 31 * result + (originalLanguage != null ? originalLanguage.hashCode() : 0);
            result = 31 * result + (originalTitle != null ? originalTitle.hashCode() : 0);
            result = 31 * result + Arrays.hashCode(genreIds);
            result = 31 * result + (backdropPath != null ? backdropPath.hashCode() : 0);
            result = 31 * result + (adult ? 1 : 0);
            result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
            return result;
        }
    }
}
