package com.clearday.ywl.isay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.clearday.ywl.isay.Utils.ShareUtils;
import com.clearday.ywl.isay.Utils.ToastUtils;

import java.io.File;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PictureActivity extends ToolbarActivity {
    public static final String EXTRA_IMAGE_URL = "image_url";
    public static final String EXTRA_IMAGE_TITLE = "image_title";
    public static final String TRANSIT_PIC = "picture";
    public ImageView mImageView;

    PhotoViewAttacher mPhotoViewAttacher;
    String mImageUrl, mImageTitle;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_picture;
    }


    @Override
    public boolean canBack() {
        return true;
    }

    public static Intent newIntent(Context context, String url, String desc) {
        Intent intent = new Intent(context, PictureActivity.class);
        intent.putExtra(PictureActivity.EXTRA_IMAGE_URL, url);
        intent.putExtra(PictureActivity.EXTRA_IMAGE_TITLE, desc);
        return intent;
    }


    private void parseIntent() {
        mImageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        mImageTitle = getIntent().getStringExtra(EXTRA_IMAGE_TITLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageView = (ImageView)findViewById(R.id.photo);
        parseIntent();
        // init image view
        ViewCompat.setTransitionName(mImageView, TRANSIT_PIC);
        Glide.with(this).load(mImageUrl).into(mImageView);
        // set up app bar
        setAppBarAlpha(1.0f);
        setTitle(mImageTitle);
        setupPhotoAttacher();
    }


    private void setupPhotoAttacher() {
        mPhotoViewAttacher = new PhotoViewAttacher(mImageView);
        mPhotoViewAttacher.setOnViewTapListener((view, v, v1) -> hideOrShowToolbar());
        // @formatter:off
        mPhotoViewAttacher.setOnLongClickListener(v -> {
            new AlertDialog.Builder(PictureActivity.this)
                    .setMessage(getString(R.string.ask_saving_picture))
                    .setNegativeButton(android.R.string.cancel,
                            (dialog, which) -> dialog.dismiss())
                    .setPositiveButton(android.R.string.ok,
                            (dialog, which) -> {
                                saveImageToGallery();
                                dialog.dismiss();
                            })
                    .show();
            // @formatter:on
            return true;
        });
    }


    private void saveImageToGallery() {
        // @formatter:off
        Subscription s = RxPhoto.saveImageAndGetPathObservable(this, mImageUrl, mImageTitle)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(uri -> {
                File appDir = new File(Environment.getExternalStorageDirectory(), "Isay");
                String msg = String.format(getString(R.string.picture_has_save_to),
                        appDir.getAbsolutePath());
                ToastUtils.showShort(msg);
            }, error -> ToastUtils.showLong(error.getMessage() + "\n再试试..."));
        // @formatter:on
        addSubscription(s);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_picture, menu);
        // TODO: 把图片的一些信息，比如 who，加载到 Overflow 当中
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share:
                RxPhoto.saveImageAndGetPathObservable(this, mImageUrl, mImageTitle)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(uri -> ShareUtils.shareImage(this, uri, "分享图片到..."),
                                error -> ToastUtils.showLong(error.getMessage()));
                return true;
            case R.id.action_save:
                saveImageToGallery();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPhotoViewAttacher.cleanup();
    }
}
