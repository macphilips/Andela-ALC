package com.rmhub.andela_alc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rmhub.andela_alc.util.ImageFetcher;
import com.rmhub.andela_alc.util.ImageWorker;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by MOROLANI on 11/2/2016
 * <p/>
 * owm
 * .
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> implements ImageWorker.OnImageLoadedListener {

    private static final int VIEW_TYPE_LOADING = 1;
    private final Context mContext;
    private ImageFetcher mImageFetcher;
    private List<User> userList;
    private int VIEW_TYPE_ITEM = 2;

    public UserAdapter(Context mContext, List<User> userList, ImageFetcher mImageFetcher) {
        this.mContext = mContext;
        this.mImageFetcher = mImageFetcher;
        this.userList = userList;
    }

    public UserAdapter(Context mContext) {
        this(mContext, new ArrayList<User>(), null);
    }

    public UserAdapter(Context mContext, ImageFetcher mImageFetcher) {
        this(mContext, new ArrayList<User>(), mImageFetcher);
    }

    public UserAdapter(Context mContext, List<User> userList) {
        this(mContext, userList, null);
    }

    public void setPostFetcher(ImageFetcher mImageFetcher) {
        this.mImageFetcher = mImageFetcher;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
            return new MyItemHolder(itemView);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item, parent, false);
            return new MyProgressHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (holder instanceof MyItemHolder) {
            MyItemHolder itemHolder = (MyItemHolder) holder;
            User user = userList.get(position);
            itemHolder.username.setText(String.format("@%s", user.getUsername()));
            itemHolder.name.setText((user.getName() != null) ? user.getName() : "");
            if (mImageFetcher != null) {
                mImageFetcher.loadImage(user.getAvatarURL(), itemHolder.avatar);
            }
        } else if (holder instanceof MyProgressHolder) {
            MyProgressHolder itemHolder = (MyProgressHolder) holder;

        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return userList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public void onImageLoaded(boolean success, int pos) {
        if (pos >= 0) {
            this.notifyItemChanged(pos);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class MyItemHolder extends MyViewHolder {
        public TextView username, name;
        public ImageView avatar;

        public MyItemHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.username);
            name = (TextView) view.findViewById(R.id.name);
            avatar = (ImageView) view.findViewById(R.id.user_avatar);
        }
    }

    public class MyProgressHolder extends MyViewHolder {
        public TextView username, name;
        public ImageView avatar;

        public MyProgressHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.username);
            name = (TextView) view.findViewById(R.id.name);
            avatar = (ImageView) view.findViewById(R.id.user_avatar);
        }
    }
}
