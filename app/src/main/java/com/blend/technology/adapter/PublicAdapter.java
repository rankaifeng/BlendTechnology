package com.blend.technology.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.blend.technology.base.BaseViewHolder;

import java.util.List;


public abstract class PublicAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private Context mContext;
    private int mLayoutId;
    public List<T> mDatas;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    protected PublicAdapter(Context context, int layoutId, List<T> datas) {
        mContext = context;
        mLayoutId = layoutId;
        mDatas = datas;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(mContext, mLayoutId, parent);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setData(List<T> data) {
        mDatas.addAll(data);
        notifyItemInserted(mDatas.size() + 1);
    }

    public void addData(List<T> data) {
        if (mDatas != null && data != null && !data.isEmpty()) {
            mDatas.addAll(data);
        }
    }

    public void clearAndAddData(List<T> data) {
        if (mDatas.size() > 0) {
            mDatas.clear();
        }
        mDatas.addAll(data);
    }

    public void clearData() {
        mDatas.clear();
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, holder, position);
            }
        });
        holder.itemView.setOnLongClickListener(v -> mOnItemLongClickListener != null
                && mOnItemLongClickListener.onItemLongClick(v, holder, position));
        convert(holder, position);
    }

    public abstract void convert(BaseViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);

    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }
}
