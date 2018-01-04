package com.blend.technology.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.blend.technology.R;
import com.blend.technology.base.BaseViewHolder;
import com.blend.technology.bean.FoodOut;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * unknown on 2018/1/4.
 */
public class FoodDetailAdapter extends PublicAdapter<FoodOut.Steps> {
    private List<FoodOut.Steps> stepsList;
    private Context mContext;

    public FoodDetailAdapter(Context context, int layoutId, List<FoodOut.Steps> datas) {
        super(context, layoutId, datas);
        this.stepsList = datas;
        this.mContext = context;
    }

    @Override
    public void convert(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv_food_detail_steps, stepsList.get(position).getStep());
        ImageView imageView = holder.getView(R.id.img_food_detail_steps);
        Glide.with(mContext).load(stepsList.get(position).getImg()).into(imageView);
    }
}
