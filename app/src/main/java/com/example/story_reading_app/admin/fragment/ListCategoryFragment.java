package com.example.story_reading_app.admin.fragment;

import static com.example.lib.RetrofitClient.getRetrofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lib.interfaceRepository.Methods;
import com.example.lib.model.CategoryModel;
import com.example.story_reading_app.FindByStoryWithCategoryActivity;
import com.example.story_reading_app.R;
import com.example.story_reading_app.adapter.CategoryAdapter;
import com.example.story_reading_app.admin.CategoryInsertOrUpdate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCategoryFragment extends Fragment {

    //category
    CategoryAdapter adapter;
    List<CategoryModel> list = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ListView lsvAdminCategory;

    //delete
    CheckBox cbCateDeleteItem;
    List<Boolean> checked;
    int[] categoryIds;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_list_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        lsvAdminCategory = getView().findViewById(R.id.lsvAdminCategory);


        lsvAdminCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //model into detail cate
                CategoryModel model = (CategoryModel) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getActivity(), CategoryInsertOrUpdate.class);
                intent.putExtra("model", model);
                startActivity(intent);
            }
        });

        //delete checkbox
        cbCateDeleteItem = getView().findViewById(R.id.cbCateDeleteItem);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //category
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        adapter = new CategoryAdapter(getActivity(), R.layout.admin_item_list_category);
        getActivity().setTitle("Danh Sách Thể Loại");
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.clear();
        GetCategoryName();
    }

    // get list category
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
                lsvAdminCategory.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {

            }
        });
    }

    //delete
    public void deleteCategory(int[] categoryIds){
        Methods methods = getRetrofit().create(Methods.class);
        Call<int[]> call =  methods.deleteCategory(categoryIds);
        call.enqueue(new Callback<int[]>() {
            @Override
            public void onResponse(Call<int[]> call, Response<int[]> response) {

            }

            @Override
            public void onFailure(Call<int[]> call, Throwable t) {

            }
        });
    }

}
