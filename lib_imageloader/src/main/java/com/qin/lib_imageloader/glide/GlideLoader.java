package com.qin.lib_imageloader.glide;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.qin.lib_imageloader.ILoaderStrategy;
import com.qin.lib_imageloader.LoaderOptions;

import java.io.File;

/**
 * Created by QZC on 2018/8/4.
 */

public class GlideLoader implements ILoaderStrategy {

    private Context mContext;
    private int defaultPlaceholder;
    private int defaultError;

    public GlideLoader(Context context, @DrawableRes int defaultPlaceholder,
                       @DrawableRes int defaultError) {
        mContext = context;
        this.defaultPlaceholder = defaultPlaceholder;
        this.defaultError = defaultError;
    }

    @Override
    public void loadImage(LoaderOptions options) {
        RequestManager requestManager = null;
        RequestBuilder requestBuilder = null;
        RequestOptions requestOptions = null;
        Context context = options.targetView.getContext();
        requestManager = Glide.with(context);
        requestManager.clear(options.targetView);
        if (requestManager == null) {
            throw new NullPointerException("RequestManager must not be null");
        }
        if (options.url != null) {
            requestBuilder = requestManager.load(options.url);
        } else if (options.file != null) {
            requestBuilder = requestManager.load(options.file);
        } else if (options.uri != null) {
            requestBuilder = requestManager.load(options.uri);
        } else if (options.drawableResId != 0) {
            requestBuilder = requestManager.load(options.drawableResId);
        }
        if (requestBuilder == null) {
            throw new NullPointerException("DrawableTypeRequest must not be null");
        }

        requestOptions = new RequestOptions();
        if (options.targetWidth > 0 && options.targetHeight > 0) {
            requestOptions.override(options.targetWidth, options.targetHeight);
        }
        if (options.isCenterCrop) {
            requestOptions.centerCrop();
        } else if (options.isFitCenter) {
            requestOptions.fitCenter();
        }
        if (options.config != null) {
        }
        if (options.errorResId != 0) {
            requestOptions.error(options.errorResId);
        } else if (options.error != null) {
            requestOptions.error(options.error);
        }
        if (options.errorResId == 0 && options.error == null) {
            requestOptions.error(defaultError);
        }
        if (options.placeholderResId != 0) {
            requestOptions.placeholder(options.placeholderResId);
        } else if (options.placeholder != null) {
            requestOptions.placeholder(options.placeholder);
        }
        if (options.placeholderResId == 0 && options.placeholder == null) {
            requestOptions.placeholder(defaultPlaceholder);
        }
        requestOptions.skipMemoryCache(options.skipMemoryCache);
        if (options.skipDiskCache) {
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        } else {
            requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        }
        if (options.transformation != null) {
            requestOptions.transform((BitmapTransformation) options.transformation);
        }
        if (options.targetView instanceof ImageView) {
            requestBuilder.apply(requestOptions)
                    .into(((ImageView) options.targetView));
        }
    }

    @Override
    public void clearMemoryCache() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(mContext).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearDiskCache() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(mContext).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(mContext).clearDiskCache();
            }
            String ImageExternalCatchDir = mContext.getExternalCacheDir()
                    + ExternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR;
            deleteFolderFile(ImageExternalCatchDir, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定目录下的文件，这里用于缓存的删除
     *
     * @param filePath       filePath
     * @param deleteThisPath deleteThisPath
     */
    private void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {
                    File files[] = file.listFiles();
                    for (File file1 : files) {
                        deleteFolderFile(file1.getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {
                        file.delete();
                    } else {
                        if (file.listFiles().length == 0) {
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
