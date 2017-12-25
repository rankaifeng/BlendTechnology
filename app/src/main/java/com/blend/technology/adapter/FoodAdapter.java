package com.blend.technology.adapter;

import android.content.Context;

import com.blend.technology.R;
import com.blend.technology.base.BaseViewHolder;
import com.blend.technology.bean.FoodOut;
import com.blend.technology.widgets.RatioImageView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rankaifeng on 2017/12/25.
 */

public class FoodAdapter extends PublicAdapter<FoodOut.Data> {
    Context mContext;
    List<FoodOut.Data> dataList;
    private List<Integer> mHeights = new ArrayList<>();
    private List<Integer> mWidths = new ArrayList<>();

    public FoodAdapter(Context context, int layoutId, List<FoodOut.Data> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
        this.dataList = datas;
    }

    @Override
    public void convert(BaseViewHolder holder, int position) {
        // 随机高度, 模拟瀑布效果.
        if (mHeights.size() <= position) {
            mHeights.add((int) (300 + Math.random() * 100));
            mWidths.add((int) (300 + Math.random() * 100));
        }
        RatioImageView mImageView = holder.getView(R.id.frg_food_img);
        holder.setText(R.id.frg_food_title, dataList.get(position).getTitle());
        mImageView.setOriginalSize(mWidths.get(position), mHeights.get(position));
        Glide.with(mContext).load(dataList.get(position).getImgUrl()).into(mImageView);
    }
}
