package com.alaminkarno.retrofitatoz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    TextView textView;
    List<Post> postList;
    RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        getPost();

    }

    private void getPost() {

        retrofitInterface = ApiClient.getInstance().getApi();

        Call<List<Post>> call = retrofitInterface.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.isSuccessful()){
                    textView.setText("Error: "+response.code());

                    return;
                }

                postList = response.body();

                for(Post post : postList){
                    String content = "";

                    content += "ID: "+post.getId() +" \n";
                    content += "User ID : "+post.getUserId() +" \n";
                    content += "Title : "+post.getTitle() +" \n";
                    content += "Body : "+post.getBody() +" \n\n";

                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void init() {
        textView = findViewById(R.id.textView);
        postList = new ArrayList<>();
    }


}