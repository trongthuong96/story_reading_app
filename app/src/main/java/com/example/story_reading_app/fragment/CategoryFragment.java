package com.example.story_reading_app.fragment;

import static com.example.lib.RetrofitClient.getRetrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lib.interfaceRepository.Methods;
import com.example.lib.model.CategoryModel;
import com.example.lib.model.StoryModel;
import com.example.story_reading_app.R;
import com.example.story_reading_app.adapter.CategoryAdapter;
import com.example.story_reading_app.adapter.StoryAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {

    //category
    CategoryAdapter adapter;
    List<CategoryModel> list = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    GridView gdvListNameCategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        gdvListNameCategory = getView().findViewById(R.id.gdvListNameCategory);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //category
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        adapter = new CategoryAdapter(getActivity(), R.layout.item_catagory);
        GetCategoryName();
        getActivity().setTitle("Thể Loại");
    }

    // SHow list category
    private void GetCategoryName(){

        Methods methods = getRetrofit().create(Methods.class);
        Call<List<CategoryModel>> call = methods.getCategory();
        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                list = response.body();

                for(CategoryModel i: list){
                    CategoryModel categoryModel = new CategoryModel();
                    categoryModel.setName(i.getName());
                    adapter.add(categoryModel);
                }
                gdvListNameCategory.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {

            }
        });
    }

    /*// SHow list story
    private void GetCategoryName(){

        Methods methods = getRetrofit().create(Methods.class);
        Call<List<CategoryModel>> call = methods.getCategory();
        call.enqueue(new Callback<List<CategoryModel>>() {
            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                list = response.body();
                for(CategoryModel i: list){
                    adapter.add(i);
                }
                gdvListNameCategory.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {

            }
        });
    }*/
}
