package com.alaminkarno.retrofitatoz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    TextView textView;
    List<Post> postList;
    List<Comment> comments;
    RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        //getPost();

        getComment();

    }

    private void getComment() {

        Call<List<Comment>> call = retrofitInterface.getComment("posts/5/comments");

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if(!response.isSuccessful()){
                    textView.setText("Error: "+response.code());

                    return;
                }

                comments = response.body();

                for(Comment comment : comments){
                    String content = "";
                    content += "Post ID: "+comment.getPostId()+" \n";
                    content += "ID: "+comment.getId()+" \n";
                    content += "Name: "+comment.getName()+" \n";
                    content += "Email: "+comment.getEmail()+" \n";
                    content += "Body: "+comment.getBody()+" \n\n";

                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPost() {

        Map<String,String> parameters = new HashMap<>();
        parameters.put("userId","1");
        parameters.put("_sort","id");
        parameters.put("_order","desc");

        Call<List<Post>> call = retrofitInterface.getPost(parameters);

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

        retrofitInterface = ApiClient.getInstance().getApi();
    }


}