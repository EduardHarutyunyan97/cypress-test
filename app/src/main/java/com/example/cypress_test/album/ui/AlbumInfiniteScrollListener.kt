package com.example.cypress_test.album.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AlbumInfiniteScrollListener(private val onBottomReached: () -> Unit) :
    RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy > 0) {
            (recyclerView.layoutManager as? LinearLayoutManager)?.let {
                val visibleItemCount = it.childCount
                val totalItemCount = it.itemCount
                val pastVisibleItems = it.findFirstVisibleItemPosition()
                if ((visibleItemCount.plus(pastVisibleItems)) >= totalItemCount) {
                    onBottomReached()
                }
            }
        }
    }
}