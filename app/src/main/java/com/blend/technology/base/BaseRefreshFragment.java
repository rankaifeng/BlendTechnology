package com.blend.technology.base;

import android.accounts.NetworkErrorException;
import android.net.ParseException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blend.technology.R;
import com.blend.technology.adapter.PublicAdapter;
import com.blend.technology.model.IBaseModel;
import com.blend.technology.utils.SpacesItemDecoration;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import butterknife.BindView;

/**
 * unknown on 2017/12/29.
 */
public abstract class BaseRefreshFragment<T, P extends BasePresenter, M extends IBaseModel>
        extends BaseMVPCompatFragment<P, M> {
    private static final String REFRESH_FLAG = "refresh";//下拉刷新标识
    private static final String LOADMORE_FLAG = "loadMore";//上拉加载更多标识
    private static int PAGE = 1;//要显示的页数
    private static int PAGE_NUMBER = 10;//每一页要显示的条数
    private String recyFlag;
    @BindView(R.id.fra_recycler)
    public XRecyclerView mXRecyclerView;
    public PublicAdapter<T> mAdapter;
    boolean isShow = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(getLayoutId(), null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
        mAdapter = getAdapter();
        mXRecyclerView.setAdapter(mAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(5);
        mXRecyclerView.addItemDecoration(decoration);
        recyFlag = REFRESH_FLAG;
        requestServiceData(PAGE, PAGE_NUMBER);
//        ArrowRefreshHeader header = new ArrowRefreshHeader(getActivity());
//        header.setProgressStyle(ProgressStyle.BallGridBeat);
//        header.setArrowImageView(R.drawable.point_s);
//        mXRecyclerView.setRefreshHeader(header);
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                recyFlag = REFRESH_FLAG;
                PAGE = 1;
                requestServiceData(PAGE, PAGE_NUMBER);
            }

            @Override
            public void onLoadMore() {
                recyFlag = LOADMORE_FLAG;
                if (!isShow)
                    PAGE++;
                requestServiceData(PAGE, PAGE_NUMBER);
            }
        });
    }

    /**
     * 网络请求数据成功
     *
     * @param dataList
     */
    public void requestSuccess(List<T> dataList) {
        hideProgress();
        isShow = dataList.size() == 0;
        if (dataList.size() > 0) {
            if (isLoadOrRefresh()) {
                mXRecyclerView.loadMoreComplete();
                // TODO: 2017/12/29 上拉加载
                mAdapter.addData(dataList);
            } else {
                // TODO: 2017/12/29 下拉刷新
                mXRecyclerView.setLoadingMoreEnabled(true);
                mXRecyclerView.refreshComplete();
                mAdapter.clearAndAddData(dataList);
            }
            mXRecyclerView.notifyItemChanged(dataList.size());
        } else {
            mXRecyclerView.setLoadingMoreEnabled(false);
            mXRecyclerView.loadMoreComplete();
        }
    }

    public void requestFail(Throwable t) {
        hideProgress();
        if (t instanceof NetworkErrorException ||
                t instanceof UnknownHostException
                || t instanceof ConnectException) {
            showToast("网络异常");
        } else if (t instanceof SocketTimeoutException
                || t instanceof InterruptedIOException
                || t instanceof TimeoutException) {
            showToast("请求超时");
        } else if (t instanceof JsonSyntaxException) {
            showToast("请求不合法");
        } else if (t instanceof JsonParseException
                || t instanceof JSONException
                || t instanceof ParseException) {   //  解析错误
            showToast("解析错误");
        } else {
            showToast("连接服务器失败");
        }
        if (isLoadOrRefresh()) {
            mXRecyclerView.loadMoreComplete();
        } else {
            mXRecyclerView.refreshComplete();
        }
        isShow = true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_food;
    }

    /**
     * 获取当前adapter
     */
    protected abstract PublicAdapter<T> getAdapter();

    protected abstract void requestServiceData(int page, int record);

    protected abstract void showLoading();

    private boolean isLoadOrRefresh() {
        return recyFlag.equals(LOADMORE_FLAG);
    }
}
