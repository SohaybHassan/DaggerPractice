package com.sw.daggerpractice.ui.main.post;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sw.daggerpractice.R;
import com.sw.daggerpractice.models.Post;
import com.sw.daggerpractice.ui.main.Resource;
import com.sw.daggerpractice.util.VerticalSpacingItemDecoration;
import com.sw.daggerpractice.viewmodel.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostFragment extends DaggerFragment {
    private static final String TAG = PostFragment.class.getSimpleName();

    private PostViewMolde viewMolde;
    private RecyclerView recyclerView;

    @Inject
    PostsRecyclerAdapter postsRecyclerAdapter;
    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        viewMolde = new ViewModelProvider(this, providerFactory).get(PostViewMolde.class);
        initRecyclerView();
        subscribeObserver();
    }


    private void subscribeObserver() {
        viewMolde.observePosts().removeObservers(getViewLifecycleOwner());
        viewMolde.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if (listResource != null) {
                    switch (listResource.status) {
                        case LOADING:
                            Log.d(TAG, "onChanged:  Loading ...");
                            break;
                        case SUCCESS:
                            Log.d(TAG, "onChanged:  get post ...");
                            postsRecyclerAdapter.setPosts(listResource.data);
                            break;
                        case ERROR:
                            Log.d(TAG, "onChanged:  ERROR ...");
                            break;
                    }
                }
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        VerticalSpacingItemDecoration itemDecoration = new VerticalSpacingItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(postsRecyclerAdapter);
    }
}
