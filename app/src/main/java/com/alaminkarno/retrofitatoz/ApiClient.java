package com.alaminkarno.retrofitatoz;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static ApiClient mInstance;
    private Retrofit retrofit;

    private ApiClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiClient getInstance(){
        if(mInstance == null){
            mInstance = new ApiClient();
        }

        return mInstance;
    }

    public RetrofitInterface getApi(){

        return retrofit.create(RetrofitInterface.class);
    }
}
