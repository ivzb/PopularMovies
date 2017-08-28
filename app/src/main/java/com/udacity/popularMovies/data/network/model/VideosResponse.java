package com.udacity.popularMovies.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

public class VideosResponse {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("results")
    private List<Video> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideosResponse that = (VideosResponse) o;

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
    @Parcel(analyze = { Video.class })
    public static class Video {

        @SerializedName("id")
        private String id;

        @SerializedName("key")
        private String key;

        @SerializedName("name")
        private String name;

        @SerializedName("site")
        private String site;

        @SerializedName("size")
        private int size;

        @SerializedName("type")
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Video video = (Video) o;

            if (size != video.size) return false;
            if (id != null ? !id.equals(video.id) : video.id != null) return false;
            if (key != null ? !key.equals(video.key) : video.key != null) return false;
            if (name != null ? !name.equals(video.name) : video.name != null) return false;
            if (site != null ? !site.equals(video.site) : video.site != null) return false;
            return type != null ? type.equals(video.type) : video.type == null;
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (key != null ? key.hashCode() : 0);
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (site != null ? site.hashCode() : 0);
            result = 31 * result + size;
            result = 31 * result + (type != null ? type.hashCode() : 0);
            return result;
        }
    }
}
