package com.sw.daggerpractice.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sw.daggerpractice.BaseActivity;
import com.sw.daggerpractice.R;
import com.sw.daggerpractice.ui.main.profile.ProfileFragment;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainAcitvity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, "MainActivity", Toast.LENGTH_SHORT).show();

        testProfileFragment();
    }

    private void testProfileFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.contener, new ProfileFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:
                sessionManger.logOut();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
