package com.rmhub.andela_alc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rmhub.andela_alc.util.AsyncTask;
import com.rmhub.andela_alc.util.ImageCache;
import com.rmhub.andela_alc.util.ImageFetcher;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class UserResultFragment extends Fragment {

    private static final String IMAGE_CACHE_DIR = "thumbs";
    ImageCache.ImageCacheParams cacheParams = null;
    int mImageThumbSize;
    private ImageFetcher mPostFetcher;
    private ArrayList<User> cardList;
    private UserAdapter mAdapter;

    @SuppressLint("ValidFragment")
    private UserResultFragment() {
    }

    public static UserResultFragment newInstance() {

        Bundle args = new Bundle();
        UserResultFragment fragment = new UserResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cacheParams =
                new ImageCache.ImageCacheParams(getActivity(), IMAGE_CACHE_DIR);
        mImageThumbSize = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
        cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to 25% of app memory
        // The welcome screen for this app (only one that automatically shows)
    }

    private void init(View v) {
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.users_list);
        mPostFetcher = new ImageFetcher(getActivity(), mImageThumbSize);
        mPostFetcher.setLoadingImage(R.drawable.post_background);
        mPostFetcher.addImageCache(getActivity().getSupportFragmentManager(), cacheParams);
        mAdapter = new UserAdapter(getActivity(), mPostFetcher);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        UserSearchQuery query = (new UserSearchQuery.QueryBuilder().setLanguage("Java").setLocation("Lagos")).builder();
        new SearchInBackground().execute(query);
    }

    private class SearchInBackground extends AsyncTask<SearchQuery, Void, SearchResultCallback> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getActivity().showDialog(0);
        }

        @Override
        protected void onPostExecute(SearchResultCallback callback) {
            super.onPostExecute(callback);
            getActivity().dismissDialog(0);
            if (callback instanceof UserSearchResult) {
                mAdapter.setCardList(((UserSearchResult) callback).getUsers());

            }
        }

        @Override
        protected SearchResultCallback doInBackground(SearchQuery... params) {
            UserSearchResult result = new UserSearchResult();
            ConnectionUtil.search(params[0], result);
            return result;
        }
    }
}
