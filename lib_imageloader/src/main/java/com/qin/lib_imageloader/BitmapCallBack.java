package com.qin.lib_imageloader;

import android.graphics.Bitmap;

/**
 * Created by QZC on 2018/8/4.
 */
public interface BitmapCallBack {

    void onBitmapLoaded(Bitmap bitmap);

    void onBitmapFailed(Exception e);

    public static class EmptyCallback implements BitmapCallBack {


        @Override
        public void onBitmapLoaded(Bitmap bitmap) {

        }

        @Override
        public void onBitmapFailed(Exception e) {

        }
    }
}
