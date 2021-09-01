package com.sw.daggerpractice.ui.main.post;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.sw.daggerpractice.SessionManger;
import com.sw.daggerpractice.network.main.MinaAPi;

import javax.inject.Inject;

public class PostViewMolde extends ViewModel {
    private static final String TAG = PostViewMolde.class.getSimpleName();

    private final SessionManger sessionManger;
    private final MinaAPi minaAPi;


    @Inject
    public PostViewMolde(SessionManger sessionManger, MinaAPi minaAPi) {
        this.sessionManger = sessionManger;
        this.minaAPi = minaAPi;
        Log.d(TAG, "PostViewMolde: PostViewModel is working");
    }
}
