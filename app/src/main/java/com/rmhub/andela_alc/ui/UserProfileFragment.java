package com.rmhub.andela_alc.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rmhub.andela_alc.R;
import com.rmhub.andela_alc.helper.User;
import com.rmhub.andela_alc.util.ImageCache;
import com.rmhub.andela_alc.util.ImageFetcher;

/**
 * Created by MOROLANI on 3/8/2017
 * <p>
 * owm
 * .
 */

public class UserProfileFragment extends Fragment {
    private static final String LOAD_USER = "user profile";
    private static final String IMAGE_CACHE_DIR = "thumbs";
    ImageCache.ImageCacheParams cacheParams = null;
    int mImageThumbSize;
    private User user;
    private ShareActionProvider mShareActionProvider;
    private ImageFetcher imageFetcher;

    public static UserProfileFragment newInstance(User user) {
        Bundle args = new Bundle();
        args.putParcelable(LOAD_USER, user);
        UserProfileFragment fragment = new UserProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = getArguments().getParcelable(LOAD_USER);
        cacheParams =
                new ImageCache.ImageCacheParams(getActivity(), IMAGE_CACHE_DIR);
        mImageThumbSize = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
        cacheParams.setMemCacheSizePercent(0.25f);
        imageFetcher = new ImageFetcher(getActivity(), mImageThumbSize);
        imageFetcher.setLoadingImage(R.drawable.post_background);
        imageFetcher.addImageCache(getActivity().getSupportFragmentManager(), cacheParams);

    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView avatar = (ImageView) view.findViewById(R.id.user_avatar);
        imageFetcher.loadImage(user.getAvatarURL(), avatar);
        TextView username = (TextView) view.findViewById(R.id.username);
        username.setText(String.format("@%s", user.getUsername()));
        TextView url = (TextView) view.findViewById(R.id.user_url);
        url.setText(String.format("%s", user.getHtmlURL()));
        View share = view.findViewById(R.id.share_button);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                String msg = String.format("Check out this awesome developer @%s, %s.", user.getUsername(), user.getHtmlURL());
                sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_profile, container, false);
    }
}
