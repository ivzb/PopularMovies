package com.udacity.popularMovies.data.network.model;

import com.google.gson.annotations.SerializedName;
import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverColumn;
import com.pushtorefresh.storio.contentresolver.annotations.StorIOContentResolverType;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;
import com.udacity.popularMovies.data.db.DbContract;

import org.parceler.Parcel;

import java.util.Arrays;

@SuppressWarnings("SimplifiableIfStatement")
@Parcel(analyze = { Movie.class })
@StorIOSQLiteType(table = DbContract.MovieEntry.TABLE_NAME)
@StorIOContentResolverType(uri = DbContract.MovieEntry.CONTENT_URI_STRING)
public class Movie {

    @SerializedName("id")
    @StorIOSQLiteColumn(name = DbContract.MovieEntry.COLUMN_ID, key = true)
    @StorIOContentResolverColumn(name = DbContract.MovieEntry.COLUMN_ID, key = true)
    int id;

    @SerializedName("vote_count")
    @StorIOSQLiteColumn(name = DbContract.MovieEntry.COLUMN_VOTE_COUNT)
    @StorIOContentResolverColumn(name = DbContract.MovieEntry.COLUMN_VOTE_COUNT)
    int voteCount;

    @SerializedName("video")
    @StorIOSQLiteColumn(name = DbContract.MovieEntry.COLUMN_VIDEO)
    @StorIOContentResolverColumn(name = DbContract.MovieEntry.COLUMN_VIDEO)
    boolean video;

    @SerializedName("vote_average")
    @StorIOSQLiteColumn(name = DbContract.MovieEntry.COLUMN_VOTE_AVERAGE)
    @StorIOContentResolverColumn(name = DbContract.MovieEntry.COLUMN_VOTE_AVERAGE)
    double voteAverage;

    @SerializedName("title")
    @StorIOSQLiteColumn(name = DbContract.MovieEntry.COLUMN_TITLE)
    @StorIOContentResolverColumn(name = DbContract.MovieEntry.COLUMN_TITLE)
    String title;

    @SerializedName("overview")
    @StorIOSQLiteColumn(name = DbContract.MovieEntry.COLUMN_OVERVIEW)
    @StorIOContentResolverColumn(name = DbContract.MovieEntry.COLUMN_OVERVIEW)
    String overview;

    @SerializedName("popularity")
    @StorIOSQLiteColumn(name = DbContract.MovieEntry.COLUMN_POPULARITY)
    @StorIOContentResolverColumn(name = DbContract.MovieEntry.COLUMN_POPULARITY)
    double popularity;

    @SerializedName("poster_path")
    @StorIOSQLiteColumn(name = DbContract.MovieEntry.COLUMN_POSTER_PATH)
    @StorIOContentResolverColumn(name = DbContract.MovieEntry.COLUMN_POSTER_PATH)
    String posterPath;

    @SerializedName("original_language")
    @StorIOSQLiteColumn(name = DbContract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE)
    @StorIOContentResolverColumn(name = DbContract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE)
    String originalLanguage;

    @SerializedName("original_title")
    @StorIOSQLiteColumn(name = DbContract.MovieEntry.COLUMN_ORIGINAL_TITLE)
    @StorIOContentResolverColumn(name = DbContract.MovieEntry.COLUMN_ORIGINAL_TITLE)
    String originalTitle;

    @SerializedName("genre_ids")
    int[] genreIds;

    @SerializedName("backdrop_path")
    @StorIOSQLiteColumn(name = DbContract.MovieEntry.COLUMN_BACKDROP_PATH)
    @StorIOContentResolverColumn(name = DbContract.MovieEntry.COLUMN_BACKDROP_PATH)
    String backdropPath;

    @SerializedName("adult")
    @StorIOSQLiteColumn(name = DbContract.MovieEntry.COLUMN_ADULT)
    @StorIOContentResolverColumn(name = DbContract.MovieEntry.COLUMN_ADULT)
    boolean adult;

    @SerializedName("release_date")
    @StorIOSQLiteColumn(name = DbContract.MovieEntry.COLUMN_RELEASE_DATE)
    @StorIOContentResolverColumn(name = DbContract.MovieEntry.COLUMN_RELEASE_DATE)
    String releaseDate;

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