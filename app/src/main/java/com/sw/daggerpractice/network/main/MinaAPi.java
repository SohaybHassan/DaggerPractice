package com.sw.daggerpractice.network.main;

import com.sw.daggerpractice.models.Post;

import java.util.List;
import java.util.Queue;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MinaAPi {


    @GET("comments")
    Flowable<List<Post>> getPostbyId(@Query("postId") int id);


}
