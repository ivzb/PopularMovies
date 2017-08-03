package com.udacity.popularMovies.utils.adapterSetters;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.udacity.popularMovies.BuildConfig;
import com.udacity.popularMovies.utils.drawables.FreskoCircleProgressBarDrawable;

public class Images {

    // Fresco
    @BindingAdapter({ "bind:url" })
    public static void loadImage(SimpleDraweeView view, String imageUrl) {
        if (imageUrl != null) {
            Uri uri = Uri.parse(BuildConfig.BASE_IMAGE_URL + imageUrl);
            view.setImageURI(uri);

            GenericDraweeHierarchy hierarchy = view.getHierarchy();
            hierarchy.setFadeDuration(250);
            hierarchy.setProgressBarImage(new FreskoCircleProgressBarDrawable());
        }
    }

    // Binding Drawable to ImageView
    @BindingAdapter("android:src")
    public static void setImageDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }
}
