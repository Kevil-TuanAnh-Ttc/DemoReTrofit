package com.example.lernretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface JsonPlaceHolderApi {
    //1 GET
    @GET("posts")
    Call<List<Post>> getPost();

    @GET("posts")
    Call<List<Post>> getPostUserId(@Query("userId") int userId);

    // trả về dữ liệu sắp xếp theo ý muốn
    @GET("posts")
    Call<List<Post>> getPostUserIdSortId(@Query("userId") int userId,
                                         @Query("_sort") String sort,
                                         @Query("_order") int order);


    @GET("posts/{id}/comments")
    Call<List<CommentTest>> getPostComment(@Path("id") int postId);

    // Request lên nếu muốn truyền url, giả sử truyền lên và lấy value object id = 1 => url posts/{1}/comments
    @GET
    Call<List<CommentTest>> getPostCommentT(@Url String url);

    // 2 POST
    @POST("posts")
    Call<Post> getPostTest(@Body Post post);
}
