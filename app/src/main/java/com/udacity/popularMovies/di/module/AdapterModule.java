package com.udacity.popularMovies.di.module;

import android.support.v7.app.AppCompatActivity;
import android.widget.BaseAdapter;

import dagger.Module;

@Module
public class AdapterModule {

    private BaseAdapter mAdapter;

    public AdapterModule(BaseAdapter adapter) {
        this.mAdapter = adapter;
    }
}