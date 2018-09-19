package com.wng.wanandroid.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
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


public class SearchResultAdapter extends
        BaseRecyclerViewAdapter<ArticleDetailData, SearchResultAdapter.ViewHolder> {


    public void setSearchKeyword() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position), position);
    }

    public class ViewHolder extends BaseViewHolder<ArticleDetailData> {
        @BindView(R.id.title) TextView title;
        @BindView(R.id.author) TextView author;
        @BindView(R.id.date) TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(final ArticleDetailData data, int position) {
            String text =data.getTitle();
//            boolean containKeyword = text.toLowerCase().contains(searchKeyword);
//            if (containKeyword) {
//                SpannableString spannableString = new SpannableString(text);
//                ForegroundColorSpan span = new ForegroundColorSpan(
//                        itemView.getContext().getResources().getColor(R.color.orange, null));
//                int start = text.toLowerCase().indexOf(searchKeyword.toLowerCase());
//                int end = start + searchKeyword.length();
//                spannableString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                title.setText(spannableString);
//            } else {
//                title.setText(text.trim());
//            }
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
