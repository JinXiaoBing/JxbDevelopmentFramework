package com.jinxb.developmentframework.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class RefreshView extends FrameLayout {
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private ImageView mIvArrow;
    private ImageView mIvSuccess;
    private ProgressBar mProgressbar;
    private TextView mTvRefresh;
    private RecyclerView mSwipeTarget;

    public RefreshView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_refresh_view, this);
        mSwipeToLoadLayout = (SwipeToLoadLayout) findViewById(R.id.swipeToLoadLayout);
        mIvArrow = (ImageView) findViewById(R.id.ivArrow);
        mIvSuccess = (ImageView) findViewById(R.id.ivSuccess);
        mProgressbar = (ProgressBar) findViewById(R.id.progressbar);
        mTvRefresh = (TextView) findViewById(R.id.tvRefresh);
        mSwipeTarget = (RecyclerView) findViewById(R.id.swipe_target);
    }

    @BindingAdapter(value = {"adapter", "submitList", "autoScrollToTopWhenInsert", "autoScrollToBottomWhenInsert"},
            requireAll = false)
    public static void bindList(RefreshView refreshView, ListAdapter adapter, List list,
                                boolean autoScrollToTopWhenInsert, boolean autoScrollToBottomWhenInsert) {
        if (refreshView == null) {
            return;
        }
        if (refreshView.mSwipeTarget == null) {
            return;
        }
        if (list == null) {
            return;
        }
        RecyclerView recyclerView = refreshView.mSwipeTarget;
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


    @BindingAdapter(value = {"onRefreshListener", "refreshing"},
            requireAll = false)
    public static void initSwipeLoadLayout(RefreshView view, OnRefreshListener onRefreshListener, boolean refreshing) {
        if (view == null) {
            return;
        }
        if (onRefreshListener != null) {
            view.mSwipeToLoadLayout.setOnRefreshListener(onRefreshListener);
        }
        view.mSwipeToLoadLayout.setRefreshing(refreshing);
    }
}
