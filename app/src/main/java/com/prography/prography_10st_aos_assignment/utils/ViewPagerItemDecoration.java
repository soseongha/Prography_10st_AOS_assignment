package com.prography.prography_10st_aos_assignment.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewPagerItemDecoration extends RecyclerView.ItemDecoration {
    private int pageMargin;

    public ViewPagerItemDecoration(int pageMargin) {
        this.pageMargin = pageMargin;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position == 0) {
            outRect.left = pageMargin;
        }
        outRect.right = pageMargin;
    }
}
