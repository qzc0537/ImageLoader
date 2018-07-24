package com.qin.lib_imageloader;

/**
 * Created by JohnsonFan on 2018/8/4.
 */

public interface ILoaderStrategy {

	void loadImage(LoaderOptions options);

	/**
	 * 清理内存缓存
	 */
	void clearMemoryCache();

	/**
	 * 清理磁盘缓存
	 */
	void clearDiskCache();

}
