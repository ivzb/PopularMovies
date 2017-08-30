package com.udacity.popularMovies.ui.main;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.HashMap;

@Parcel(analyze = { SortBy.class })
enum SortBy {
    MostPopular("Most popular"),
    TopRated("Top rated"),
    Favorite("Favorite");

    private String mFriendlyName;

    private static final HashMap<String, SortBy> map = new HashMap<>();

    static {
        for (SortBy sortBy : SortBy.values()) {
            map.put(sortBy.toString(), sortBy);
        }
    }

    @ParcelConstructor
    SortBy() {

    }

    SortBy(String friendlyName) {
        mFriendlyName = friendlyName;
    }

    public static String[] getValues() {
        String[] values = new String[SortBy.values().length];
        int i = 0;

        for (SortBy sortBy: SortBy.values()) {
            values[i++] = sortBy.toString();
        }

        return values;
    }

    public static SortBy getByName(String name) {
        return map.get(name);
    }

    @Override
    public String toString() {
        return mFriendlyName;
    }
}