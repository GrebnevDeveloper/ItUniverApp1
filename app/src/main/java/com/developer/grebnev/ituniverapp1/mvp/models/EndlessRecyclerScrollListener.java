package com.developer.grebnev.ituniverapp1.mvp.models;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Grebnev on 19.10.2017.
 */

public abstract class EndlessRecyclerScrollListener extends RecyclerView.OnScrollListener {
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

        if (dy > 0 && (totalItemCount - visibleItemCount) <= (firstVisibleItem)) {
            if (totalItemCount < linearLayoutManager.getItemCount() - 100) {
                totalItemCount += 100;
            }
            onLoadMore(totalItemCount);
        }
        else {
            if (dy < 0 && (totalItemCount - 100) >= (firstVisibleItem)) {
                if (totalItemCount > 100) {
                    totalItemCount -= 100;
                }
                onLoadMore(totalItemCount);
            }
        }
    }

    public abstract void onLoadMore(int totalItemCount);
}
