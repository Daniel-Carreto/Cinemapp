package com.karetolabs.cinemapp.upcomming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karetolabs.cinemapp.data.network.NetworkConnection
import com.karetolabs.cinemapp.data.network.service.DiscoverService
import com.karetolabs.cinemapp.discover.Movie
import kotlinx.coroutines.launch
import retrofit2.Response

class UpComingViewModel: ViewModel() {
    private var listMovies = MutableLiveData<List<Movie>>()
    var listMoviesLiveData: LiveData<List<Movie>> = listMovies

    fun getMovies() {
        viewModelScope.launch {
            val response = NetworkConnection.provideApi(DiscoverService::class.java)?.discoverList("https://api.themoviedb.org/3/movie/upcoming?api_key=568ff4df19633325b978ea1b75fe2290&language=en-US&page=1")

            val movies = response?.results?.map {
                Movie(
                    it.id,
                    it.title,
                    it.posterPath,
                    it.overview,
                    it.releaseDate
                )
            }

            movies?.let {
                listMovies.postValue(it)
            }
        }
    }
}