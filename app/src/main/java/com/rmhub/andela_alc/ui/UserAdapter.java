package com.rmhub.andela_alc.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rmhub.andela_alc.R;
import com.rmhub.andela_alc.helper.User;
import com.rmhub.andela_alc.helper.UserResult;
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
    private View.OnClickListener itemClickListener;
    private ImageFetcher mImageFetcher;
    private List<User> userList = new ArrayList<>();
    private int VIEW_TYPE_ITEM = 2;
    private String nextURL;
    private String lastURL;
    private int totalCount, currentCount;


    public UserAdapter(ImageFetcher mImageFetcher) {
        this.mImageFetcher = mImageFetcher;
    }

    public void setItemClickListener(View.OnClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private String getLastURL() {
        return lastURL;
    }

    private void setLastURL(String lastURL) {
        this.lastURL = lastURL;
    }

    private List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = (userList);
        notifyDataSetChanged();
    }

    public void addUserList(List<User> userList) {
        if (userList.size() == 0){

        }
            int end = this.userList.size();
        for (int i = 0, n = userList.size(); i < n; i++) {
            userList.get(i).setId(i + end);
        }
        this.userList.addAll(userList);
        currentCount = userList.size();
        notifyDataSetChanged();
    }

    public void setPostFetcher(ImageFetcher mImageFetcher) {
        this.mImageFetcher = mImageFetcher;
    }

    public String getNextURL() {
        return nextURL;
    }

    private void setNextURL(String nextURL) {
        this.nextURL = nextURL;
    }

    public void searchResult(UserResult result) {
        addUserList(result.getUsers());
        this.setLastURL(result.getHeader().getLast());
        this.setNextURL(result.getHeader().getNext());
        totalCount = result.getTotalCount();

    }

    public boolean hasMoreItemToLoad() {
        return currentCount < totalCount;
    }

    public void removeLastItem() {
        getUserList().remove(getItemCount() - 1);
        notifyItemInserted(getItemCount());
    }

    public void addLastItem(User user) {
        getUserList().add(user);
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            return new MyItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false));
        } else if (viewType == VIEW_TYPE_LOADING) {
            return new MyProgressHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (holder instanceof MyItemHolder) {
            MyItemHolder itemHolder = (MyItemHolder) holder;
            User user = userList.get(position);
            itemHolder.username.setText(String.format("@%s", user.getUsername()));
            itemHolder.name.setText(String.format("id %d", user.getId()));
            // itemHolder.name.setText((user.getName() != null) ? user.getName() : "");
            if (mImageFetcher != null) {
                mImageFetcher.loadImage(user.getAvatarURL(), itemHolder.avatar);
            }
            holder.container.setTag(user);
            if (itemClickListener != null)
                itemHolder.container.setOnClickListener(itemClickListener);
        } else if (holder instanceof MyProgressHolder) {
            MyProgressHolder itemHolder = (MyProgressHolder) holder;
        }
    }

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


    class MyViewHolder extends RecyclerView.ViewHolder {
        View container;

        public MyViewHolder(View itemView) {
            super(itemView);
            container = itemView;
        }
    }

    private class MyItemHolder extends MyViewHolder {
        TextView username, name;
        ImageView avatar;

        MyItemHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.username);
            name = (TextView) view.findViewById(R.id.user_fullname);
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
