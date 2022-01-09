package com.example.story_reading_app.admin;

import static com.example.lib.RetrofitClient.getRetrofit;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lib.interfaceRepository.Methods;
import com.example.lib.model.CategoryModel;
import com.example.lib.model.StatusModel;
import com.example.lib.model.StoryModel;
import com.example.story_reading_app.R;
import com.example.story_reading_app.adapter.CategoryAdapter;
import com.example.story_reading_app.adapter.StatusAdapter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryInsertOrUpdate extends AppCompatActivity {

    //category
    CategoryAdapter adapter;
    List<CategoryModel> list = new ArrayList<>();
    Spinner spnListCateIU;

    //status
    StatusAdapter statusAdapter;
    Spinner spnListStatusIU;

    //story
    StoryModel model;
    EditText name;
    int cateId;
    int statusId;
    EditText author;
    EditText summary;
    EditText image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_insert_or_update);

        //list cate
        spnListCateIU = findViewById(R.id.spnListCateIU);
        adapter = new CategoryAdapter(this, R.layout.admin_spinner_item);
        GetCategoryName();

        //list status
        spnListStatusIU = findViewById(R.id.spnListStatusIU);
        statusAdapter = new StatusAdapter(this, R.layout.admin_spinner_item);
        GetStatus();

        //add find id view
        Button btnInsertOrUpdateIU = findViewById(R.id.btnInsertOrUpdateIU);
        name = findViewById(R.id.txtNameStoryIU);
        author = findViewById(R.id.txtAuthorIU);
        summary = findViewById(R.id.txtSummaryIU);
        image = findViewById(R.id.txtLinkImgIU);

        spnListCateIU.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CategoryModel categoryModel = (CategoryModel) adapterView.getItemAtPosition(i);
                cateId = categoryModel.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnListStatusIU.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                StatusModel statusModel = (StatusModel) adapterView.getItemAtPosition(i);
                statusId = statusModel.getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //model story
        Intent intent = this.getIntent();
        model = (StoryModel) intent.getSerializableExtra("model");

        if(model == null){
            // edit name button
            btnInsertOrUpdateIU.setText("Thêm");

        } else { //set text layout from model
            btnInsertOrUpdateIU.setText("Sửa");
            name.setText(model.getName());
            author.setText(model.getAuthor());
            summary.setText(model.getSummaryContent());
            image.setText(model.getImage());
        }
    }

    // get list category
    public void GetCategoryName(){

        Methods methods = getRetrofit().create(Methods.class);
        Call<List<CategoryModel>> call = methods.getCategory();
        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                list = response.body();
                CategoryModel temp = new CategoryModel();
                for(CategoryModel i: list){
                    adapter.add(i);
                    if(model != null && (int)model.getCategoryId() == (int)i.getId()){
                        temp = i;
                    }
                }
                spnListCateIU.setAdapter(adapter);
                spnListCateIU.setSelection(adapter.getPosition(temp));
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
            }
        });
    }

    // get list status
    public void GetStatus(){

        Methods methods = getRetrofit().create(Methods.class);
        Call<List<StatusModel>> call = methods.getStatus();
        call.enqueue(new Callback<List<StatusModel>>() {
            @Override
            public void onResponse(Call<List<StatusModel>> call, Response<List<StatusModel>> response) {
                StatusModel temp = new StatusModel();
                for(StatusModel i: response.body()){
                    statusAdapter.add(i);
                    if(model != null && (int)model.getStatusId() == (int)i.getId()){
                        temp = i;
                    }
                }
                spnListStatusIU.setAdapter(statusAdapter);
                spnListStatusIU.setSelection(statusAdapter.getPosition(temp));
                int o=0;
            }

            @Override
            public void onFailure(Call<List<StatusModel>> call, Throwable t) {
            }
        });
    }

    //button insert or update story
    public void goToInsertOrUpdateStory(View view) {
        //Insert
        if(model == null){
            model = new StoryModel();

            //check
            if(name.getText().toString().equals("") || author.getText().toString().equals("")){

            }else {
                //set model
                model.setName(name.getText().toString());
                model.setAuthor(author.getText().toString());
                model.setSummaryContent(summary.getText().toString());
                model.setImage(image.getText().toString());
                model.setStatusId(statusId);
                model.setCategoryId(cateId);
                model.setUserId(1L);

                // call api
                Methods methods = getRetrofit().create(Methods.class);
                Call<StoryModel> call = methods.postStory(model);
                call.enqueue(new Callback<StoryModel>() {
                    @Override
                    public void onResponse(Call<StoryModel> call, Response<StoryModel> response) {
                    }

                    @Override
                    public void onFailure(Call<StoryModel> call, Throwable t) {
                    }
                });
            }
        } else {
            //update
            //check
            if(name.getText().toString().equals("") || author.getText().toString().equals("")){

            }else {
                //set model
                model.setName(name.getText().toString());
                model.setAuthor(author.getText().toString());
                model.setSummaryContent(summary.getText().toString());
                model.setImage(image.getText().toString());
                model.setStatusId(statusId);
                model.setCategoryId(cateId);
                model.setUserId(1L);
                model.setDateCreate(convertStringToTimestamp(model.getDateCreate().toString()));

                // call api
                Methods methods = getRetrofit().create(Methods.class);
                Call<StoryModel> call = methods.putStory(model);
                call.enqueue(new Callback<StoryModel>() {
                    @Override
                    public void onResponse(Call<StoryModel> call, Response<StoryModel> response) {
                        System.out.println(response.code());
                    }

                    @Override
                    public void onFailure(Call<StoryModel> call, Throwable t) {
                        System.out.println(t.toString());
                    }
                });
            }
        }
    }

    // fomat time
    public static Timestamp convertStringToTimestamp(String strDate) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
            // you can change format of date
            Date date = formatter.parse(strDate);
            Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate;
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return null;
        }
    }
}