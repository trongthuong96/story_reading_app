package com.example.story_reading_app.fragment;

import static com.example.lib.RetrofitClient.getRetrofit;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.LayoutInflaterCompat;
import androidx.fragment.app.Fragment;

import com.example.lib.interfaceRepository.Methods;
import com.example.lib.model.StoryModel;
import com.example.story_reading_app.MainActivity;
import com.example.story_reading_app.R;
import com.example.story_reading_app.adapter.StoryAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    //story
    StoryAdapter storyAdapter;
    List<StoryModel> list = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    GridView gdvListStory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        gdvListStory = getView().findViewById(R.id.gdvListStory);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //story
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        storyAdapter = new StoryAdapter(getActivity(), R.layout.item_story);
        GetStory();
        getActivity().setTitle("App Truyện");
    }

    // SHow list story
    private void GetStory(){

        Methods methods = getRetrofit().create(Methods.class);
        Call<List<StoryModel>> call = methods.getStory();
        call.enqueue(new Callback<List<StoryModel>>() {
            @Override
            public void onResponse(Call<List<StoryModel>> call, Response<List<StoryModel>> response) {
                list = response.body();
                for(StoryModel i: list){
                    storyAdapter.add(i);
                }
                gdvListStory.setAdapter(storyAdapter);
            }

            @Override
            public void onFailure(Call<List<StoryModel>> call, Throwable t) {

            }
        });
    }
}
