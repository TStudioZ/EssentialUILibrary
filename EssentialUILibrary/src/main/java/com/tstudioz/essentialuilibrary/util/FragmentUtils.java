package com.tstudioz.essentialuilibrary.util;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class FragmentUtils {

    /**
     * Creates and sets an appropriate LayoutManager for the given RecyclerView.
     */
    public static void setupLayoutManagerForRecyclerView(Context context,
                                                   RecyclerView recyclerView,
                                                   final int columnCount) {
        if (columnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.addItemDecoration(new DividerItemDecoration(
                    context, LinearLayoutManager.VERTICAL));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
        }
    }
}
