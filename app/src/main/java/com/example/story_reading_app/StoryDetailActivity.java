package com.example.story_reading_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.lib.model.StoryModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StoryDetailActivity extends AppCompatActivity {

    TextView txtNameStoryDetail;
    ImageView imgStoryDetail;
    TextView txtAuthorDetail;
    TextView txtCateStoryDetail;
    TextView txtStatusDetail;
    TextView txtSummaryDetail;
    RelativeLayout relativeBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        txtNameStoryDetail = findViewById(R.id.txtNameStoryDetail);
        imgStoryDetail = findViewById(R.id.imgStoryDetail);
        txtAuthorDetail = findViewById(R.id.txtAuthorDetail);
        txtCateStoryDetail = findViewById(R.id.txtCateStoryDetail);
        txtStatusDetail = findViewById(R.id.txtStatusDetail);
        txtSummaryDetail = findViewById(R.id.txtSummaryDetail);
        relativeBackground = findViewById(R.id.relativeBackground);

        Intent intent = getIntent();
        StoryModel model = (StoryModel) intent.getSerializableExtra("model");

        txtNameStoryDetail.setText(model.getName());
        txtAuthorDetail.setText(model.getAuthor());
        txtCateStoryDetail.setText(model.getCategoryName());
        txtStatusDetail.setText(model.getStatusName());
        txtSummaryDetail.setText(model.getSummaryContent());
        Glide.with(this).load(model.getImage()).into(imgStoryDetail);

        Glide.with(this)
                .load(model.getImage())
                .into(new CustomTarget<Drawable>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        relativeBackground.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    public void goToBackHome(View view) {
        finish();
    }

    public void goToChapter(View view) {

    }
}