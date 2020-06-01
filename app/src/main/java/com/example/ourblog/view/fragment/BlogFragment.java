package com.example.ourblog.view.fragment;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ourblog.MainActivity;
import com.example.ourblog.R;
import com.example.ourblog.viewmodel.BlogViewModel;

/**
 * @author singsong
 */
public class BlogFragment extends Fragment {

    private BlogViewModel mViewModel;
    private MainActivity mainActivity;

    public static BlogFragment newInstance() {
        return new BlogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.blog_fragment, container, false);
        mainActivity = (MainActivity)getActivity();
        viewBinding(view);
        return view;
    }

    private void viewBinding(View view){
        Button button = view.findViewById(R.id.button_hide);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.bottomBarHideManager.hideBar();
            }
        });

        Button button1 = view.findViewById(R.id.button_show);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.bottomBarHideManager.showBar();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BlogViewModel.class);
        // TODO: Use the ViewModel
    }

}
