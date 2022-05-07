package com.karetolabs.cinemapp.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karetolabs.cinemapp.data.network.NetworkConnection
import com.karetolabs.cinemapp.data.network.service.DiscoverService
import com.karetolabs.cinemapp.discover.Movie
import kotlinx.coroutines.launch

class PopularViewModel(
    private val inicializador: Int = 0
) : ViewModel() {

    private val contadorMutableLiveData = MutableLiveData<Int>()
    val contadorLiveData: LiveData<Int> = contadorMutableLiveData

    private val popularMoviesMutableLiveData = MutableLiveData<List<Movie>?>()
    val popularMoviesLiveData: LiveData<List<Movie>?> = popularMoviesMutableLiveData

    var contador = 0

    init {
        contadorMutableLiveData.value = inicializador
    }


    fun actualizarContador() {
        contadorMutableLiveData.value = (inicializador + ++contador)
    }

    fun fetchPopularMovies() {
        viewModelScope.launch {
            val response = NetworkConnection
                .provideApi(DiscoverService::class.java)?.discoverList(
                    "https://api.themoviedb.org/3/movie/popular?api_key=568ff4df19633325b978ea1b75fe2290&language=en-US&page=1"
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
            popularMoviesMutableLiveData.postValue(movies)
            // withContext(Dispatchers.Main) {
            //topRatedView.showTopRated(movies?: arrayListOf())
            // topRatedView.hideLoading()
            // }
        }
    }

    fun fetchSearchMovie(title: String){
        viewModelScope.launch {
            val query = title.replace(" ","%20")

            val response = NetworkConnection
                .provideApi(DiscoverService::class.java)?.discoverList(
                    "https://api.themoviedb.org/3/search/movie?api_key=568ff4df19633325b978ea1b75fe2290&language=en-US&query=$query"
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
            popularMoviesMutableLiveData.postValue(movies)
            // withContext(Dispatchers.Main) {
            //topRatedView.showTopRated(movies?: arrayListOf())
            // topRatedView.hideLoading()
            // }
        }
    }



}