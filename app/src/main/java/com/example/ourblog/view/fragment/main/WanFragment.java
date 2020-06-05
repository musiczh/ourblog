package com.example.ourblog.view.fragment.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ourblog.MainActivity;
import com.example.ourblog.R;
import com.example.ourblog.model.bean.WanArticleItem;
import com.example.ourblog.view.activity.WebActivity;
import com.example.ourblog.viewmodel.mainviewmodel.WanViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WanFragment extends Fragment {

    private WanViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.ariticle_iten_fragment, container, false);
        init(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WanViewModel.class);
        // TODO: Use the ViewModel
        mViewModel.getItems().observe(getViewLifecycleOwner(), new Observer<List<WanArticleItem>>() {
            @Override
            public void onChanged(List<WanArticleItem> items) {
                mAdapter.setmArticleList(items);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mViewModel.getRecycleBottomString().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mAdapter.setBottomText(s);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy<=0){
                        ((MainActivity) Objects.requireNonNull(getActivity())).bottomBarHideManager.showBar();
                }else{
                    ((MainActivity) Objects.requireNonNull(getActivity())).bottomBarHideManager.hideBar();
                    if(!recyclerView.canScrollVertically(1)){
                        mViewModel.getMoreItem();
                    }
                }
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mViewModel.refreshItems();
            }
        });

    }

    private void init(View view){
        mRecyclerView=view.findViewById(R.id.recycler_view);
        mSwipeRefreshLayout=view.findViewById(R.id.refresh);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.getContext()));
        mAdapter=new ArticleAdapter(new ArrayList<WanArticleItem>(),this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public static class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
        private List<WanArticleItem> mArticleList;
        private String bottomText="正在努力加载...";
        private Fragment mFragment;

         static class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView shareTime;
            TextView author;
            TextView bottom;
            View view;

            public ViewHolder(View view, int viewType) {
                super(view);
                this.view=view;
                if(viewType==R.layout.wan_article_item){
                    title = view.findViewById(R.id.title);
                    shareTime = view.findViewById(R.id.time);
                    author = view.findViewById(R.id.author);
                }else{
                    bottom=view.findViewById(R.id.recycle_bottom);
                }
            }
        }

        public ArticleAdapter(List<WanArticleItem> articleList, Fragment fragment) {
            mArticleList = articleList;
            mFragment=fragment;
        }

        @NotNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(viewType, parent, false);
            ViewHolder holder= new ViewHolder(view, viewType);
            int position=holder.getAdapterPosition();


            return holder;
        }

        @Override
        public void onBindViewHolder(@NotNull ViewHolder holder, int position) {

            if(position<mArticleList.size()){
                WanArticleItem item = mArticleList.get(position);
                holder.title.setText(item.getTitle());
                holder.shareTime.setText(item.getNiceShareDate());
                holder.author.setText(item.getShareUser());
                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(MainActivity.getContext(), WebActivity.class);
                        intent.putExtra("link", mArticleList.get(position).getLink());
                        mFragment.startActivity(intent);
                    }
                });

            }else{
                holder.bottom.setText(bottomText);
            }


        }

        @Override
        public int getItemCount() {
            return mArticleList.size()+1;
        }

        public void setmArticleList(List<WanArticleItem> mArticleList) {
            this.mArticleList = mArticleList;
            this.notifyDataSetChanged();
        }

        public void setBottomText(String bottomText) {
            this.bottomText = bottomText;
            this.notifyItemChanged(mArticleList.size());
        }

        @Override
        public int getItemViewType(int position) {
            if(position==mArticleList.size()){
                return R.layout.recycle_bottom;
            }else{
                return R.layout.wan_article_item;
            }

        }
    }
}