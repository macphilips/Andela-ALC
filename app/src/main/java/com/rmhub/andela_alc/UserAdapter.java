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

    private final Context mContext;
    private ImageFetcher mImageFetcher;
    private List<User> cardList;

    public UserAdapter(Context mContext, List<User> cardList, ImageFetcher mImageFetcher) {
        this.mContext = mContext;
        this.mImageFetcher = mImageFetcher;
        this.cardList = cardList;
    }

    public UserAdapter(Context mContext) {
        this(mContext, new ArrayList<User>(), null);
    }

    public UserAdapter(Context mContext, ImageFetcher mImageFetcher) {
        this(mContext, new ArrayList<User>(), mImageFetcher);
    }

    public UserAdapter(Context mContext, List<User> cardList) {
        this(mContext, cardList, null);
    }

    public void setPostFetcher(ImageFetcher mImageFetcher) {
        this.mImageFetcher = mImageFetcher;
    }

    public void setCardList(List<User> cardList) {
        this.cardList = cardList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        User user = cardList.get(position);
        holder.username.setText(user.getUsername());
        if (mImageFetcher != null) {
            mImageFetcher.loadImage(user.getAvatar(), holder.avatar);
        }
        //  holder.card_post.setImageResource(R.drawable.post1);

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }


    @Override
    public void onImageLoaded(boolean success, int pos) {
        if (pos >= 0) {
            this.notifyItemChanged(pos);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username, name;
        public ImageView avatar;

        public MyViewHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.username);
            name = (TextView) view.findViewById(R.id.name);
            avatar = (ImageView) view.findViewById(R.id.user_avatar);
        }
    }
}
