package com.clearday.ywl.isay.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clearday.ywl.isay.AboutActivity;
import com.clearday.ywl.isay.CircleImageView;
import com.clearday.ywl.isay.R;

/**
 * Created by Ywl on 2015/10/28.
 */
public class Tab1Adapter extends RecyclerView.Adapter<Tab1Adapter.ViewHolder> {
    private String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public CardView mCardView;
        public CircleImageView mCircleImageView;
        public TextView mVoiceName;
        public TextView mVoiceDate;
        public TextView mVoiceText;
        public LinearLayout mVoiceContent;
        public LinearLayout mFavorites;
        public LinearLayout mComments;
        public LinearLayout mLikes;
        public TextView mFavoritesCount;
        public TextView mCommentsCount;
        public TextView mLikesCount;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            mCardView = (CardView)v.findViewById(R.id.cardview);
            mCircleImageView = (CircleImageView)v.findViewById(R.id.voice_circleImageView);
            mVoiceName = (TextView)v.findViewById(R.id.voice_name);
            mVoiceDate = (TextView)v.findViewById(R.id.voice_date);
            mVoiceText = (TextView)v.findViewById(R.id.voice_text);
            mVoiceContent = (LinearLayout)v.findViewById(R.id.voice_content);
            mFavorites = (LinearLayout)v.findViewById(R.id.favorites);
            mComments = (LinearLayout)v.findViewById(R.id.comments);
            mLikes = (LinearLayout)v.findViewById(R.id.likes);
            mFavoritesCount = (TextView)v.findViewById(R.id.favorites_count);
            mCommentsCount = (TextView)v.findViewById(R.id.comments_count);
            mLikesCount = (TextView)v.findViewById(R.id.likes_count);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Tab1Adapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Tab1Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.array_item_surrounding, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mTextView.setText(mDataset[position]);
        holder.mVoiceContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, AboutActivity.class);
                context.startActivity(intent);
            }
        });

        holder.mFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 15/11/25
                holder.mFavoritesCount.setText(Integer.parseInt(holder.mFavoritesCount.getText().toString()) + 1 + "");
            }
        });

        holder.mComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 15/11/25
                holder.mCommentsCount.setText(Integer.parseInt(holder.mCommentsCount.getText().toString())+1+"");
            }
        });

        holder.mLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 15/11/25
                holder.mLikesCount.setText(Integer.parseInt(holder.mLikesCount.getText().toString())+1+"");
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}