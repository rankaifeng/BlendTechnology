package com.blend.technology.adapter;

import android.content.Context;

import com.blend.technology.R;
import com.blend.technology.base.BaseViewHolder;
import com.blend.technology.bean.FoodOut;
import com.blend.technology.widgets.RatioImageView;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by rankaifeng on 2017/12/25.
 */

public class FoodAdapter extends PublicAdapter<FoodOut.Data> {
    Context mContext;
    List<FoodOut.Data> dataList;

    public FoodAdapter(Context context, int layoutId, List<FoodOut.Data> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
        this.dataList = datas;
    }

    @Override
    public void convert(BaseViewHolder holder, int position) {
        holder.setText(R.id.frg_food_title, dataList.get(position).getTitle());
        RatioImageView mImageView = holder.getView(R.id.frg_food_img);
        mImageView.setOriginalSize(50,50);
        Glide.with(mContext).load(dataList.get(position).getImgUrl()).into(mImageView);
    }
}
