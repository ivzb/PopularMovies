package com.udacity.popularMovies.data.callbacks;

public interface GetCallback<T> {

    void onSuccess(T data);
    void onFailure(String message);
}