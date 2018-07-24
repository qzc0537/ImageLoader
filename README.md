# ImageLoader
A flexible Android image loading library.一个灵活的安卓图片加载库

[![](https://jitpack.io/v/qzc0537/ImageLoader.svg)](https://jitpack.io/#qzc0537/ImageLoader)

使用
--
1.project build.gradle下添加：
maven { url 'https://jitpack.io' }

如下：

```
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

2.app build.gradle下添加依赖 ：

```
implementation 'com.github.qzc0537:ImageLoader:1.0.1'
```
3.初始化实现类（默认使用Glide，其他自己实现）：
```
 ImageLoader.getInstance().setImageLoader(new GlideLoader(this,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher));
```
4.加载图片（可以是Url Uri File drawable...）：

```
ImageLoader.getInstance()
                .load(url)
                .skipMemoryCache(true)
                .skipDiskCache(true)
                .centerCrop()
                .transform(new CircleTransformation(this, 2, Color.BLACK))
                .into(mIv_girl1);
```
女神十元镇楼：
![女神十元镇楼](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532414059726&di=9c3a669a6a1c8b86d484e546f378229b&imgtype=0&src=http://i2.hdslb.com/bfs/face/cffbc3f149b1a2b02d5969f2b1f8ab4cf9cd00b8.jpg)
