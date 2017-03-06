package com.rmhub.andela_alc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rmhub.andela_alc.util.ImageCache;
import com.rmhub.andela_alc.util.ImageFetcher;

public class MainActivity extends AppCompatActivity {

    private ImageCache.ImageCacheParams cacheParams = null;
    private int mImageThumbSize;
    private ImageFetcher mPostFetcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupFeed();
    }

    private void setupFeed() {
        mPostFetcher = new ImageFetcher(this, mImageThumbSize);
        mPostFetcher.setLoadingImage(R.drawable.post_background);
        mPostFetcher.addImageCache(this.getSupportFragmentManager(), cacheParams);
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment mFragment = MainActivityFragment.newInstance();
        ft.add(R.id.container, mFragment);
        ft.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
