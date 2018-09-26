package com.wng.wanandroid.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wng.wanandroid.R;
import com.wng.wanandroid.base.BaseRecyclerViewAdapter;
import com.wng.wanandroid.base.BaseViewHolder;
import com.wng.wanandroid.model.PicturesData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PicturesAdapter extends BaseRecyclerViewAdapter<PicturesData.PictureDetailData, RecyclerView.ViewHolder> {
    private static final String TAG = "PicturesAdapter";
    private List<String> imageUrlList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.picture_list_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ItemViewHolder) {
            ((ItemViewHolder) viewHolder).bind(data.get(i), i);
        }
    }

    class ItemViewHolder extends BaseViewHolder<PicturesData.PictureDetailData> {
        @BindView(R.id.picture)
        ImageView picture;

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(PicturesData.PictureDetailData data, int position) {
            super.bind(data, position);
            Log.d(TAG, "bind: " + data.getUrl());
            Glide.with(itemView.getContext())
                    .load(data.getUrl()).apply(RequestOptions.fitCenterTransform())
                    .into(picture);
        }
    }
}
