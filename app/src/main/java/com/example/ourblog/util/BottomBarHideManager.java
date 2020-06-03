package com.example.ourblog.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * @author singsong
 */
public class BottomBarHideManager {
    private boolean isNotAnimating = true;
    private boolean isNotHide = true;
    private int animationTime = 500;

    private View mView;

    public BottomBarHideManager(View view){
        mView = view;
    }

    public void hideBar(){
        if (isNotAnimating && isNotHide){
            isNotAnimating = false;
            AnimatorSet set = new AnimatorSet();
            set.playTogether(
                    ObjectAnimator.ofFloat(mView,"alpha",1,0),
                    ObjectAnimator.ofFloat(mView,"translationY",mView.getHeight())
            );
            set.setDuration(animationTime);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mView.setVisibility(View.GONE);
                    isNotAnimating = true;
                    isNotHide = false;
                }
            });
            set.start();
        }
    }

    public void showBar(){
        if (isNotAnimating && !isNotHide){
            mView.setAlpha(0);
            if (mView.getVisibility()==View.GONE){
                mView.setVisibility(View.VISIBLE);
            }
            isNotAnimating = false;
            AnimatorSet set = new AnimatorSet();
            set.playTogether(
                    ObjectAnimator.ofFloat(mView,"alpha",0,1),
                    ObjectAnimator.ofFloat(mView,"translationY",mView.getHeight(),0)
            );
            set.setDuration(animationTime);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    isNotAnimating = true;
                    isNotHide = true;
                }
            });
            set.start();
        }
    }


}
