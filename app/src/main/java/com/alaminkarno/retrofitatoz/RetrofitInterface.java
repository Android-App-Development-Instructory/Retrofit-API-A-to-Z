package com.alaminkarno.retrofitatoz;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("posts")
    Call<List<Post>> getPost();
}
