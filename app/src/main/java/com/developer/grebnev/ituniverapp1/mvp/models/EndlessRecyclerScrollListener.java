package com.developer.grebnev.ituniverapp1.mvp.models;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.developer.grebnev.ituniverapp1.consts.EndlessRecyclerConstants;

/**
 * Created by Grebnev on 19.10.2017.
 */

public abstract class EndlessRecyclerScrollListener extends RecyclerView.OnScrollListener {
    private static final int VISIBLE_THRESHOLD = 10;
    private int visibleItemCount;
    private int totalItemCount;
    private int firstVisibleItem;

    private LinearLayoutManager linearLayoutManager;

    public EndlessRecyclerScrollListener(LinearLayoutManager linearLayoutManager, int totalItemCount) {
        this.linearLayoutManager = linearLayoutManager;
        this.totalItemCount = totalItemCount;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

        if (dy > 0 && (totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
            if (totalItemCount <= linearLayoutManager.getItemCount()) {
                totalItemCount += EndlessRecyclerConstants.VOLUME_LOAD;
            }
            onLoadMore(totalItemCount);
        }
        else {
            if (dy < 0 && (totalItemCount - EndlessRecyclerConstants.VOLUME_LOAD) >= (firstVisibleItem - VISIBLE_THRESHOLD)) {
                if (totalItemCount >= EndlessRecyclerConstants.VOLUME_LOAD) {
                    totalItemCount -= EndlessRecyclerConstants.VOLUME_LOAD;
                }
                onLoadMore(totalItemCount);
            }
        }
    }

    public abstract void onLoadMore(int totalItemCount);
}
