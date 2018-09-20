package com.wng.wanandroid.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wng.wanandroid.R;
import com.wng.wanandroid.base.BaseRecyclerViewAdapter;
import com.wng.wanandroid.base.BaseViewHolder;
import com.wng.wanandroid.model.ArticleDetailData;
import com.wng.wanandroid.model.CategoryDetailData;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;

public class CategoriesAdapter extends BaseRecyclerViewAdapter<CategoryDetailData, RecyclerView.ViewHolder> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.categories_list_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ItemViewHolder) {
            ((ItemViewHolder) viewHolder).bind(data.get(i), i);
        }
    }

    class ItemViewHolder extends BaseViewHolder<CategoryDetailData> {
        @BindView(R.id.text_category_title) TextView textView;
        @BindView(R.id.flow_layout) TagFlowLayout flowLayout;

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(CategoryDetailData data, int position) {
            super.bind(data, position);
            final List<CategoryDetailData> children = data.getChildren();

            textView.setText(data.getName());
            flowLayout.setAdapter(new TagAdapter<CategoryDetailData>(children) {
                @Override
                public View getView(FlowLayout parent, int position, CategoryDetailData child) {
                    TextView view = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flow_layout
                            , flowLayout, false);
                    if (child==null){
                        return null;
                    }
                    view.setText(child.getName());

                    flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                        @Override
                        public boolean onTagClick(View view, int position, FlowLayout parent) {
                    //        listener.onClick(view, position, parent ,children);
                            return true;
                        }
                    });
                    return view;
                }
            });
        }
    }
}
