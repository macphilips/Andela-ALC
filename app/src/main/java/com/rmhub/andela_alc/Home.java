package com.rmhub.andela_alc;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setupFeed();
    }

    private void setupFeed() {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment mFragment = UserResultFragment.newInstance();
        ft.add(R.id.container, mFragment);
        ft.commit();
    }
    public void loadUserProfile(User user){
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment mFragment = UserProfileFragment.newInstance();
        ft.replace(R.id.container, mFragment);
        ft.commit();
    }

    @SuppressWarnings("deprecation")
    @Override
    protected Dialog onCreateDialog(int id) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Searching for Users in Lagos");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        return dialog;
    }

}
