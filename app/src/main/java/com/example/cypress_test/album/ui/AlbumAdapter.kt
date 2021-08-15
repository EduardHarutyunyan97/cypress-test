package com.example.cypress_test.album.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cypress_test.R
import com.example.cypress_test.databinding.AlbumItemBinding

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private val items = mutableListOf<AlbumUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        val binding = AlbumItemBinding.bind(view)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        album?.let { holder.bind(it) }

    }

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: List<AlbumUiModel>) {
        items.addAll(newItems)
        notifyItemRangeInserted(items.size, newItems.size)
    }

    private fun getItem(position: Int) = items.getOrNull(position)

    class AlbumViewHolder(
        private val binding: AlbumItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.rvPhoto.layoutManager =
                LinearLayoutManager(binding.rvPhoto.context, LinearLayoutManager.HORIZONTAL, false)
        }

        fun bind(albumUiModel: AlbumUiModel) {
            with(albumUiModel) {
                binding.tvTitle.text = album.title
                binding.rvPhoto.adapter = photoAdapter
            }
        }
    }

}