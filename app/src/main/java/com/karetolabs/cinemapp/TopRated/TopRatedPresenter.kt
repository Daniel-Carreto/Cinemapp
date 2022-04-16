package com.karetolabs.cinemapp.TopRated

import com.karetolabs.cinemapp.data.network.NetworkConnection
import com.karetolabs.cinemapp.data.network.service.DiscoverService
import com.karetolabs.cinemapp.discover.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TopRatedPresenter(
    private val topRatedView: TopRatedContract.TopRatedView
): TopRatedContract.TopRatedPresenter {

    init {
        fetchRequestTopRatedMovie()
    }

    override fun fetchRequestTopRatedMovie() {
        topRatedView.showLoading()
        CoroutineScope(Dispatchers.IO).launch {
            val response = NetworkConnection
                .provideApi(DiscoverService::class.java)?.discoverList(
                    "https://api.themoviedb.org/3/movie/top_rated?api_key=568ff4df19633325b978ea1b75fe2290&language=en-US&page=1"
                )

            val movies = response?.results?.map { movieResponse ->
                Movie(
                    id = movieResponse.id,
                    title = movieResponse.title,
                    posterPath = movieResponse.posterPath,
                    summary = movieResponse.overview,
                    date = movieResponse.releaseDate
                )
            }
            withContext(Dispatchers.Main) {
                topRatedView.showTopRated(movies?: arrayListOf())
                topRatedView.hideLoading()
            }
        }
    }
}