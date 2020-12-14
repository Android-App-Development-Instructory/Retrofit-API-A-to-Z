package com.alaminkarno.retrofitatoz;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitInterface {

    @GET("posts")
    Call<List<Post>> getPost();

    @GET("posts/3/comments")
    Call<List<Comment>> getComment();

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComment1(@Path("id") int userID);

    @GET("comments")
    Call<List<Comment>> getComment(@Query("postId") int postID);

    @GET("comments")
    Call<List<Comment>> getComment(
            @Query("postId") Integer[] postID,
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET("posts")
    Call<List<Post>> getPost(@QueryMap Map<String,String> parameters);

    @GET
    Call<List<Comment>> getComment(@Url String url);
}
