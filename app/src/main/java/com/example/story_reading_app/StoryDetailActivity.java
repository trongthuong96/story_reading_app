package com.example.story_reading_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lib.model.StoryModel;

public class StoryDetailActivity extends AppCompatActivity {

    TextView txtNameStoryDetail;
    ImageView imgStoryDetail;
    TextView txtAuthorDetail;
    TextView txtCateStoryDetail;
    TextView txtStatusDetail;
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
        relativeBackground = findViewById(R.id.relativeBackground);

        Intent intent = getIntent();
        StoryModel model = (StoryModel) intent.getSerializableExtra("model");

        txtNameStoryDetail.setText(model.getName());
        txtAuthorDetail.setText(model.getAuthor());
        txtCateStoryDetail.setText(model.getCategoryName());
        txtStatusDetail.setText(model.getStatusName());
        Glide.with(this).load(model.getImage()).into(imgStoryDetail);
        relativeBackground.setBackground(Glide.with(this).load(model.getImage()).into(imgStoryDetail).getView().getBackground());

    }
}