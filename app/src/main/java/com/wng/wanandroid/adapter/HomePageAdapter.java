package com.wng.wanandroid.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wng.wanandroid.R;
import com.wng.wanandroid.activity.WebVeiewActivity;
import com.wng.wanandroid.base.BaseRecyclerViewAdapter;
import com.wng.wanandroid.base.BaseViewHolder;
import com.wng.wanandroid.model.ArticleDetailData;

import butterknife.BindView;

public class HomePageAdapter extends BaseRecyclerViewAdapter<ArticleDetailData, RecyclerView.ViewHolder>{
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.articles_list_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    class ItemViewHolder extends BaseViewHolder<ArticleDetailData> {
        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.date)
        TextView date;
        public ItemViewHolder(View itemView) {

            super(itemView);
        }

        @Override
        public void bind(final ArticleDetailData data, int position) {
            super.bind(data, position);

            title.setText(data.getTitle());
            author.setText(data.getAuthor());
            date.setText(data.getNiceDate());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), WebVeiewActivity.class);
                    intent.putExtra("URL", data.getLink());
                    intent.putExtra("TITLE", data.getTitle());
                    itemView.getContext().startActivity(
                            intent,
                            ActivityOptionsCompat.makeClipRevealAnimation(
                                    itemView, 0, 0, itemView.getWidth(),
                                    itemView.getHeight()).toBundle()
                    );
                }
            });

        }
    }
}
