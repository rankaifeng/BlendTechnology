package com.blend.technology.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blend.technology.R;
import com.blend.technology.adapter.PublicAdapter;
import com.blend.technology.model.IBaseModel;
import com.blend.technology.utils.FooterViewError;
import com.blend.technology.utils.SpacesItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

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
    View inflate;
    ProgressBar footerProgress;
    TextView footerTvMore, footerTvNomore;
    boolean isShow = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(getLayoutId(), null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = getAdapter();
        mXRecyclerView.setAdapter(mAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(5);
        mXRecyclerView.addItemDecoration(decoration);
        recyFlag = REFRESH_FLAG;
        requestServiceData(PAGE, PAGE_NUMBER);
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
                showAndHide(false);
                requestServiceData(PAGE, PAGE_NUMBER);
            }
        });
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.listview_footer, null);
        mXRecyclerView.setFootView(inflate, new FooterViewError() {
            @Override
            protected void loadingMore(View footerView) {
                footerProgress = footerView.findViewById(R.id.footer_progress);
                footerTvMore = footerView.findViewById(R.id.footer_tv_more);
                footerTvNomore = footerView.findViewById(R.id.footer_tv_nomore);
            }

            @Override
            protected void loadMoreComplete(View footerView) {
                showAndHide(isShow);
            }
        });
    }

    /**
     * 网络请求数据成功
     *
     * @param dataList
     */
    public void requestSuccess(List<T> dataList) {
        isShow = dataList.size() == 0;
        if (recyFlag.equals(LOADMORE_FLAG)) {
            mXRecyclerView.loadMoreComplete();
            // TODO: 2017/12/29 上拉加载
            mAdapter.addData(dataList);
        } else {
            // TODO: 2017/12/29 下拉刷新
            mXRecyclerView.refreshComplete();
            mAdapter.clearAndAddData(dataList);
        }
        mXRecyclerView.notifyItemChanged(dataList.size());
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

    /**
     * 根据数据源来判断底部的暂无数据是否显示
     *
     * @param isShow
     */
    private void showAndHide(boolean isShow) {
        if (isShow) {
            footerProgress.setVisibility(View.GONE);
            footerTvMore.setVisibility(View.GONE);
            footerTvNomore.setVisibility(View.VISIBLE);
            return;
        }
        footerProgress.setVisibility(View.VISIBLE);
        footerTvMore.setVisibility(View.VISIBLE);
        footerTvNomore.setVisibility(View.GONE);
    }
}
