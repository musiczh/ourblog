package com.example.ourblog.view.fragment;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ourblog.MainActivity;
import com.example.ourblog.R;
import com.example.ourblog.model.WanArticleItem;
import com.example.ourblog.util.BottomBarHideManager;
import com.example.ourblog.view.activity.WebActivity;
import com.example.ourblog.viewmodel.MainViewModel;
import com.example.ourblog.viewmodel.WanViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WanFragment extends Fragment {

    private WanViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;
    private TextView mRecycleBotText;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.wan_fragment, container, false);
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
            }
        });
        mViewModel.getRecycleBottomString().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mRecycleBotText.setText(s);
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

    }

    private void init(View view){
        mRecycleBotText=view.findViewById(R.id.recycle_bottom);
        mRecyclerView=view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.getContext()));
        mAdapter=new ArticleAdapter(new ArrayList<WanArticleItem>());
        mRecyclerView.setAdapter(mAdapter);
    }

    public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
        private List<WanArticleItem> mArticleList;
         class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView shareTime;
            TextView author;

            public ViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.title);
                shareTime = view.findViewById(R.id.time);
                author = view.findViewById(R.id.author);
            }
        }

        public ArticleAdapter(List<WanArticleItem> articleList) {
            mArticleList = articleList;
        }

        @NotNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.article_item, parent, false);
            ViewHolder holder=new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=holder.getAdapterPosition();
                    Intent intent = new Intent(MainActivity.getContext(), WebActivity.class);
                    intent.putExtra("link", mArticleList.get(position).getLink());
                    WanFragment.this.startActivity(intent);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
                 WanArticleItem item = mArticleList.get(position);
                 holder.title.setText(item.getTitle());
                 holder.shareTime.setText(item.getNiceShareDate());
                 holder.author.setText(item.getShareUser());

        }

        @Override
        public int getItemCount() {
            return mArticleList.size();
        }

        public void setmArticleList(List<WanArticleItem> mArticleList) {
            this.mArticleList = mArticleList;
            this.notifyDataSetChanged();
        }
    }

}