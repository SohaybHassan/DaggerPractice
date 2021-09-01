package com.sw.daggerpractice.ui.main.post;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.sw.daggerpractice.SessionManger;
import com.sw.daggerpractice.models.Post;
import com.sw.daggerpractice.network.main.MinaAPi;
import com.sw.daggerpractice.ui.main.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostViewMolde extends ViewModel {
    private static final String TAG = PostViewMolde.class.getSimpleName();

    private final SessionManger sessionManger;
    private final MinaAPi minaAPi;
    private MediatorLiveData<Resource<List<Post>>> posts;

    @Inject
    public PostViewMolde(SessionManger sessionManger, MinaAPi minaAPi) {
        this.sessionManger = sessionManger;
        this.minaAPi = minaAPi;
        Log.d(TAG, "PostViewMolde: PostViewModel is working");
    }

    public LiveData<Resource<List<Post>>> observePosts() {
        if (posts == null) {
            posts = new MediatorLiveData<>();
            posts.setValue(Resource.loading((List<Post>) null));
            final LiveData<Resource<List<Post>>> source = LiveDataReactiveStreams.fromPublisher(
                    minaAPi.getPostbyId(sessionManger.getAuthUser().getValue().data.getId())
                            .onErrorReturn(new Function<Throwable, List<Post>>() {
                                @Override
                                public List<Post> apply(@NonNull Throwable throwable) throws Exception {
                                    Log.e(TAG, "apply: ", throwable);
                                    Post post = new Post();
                                    post.setId(-1);
                                    ArrayList<Post> posts = new ArrayList<>();
                                    posts.add(post);
                                    return posts;
                                }
                            })
                            .map(new Function<List<Post>, Resource<List<Post>>>() {
                                @Override
                                public Resource<List<Post>> apply(@NonNull List<Post> posts) throws Exception {
                                    if (posts.size() > 0) {
                                        if (posts.get(0).getId() == -1) {
                                            return Resource.error("spme Error her", null);
                                        }
                                    }
                                    return Resource.success(posts);
                                }
                            })
                    .subscribeOn(Schedulers.io())
            );
            posts.addSource(source, new Observer<Resource<List<Post>>>() {
                @Override
                public void onChanged(Resource<List<Post>> listResource) {
                    posts.setValue(listResource);
                    posts.removeSource(source);
                }
            });
        }

        return posts;
    }
}
