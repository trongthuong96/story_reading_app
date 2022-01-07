package com.example.lib.interfaceRepository;


import com.example.lib.model.CategoryModel;
import com.example.lib.model.ChapterModel;
import com.example.lib.model.StoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Methods {
    // list category
    @GET("api/category")
    Call<List<CategoryModel>> getCategory();

    // update category
    @PUT("api/category")
    Call<CategoryModel> putCategory(@Body CategoryModel model);

    //insert category
    @POST("api/category")
    Call<CategoryModel> postCategory(@Body CategoryModel model);

    //delete category
    @DELETE("api/category")
    Call<int[]> deleteCategory(@Body int[] categoryIds);

   /* ---------------------------------------------------------------------------------*/

    //list story
    @GET("api/story")
    Call<List<StoryModel>> getStory();

    // search story
    @GET("api/timkiem")
    Call<List<StoryModel>> searchStory(@Query("theloai") Integer categoryId, @Query("tentruyen") String storyName, @Query("tacgia") String author, @Query("tomtat") String summary);

    //list chapter
    @GET("api/chapter/{id}")
    Call<List<ChapterModel>> getChapter(@Path("id") Long id);
}
