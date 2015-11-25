package com.clearday.ywl.isay;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.LruCache;
import android.view.View;

import com.clearday.ywl.isay.Utils.ImageFileCacheUtils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * activity的基类
 *
 * Created by Administrator on 2015/8/26:20:27.
 */
public class BaseActivity extends AppCompatActivity {

    public RequestQueue              mRequestQueue;
    protected ImageCache                mImageCache;
    protected ImageLoader               mImageLoader;
    protected LruCache<String, Bitmap> mLruCache;
   // private Oauth2AccessToken         mAccessToken;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

   //     setmAccessToken(AccessTokenKeeper.readAccessToken(this));
        mRequestQueue = BaseApplication.getsRequestQueue();
        mLruCache = new LruCache<>(40);
        mImageCache = new ImageCache() {
            @Override
            public Bitmap getBitmap(String key) {
                return ImageFileCacheUtils.getInstance().getImage(key);
            }

            @Override
            public void putBitmap(String key, Bitmap value) {
                ImageFileCacheUtils.getInstance().saveBitmap(value, key);
            }
        };

        mImageLoader = new ImageLoader(mRequestQueue, mImageCache);
    }
//
//    public Oauth2AccessToken getmAccessToken() {
//        return mAccessToken;
//    }
//
//    public void setmAccessToken(Oauth2AccessToken mAccessToken) {
//        this.mAccessToken = mAccessToken;
//    }
}