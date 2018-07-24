package com.qin.lib_imageloader;

import android.animation.Animator;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.view.View;

import java.io.File;

/**
 * Created by QZC on 2018/8/4.
 */
public class LoaderOptions {
    public String url;
    public File file;
    public Uri uri;
    public int drawableResId;
    public View targetView;
    public int placeholderResId;
    public int errorResId;
    public Drawable placeholder;
    public Drawable error;
    public boolean isCenterCrop = false;
    public boolean isFitCenter = false;
    public boolean skipMemoryCache = false;
    public boolean skipDiskCache = false;
    public Bitmap.Config config = Bitmap.Config.RGB_565;
    public Object transformation;
    public int targetWidth;
    public int targetHeight;
    public int animationResId;
    public Animator animator;
    public BitmapCallBack callBack;

    public LoaderOptions(String url) {
        this.url = url;
    }

    public LoaderOptions(File file) {
        this.file = file;
    }

    public LoaderOptions(int drawableResId) {
        this.drawableResId = drawableResId;
    }

    public LoaderOptions(Uri uri) {
        this.uri = uri;
    }

    public void into(View targetView) {
        this.targetView = targetView;
        ImageLoader.getInstance().loadOptions(this);
    }

    public void bitmap(BitmapCallBack callBack) {
        this.callBack = callBack;
        ImageLoader.getInstance().loadOptions(this);
    }

    public LoaderOptions placeholder(@DrawableRes int placeholderResId) {
        this.placeholderResId = placeholderResId;
        return this;
    }

    public LoaderOptions placeholder(Drawable placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public LoaderOptions error(@DrawableRes int errorResId) {
        this.errorResId = errorResId;
        return this;
    }

    public LoaderOptions error(Drawable error) {
        this.error = error;
        return this;
    }

    public LoaderOptions centerCrop() {
        isCenterCrop = true;
        return this;
    }

    public LoaderOptions fitCenter() {
        isFitCenter = true;
        return this;
    }

    public LoaderOptions skipMemoryCache(boolean skipMemoryCache) {
        this.skipMemoryCache = skipMemoryCache;
        return this;
    }

    public LoaderOptions skipDiskCache(boolean skipDiskCache) {
        this.skipDiskCache = skipDiskCache;
        return this;
    }

    public LoaderOptions config(Bitmap.Config config) {
        this.config = config;
        return this;
    }

    public LoaderOptions resize(int targetWidth, int targetHeight) {
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
        return this;
    }

    public LoaderOptions animate(int animationResId) {
        this.animationResId = animationResId;
        return this;
    }

    public LoaderOptions animate(Animator animator) {
        this.animator = animator;
        return this;
    }

    public LoaderOptions transform(Object transformation) {
        this.transformation = transformation;
        return this;
    }
}
