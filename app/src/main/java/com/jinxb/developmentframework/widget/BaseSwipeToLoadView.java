package com.jinxb.developmentframework.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.jixb.developmentframework.R;

import java.util.List;

public abstract class BaseSwipeToLoadView extends FrameLayout {
    protected SwipeToLoadLayout mSwipeToLoadLayout;
    protected RecyclerView mSwipeTarget;

    public BaseSwipeToLoadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(initLayout(), this);
        mSwipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);
        mSwipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
    }

    protected abstract int initLayout();

    @BindingAdapter(value = {"adapter", "submitList", "autoScrollToTopWhenInsert", "autoScrollToBottomWhenInsert"},
            requireAll = false)
    public static void bindList(BaseSwipeToLoadView swipeToLoadView, ListAdapter adapter, List list,
                                boolean autoScrollToTopWhenInsert, boolean autoScrollToBottomWhenInsert) {
        if (swipeToLoadView == null) {
            return;
        }
        if (swipeToLoadView.mSwipeTarget == null) {
            return;
        }
        if (list == null) {
            return;
        }
        RecyclerView recyclerView = swipeToLoadView.mSwipeTarget;
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

    @BindingAdapter(value = {"onRefreshListener", "onLoadMoreListener","refreshEnabled", "refreshing","loadMoreEnabled","loadingMore"},
            requireAll = false)
    public static void initSwipeLoadLayout(BaseSwipeToLoadView view, OnRefreshListener onRefreshListener,
                                           OnLoadMoreListener onLoadMoreListener, boolean refreshEnabled,
                                           boolean refreshing, boolean loadMoreEnabled, boolean loadingMore) {
        if (view == null) {
            return;
        }
        if (onRefreshListener != null) {
            view.mSwipeToLoadLayout.setOnRefreshListener(onRefreshListener);
        }
        if (onLoadMoreListener != null) {
            view.mSwipeToLoadLayout.setOnLoadMoreListener(onLoadMoreListener);
        }
        view.mSwipeToLoadLayout.setRefreshing(refreshing);
        view.mSwipeToLoadLayout.setRefreshEnabled(refreshEnabled);
        view.mSwipeToLoadLayout.setLoadMoreEnabled(loadMoreEnabled);
        view.mSwipeToLoadLayout.setLoadingMore(loadingMore);
    }
}
