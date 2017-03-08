package com.rmhub.andela_alc;

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
    private ImageFetcher mImageFetcher;
    private List<User> userList;
    private int VIEW_TYPE_ITEM = 2;
    private String nextURL;
    private String lastURL;

    public UserAdapter(List<User> userList, ImageFetcher mImageFetcher) {
        this.mImageFetcher = mImageFetcher;
        this.userList = userList;
    }

    public UserAdapter() {
        this(new ArrayList<User>(), null);
    }

    public UserAdapter(ImageFetcher mImageFetcher) {
        this(new ArrayList<User>(), mImageFetcher);
    }

    public UserAdapter(List<User> userList) {
        this(userList, null);
    }

    public String getLastURL() {
        return lastURL;
    }

    public void setLastURL(String lastURL) {
        this.lastURL = lastURL;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public void setPostFetcher(ImageFetcher mImageFetcher) {
        this.mImageFetcher = mImageFetcher;
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
        return (userList == null) ? 0 : userList.size();
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

    public String getNextURL() {
        return nextURL;
    }

    public void setNextURL(String nextURL) {
        this.nextURL = nextURL;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class MyItemHolder extends MyViewHolder {
        TextView username, name;
        ImageView avatar;

        MyItemHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.username);
            name = (TextView) view.findViewById(R.id.name);
            avatar = (ImageView) view.findViewById(R.id.user_avatar);
        }
    }

    private class MyProgressHolder extends MyViewHolder {
        View progressContainer, loadMoreContain;

        MyProgressHolder(View view) {
            super(view);
            progressContainer = view.findViewById(R.id.progress_container);
            loadMoreContain = view.findViewById(R.id.load_container);
        }
    }
}
