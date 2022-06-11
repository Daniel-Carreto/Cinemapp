package com.karetolabs.cinemapp.pokemon.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karetolabs.cinemapp.pokemon.data.repository.SearchNetworkRepository
import com.karetolabs.cinemapp.pokemon.domain.interactors.SearchInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private var searchInteractor: SearchInteractor? = null

    private val resultFlow = MutableStateFlow<String>("")
    val result: StateFlow<String> = resultFlow


    init {
        searchInteractor = SearchInteractor(SearchNetworkRepository())
    }


    fun search(name: String) {
        viewModelScope.launch {
            searchInteractor?.search(name)?.onStart {
                //pintar progress o loading
            }?.collect {
                when(it){
                    is SearchInteractor.SearchResult.Success -> {
                        resultFlow.emit(it.data.image.orEmpty())
                    }
                    is SearchInteractor.SearchResult.NotExist -> {

                    }
                    is SearchInteractor.SearchResult.NotFound -> {

                    }
                }

            }
        }
    }


}