package com.example.lernretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView txtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtValue = findViewById(R.id.txt_value);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/").
                        addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi service = retrofit.create(JsonPlaceHolderApi.class);

        //1 DÙng GET
        // kind 1
        // getPost(service);

        // kind 2: request có điều kiên
        // getPostComment(service);

        //kind 3: request theo userId
        //  getPostUserId(service);

        // Dùng POST
        getPostTest(service);

    }

    private void getPostUserId(JsonPlaceHolderApi service) {
        Call<List<Post>> callApi = service.getPostUserId(2);
        callApi.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    txtValue.setText("Code" + response.code());
                    return;
                }

                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    txtValue.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                txtValue.setText(t.getMessage());
            }
        });
    }

    private void getPostComment(JsonPlaceHolderApi service) {
        Call<List<CommentTest>> callApi = service.getPostComment(4);
        callApi.enqueue(new Callback<List<CommentTest>>() {
            @Override
            public void onResponse(Call<List<CommentTest>> call, Response<List<CommentTest>> response) {
                if (!response.isSuccessful()) {
                    txtValue.setText("Code" + response.code());
                    return;
                }

                List<CommentTest> commentTests = response.body();
                for (CommentTest commentTest : commentTests) {
                    String content = "";
                    content += "ID: " + commentTest.getId() + "\n";
                    content += "Name: " + commentTest.getName() + "\n";
                    content += "Body: " + commentTest.getBody() + "\n";
                    content += "Email: " + commentTest.getEmail() + "\n";
                    content += "postId: " + commentTest.getPostId() + "\n\n";
                    txtValue.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<CommentTest>> call, Throwable t) {
                txtValue.setText(t.getMessage());
            }
        });
    }

    private void getPost(JsonPlaceHolderApi service) {
        Call<List<Post>> callApi = service.getPost();
        callApi.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    txtValue.setText("Code" + response.code());
                    return;
                }

                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";
                    txtValue.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                txtValue.setText(t.getMessage());
            }
        });
    }

    private void getPostTest(JsonPlaceHolderApi service) {
        Post post = new Post(23, "New Title", "New Text");
        Call<Post> callApi = service.getPostTest(post);
        callApi.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    txtValue.setText("Code" + response.code());
                    return;
                }

                Post postsRepon = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postsRepon.getId() + "\n";
                content += "User ID: " + postsRepon.getUserId() + "\n";
                content += "Title: " + postsRepon.getTitle() + "\n";
                content += "Text: " + postsRepon.getText() + "\n\n";
                txtValue.setText(content);


            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                txtValue.setText(t.getMessage());
            }
        });
    }
}
