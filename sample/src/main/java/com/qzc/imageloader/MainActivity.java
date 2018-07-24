package com.qzc.imageloader;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.qin.lib_imageloader.ImageLoader;
import com.qin.lib_imageloader.glide.BlurTransformation;
import com.qin.lib_imageloader.glide.CircleTransformation;
import com.qin.lib_imageloader.glide.GlideLoader;
import com.qin.lib_imageloader.glide.RadiusCustomTransformation;
import com.qin.lib_imageloader.glide.RadiusTransformation;
import com.qin.lib_imageloader.glide.RotateTransformation;

public class MainActivity extends AppCompatActivity {
    private final String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532414059726&di=9c3a669a6a1c8b86d484e546f378229b&imgtype=0&src=http%3A%2F%2Fi2.hdslb.com%2Fbfs%2Fface%2Fcffbc3f149b1a2b02d5969f2b1f8ab4cf9cd00b8.jpg";
    private ImageView mIv_girl1;
    private ImageView mIv_girl2;
    private ImageView mIv_girl3;
    private ImageView mIv_girl4;
    private ImageView mIv_girl5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIv_girl1 = findViewById(R.id.iv_girl1);
        mIv_girl2 = findViewById(R.id.iv_girl2);
        mIv_girl3 = findViewById(R.id.iv_girl3);
        mIv_girl4 = findViewById(R.id.iv_girl4);
        mIv_girl5 = findViewById(R.id.iv_girl5);

        //初始化实现类
        ImageLoader.getInstance().setImageLoader(new GlideLoader(this,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher));

        ImageLoader.getInstance()
                .load(url)
                .skipMemoryCache(true)
                .skipDiskCache(true)
                .centerCrop()
                .transform(new CircleTransformation(this, 2, Color.BLACK))
                .into(mIv_girl1);
        ImageLoader.getInstance()
                .load(url)
                .transform(new RadiusTransformation(this, 10))
                .into(mIv_girl2);
        ImageLoader.getInstance()
                .load(url)
                .transform(new RadiusCustomTransformation(this, 10, RadiusCustomTransformation.CornerType.TOP))
                .into(mIv_girl3);
        ImageLoader.getInstance()
                .load(url)
                .transform(new RotateTransformation(this, 180f))
                .into(mIv_girl4);
        ImageLoader.getInstance()
                .load(url)
                .transform(new BlurTransformation(this, 10, 1))
                .into(mIv_girl5);

    }
}
