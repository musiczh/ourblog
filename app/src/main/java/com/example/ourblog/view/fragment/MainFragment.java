package com.example.ourblog.view.fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ourblog.MainActivity;
import com.example.ourblog.R;
import com.example.ourblog.model.WanArticleItem;
import com.example.ourblog.viewmodel.MainViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

/**
 * @author singsong
 */
public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private ViewPager2 mViewPager2;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_fragment, container, false);
        init(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new  ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

    }

    private void init(View view){
        mViewPager2=view.findViewById(R.id.pager);
        TabLayout tabLayout=view.findViewById(R.id.tab);
        ViewPagerAdapter adapter= new ViewPagerAdapter(this);
        mViewPager2.setAdapter(adapter);
        String[] titles={"玩安卓","干货集中营"};
        new TabLayoutMediator(tabLayout, mViewPager2, (tab, position) -> tab.setText(titles[position])).attach();
    }

    private class ViewPagerAdapter extends FragmentStateAdapter {

        public ViewPagerAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new WanFragment();
                default:
                    return new Fragment(R.layout.blog_fragment);
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }

    }

}
