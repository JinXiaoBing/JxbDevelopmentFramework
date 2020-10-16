package com.jinxb.developmentframework.binding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class RecyclerViewBindingAdapter {
    @BindingAdapter(value = {"adapter", "submitList", "autoScrollToTopWhenInsert", "autoScrollToBottomWhenInsert"},
            requireAll = false)
    public static void bindList(RecyclerView recyclerView, ListAdapter adapter, List list,
                                boolean autoScrollToTopWhenInsert, boolean autoScrollToBottomWhenInsert) {

        if (recyclerView != null && list != null) {

            if (recyclerView.getAdapter() == null) {

                if (recyclerView.getLayoutManager() != null) {

                    if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                        int spanCount = ((GridLayoutManager) recyclerView.getLayoutManager()).getSpanCount();
                        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), spanCount));

                    } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

                    } else if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                        int spanCount = ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).getSpanCount();
                        int orientation =
                                ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).getOrientation();
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(spanCount, orientation));
                    }
                }

                recyclerView.setAdapter(adapter);

                adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onItemRangeInserted(int positionStart, int itemCount) {
                        if (autoScrollToTopWhenInsert) {
                            recyclerView.scrollToPosition(0);
                        } else if (autoScrollToBottomWhenInsert) {
                            recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount());
                        }
                    }
                });
            }
            adapter.submitList(list);
        }
    }

    @BindingAdapter(value = {"delegate"}, requireAll = false)
    public static void bindDelegate(BGARefreshLayout refreshLayout,
                                    BGARefreshLayout.BGARefreshLayoutDelegate delegate) {
        if (refreshLayout != null && delegate != null) {
            refreshLayout.setDelegate(delegate);
        }
    }

    @BindingAdapter(value = {"onRefreshListener", "onLoadMoreListener","","refreshing","loadingMore"}, requireAll = false)
    public static void initSwipeLoadLayout(SwipeToLoadLayout view, OnRefreshListener onRefreshListener,
                                            OnLoadMoreListener onLoadMoreListener, boolean refreshing,
                                            boolean loadingMore) {
        if (view == null) {
            return;
        }
        if (onRefreshListener != null) {
            view.setOnRefreshListener(onRefreshListener);
        }
        if (onLoadMoreListener != null) {
            view.setOnLoadMoreListener(onLoadMoreListener);
        }
        view.setRefreshing(refreshing);
        view.setLoadingMore(loadingMore);
    }

    @BindingAdapter(value = {"notifyCurrentListChanged"})
    public static void notifyListChanged(RecyclerView recyclerView, boolean notify) {
        if (notify && recyclerView != null && recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }
}
