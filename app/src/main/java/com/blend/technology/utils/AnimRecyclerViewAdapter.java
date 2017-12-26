package com.blend.technology.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.blend.technology.R;

/**
 * Created by rankaifeng on 2017/12/26.
 */

public class AnimRecyclerViewAdapter<T extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<T> {
    private static final int DELAY = 138;
    private int mLastPosition = -1;
    private Context mContext;

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void showItemAnim(final View view, final int position) {
        mContext = view.getContext();
        if (position > mLastPosition) {
            view.setAlpha(0);
            view.postDelayed(() -> {
                Animation animation = AnimationUtils.loadAnimation(mContext,
                        R.anim.item);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        view.setAlpha(1);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                view.startAnimation(animation);
            }, DELAY * position);
            mLastPosition = position;
        }
    }
}
