package com.qin.lib_imageloader;

import android.net.Uri;

import java.io.File;

/**
 * Created by QZC on 2018/8/4.若切换其它图片加载框架，可以实现一键替换(策略模式+代理模式)
 */
public class ImageLoader {
    private static ILoaderStrategy sLoader;
    private static volatile ImageLoader sInstance;

    private ImageLoader() {
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static ImageLoader getInstance() {
        if (sInstance == null) {
            synchronized (ImageLoader.class) {
                if (sInstance == null) {
                    sInstance = new ImageLoader();
                }
            }
        }
        return sInstance;
    }

    /**
     * 提供实时替换图片加载框架的接口
     *
     * @param loader
     */
    public void setImageLoader(ILoaderStrategy loader) {
        if (loader != null) {
            sLoader = loader;
        }
    }

    public LoaderOptions load(String path) {
        return new LoaderOptions(path);
    }

    public LoaderOptions load(int drawable) {
        return new LoaderOptions(drawable);
    }

    public LoaderOptions load(File file) {
        return new LoaderOptions(file);
    }

    public LoaderOptions load(Uri uri) {
        return new LoaderOptions(uri);
    }

    public void loadOptions(LoaderOptions options) {
        sLoader.loadImage(options);
    }

    public void clearMemoryCache() {
        sLoader.clearMemoryCache();
    }

    public void clearDiskCache() {
        sLoader.clearDiskCache();
    }
}
