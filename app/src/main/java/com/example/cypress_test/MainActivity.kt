package com.example.cypress_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cypress_test.album.entity.Album
import com.example.cypress_test.album.ui.AlbumAdapter
import com.example.cypress_test.album.ui.AlbumInfiniteScrollListener
import com.example.cypress_test.album.ui.AlbumUiModel
import com.example.cypress_test.core.ConnectionHandler
import com.example.cypress_test.core.Injection
import com.example.cypress_test.databinding.ActivityMainBinding
import com.example.cypress_test.photo.ui.PhotoAdapter
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val albumAdapter: AlbumAdapter = AlbumAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNetworkHandler()
        initViewModel()
        initRecyclerView()

    }

    private fun getAlbums() {
        viewModel.getAlbums()
            .onErrorReturn { listOf() }
            .subscribe {
            val albumUiModels= mapToAlbumUiModel(it)
                runOnUiThread {

                albumAdapter.submitList(albumUiModels)
                }
                viewModel.getPhotosByAlbumIds(albumUiModels) { flowable, photoAdapter ->
                    flowable
                        .subscribe { data ->
                            photoAdapter.submitData(lifecycle, data)
                        }
                }
            }
    }

    private fun initNetworkHandler() {
        val connectionHandler = ConnectionHandler(this)
        val snackBar = Snackbar.make(
            binding.container,
            resources.getString(R.string.internet_connection_message),
            Snackbar.LENGTH_INDEFINITE
        )
        connectionHandler.observe(this, { hasConnection ->
            if (hasConnection) {
                snackBar.dismiss()
                getAlbums()
            } else {
                snackBar.show()
            }
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            Injection.provideMainViewModelFactory(this)
        ).get(MainViewModel::class.java)
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            val scrollListener = AlbumInfiniteScrollListener { getAlbums() }
            addOnScrollListener(scrollListener)
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = albumAdapter
        }
        getAlbums()
    }

    private fun mapToAlbumUiModel(albums: List<Album>) = albums.map {
        AlbumUiModel(
            it,
            PhotoAdapter()
        )
    }
}