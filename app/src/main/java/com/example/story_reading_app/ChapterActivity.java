package com.example.story_reading_app;

import static com.example.lib.RetrofitClient.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lib.interfaceRepository.Methods;
import com.example.lib.model.ChapterModel;
import com.example.lib.model.StoryModel;
import com.example.story_reading_app.adapter.ChapterAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChapterActivity extends AppCompatActivity {

    //Chapter
    ChapterAdapter chapterAdapter;
    List<ChapterModel> list = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ListView lsvChapter;
    TextView txtNameStory_ItemChap;
    StoryModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        // lấy biến từ intent story
        Intent intent = getIntent();
        model = (StoryModel) intent.getSerializableExtra("modelStory");

        // list name chapter
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        chapterAdapter = new ChapterAdapter(this, R.layout.item_chapter_name);
        lsvChapter = findViewById(R.id.lsvChapter);
        txtNameStory_ItemChap = findViewById(R.id.txtNameStory_ItemChap);
        txtNameStory_ItemChap.setText(model.getName());
        GetChapter();

        //click chapter content
        lsvChapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //model into detail chapter
                ChapterModel model = (ChapterModel) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(ChapterActivity.this, ChapterDetailActivity.class);
                intent.putExtra("model", model);
                startActivity(intent);
            }
        });


    }

    // SHow list chapter
    private void GetChapter(){

        Methods methods = getRetrofit().create(Methods.class);
        Call<List<ChapterModel>> call = methods.getChapter(model.getId());
        call.enqueue(new Callback<List<ChapterModel>>() {
            @Override
            public void onResponse(Call<List<ChapterModel>> call, Response<List<ChapterModel>> response) {
                list = response.body();
                for(ChapterModel i: list){
                    chapterAdapter.add(i);
                }
                lsvChapter.setAdapter(chapterAdapter);
            }

            @Override
            public void onFailure(Call<List<ChapterModel>> call, Throwable t) {

            }
        });

    }

    public void goToBackChapter(View view) {
        finish();
    }
}