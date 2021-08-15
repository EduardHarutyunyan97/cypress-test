package com.example.cypress_test.album.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cypress_test.R
import com.example.cypress_test.album.entity.Album
import com.example.cypress_test.databinding.AlbumItemBinding
import com.example.cypress_test.photo.ui.PhotoAdapter

class AlbumAdapter(
    private val photoAdapter: PhotoAdapter
) : ListAdapter<Album, AlbumAdapter.AlbumViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        val binding = AlbumItemBinding.bind(view)
        return AlbumViewHolder(binding, photoAdapter)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        album?.let { holder.bind(it) }

    }

    class AlbumViewHolder(
        private val binding: AlbumItemBinding,
        private val photoAdapter: PhotoAdapter
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.rvPhoto.layoutManager =
                LinearLayoutManager(binding.rvPhoto.context, LinearLayoutManager.HORIZONTAL, false)
        }

        fun bind(album: Album) {
            binding.tvTitle.text = album.title
            binding.rvPhoto.adapter = photoAdapter
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
                return oldItem == newItem
            }
        }
    }
}