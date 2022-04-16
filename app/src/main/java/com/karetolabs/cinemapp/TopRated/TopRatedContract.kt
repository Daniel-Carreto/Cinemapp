package com.karetolabs.cinemapp.TopRated

import com.karetolabs.cinemapp.discover.Movie

interface TopRatedContract {

    interface TopRatedView {
        fun showLoading()
        fun hideLoading()
        fun showTopRated(topRatedMovies: List<Movie>)
        fun showErrorRequest(message:String)
    }

    interface TopRatedPresenter {
        fun fetchRequestTopRatedMovie()
    }

    interface TopRatedData {
        fun onSuccess(topRatedMovies: List<Movie>)
        fun onErrorResponse(message: String)
    }


}