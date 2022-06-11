package com.karetolabs.cinemapp.pokemon.domain.interactors

import com.karetolabs.cinemapp.pokemon.data.repository.SearchNetworkRepository
import com.karetolabs.cinemapp.pokemon.domain.model.SearchDomain
import com.karetolabs.cinemapp.pokemon.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

class SearchInteractor(private val searchRepository: SearchRepository)
    : BaseInteractor<SearchInteractor.SearchResult>(){

        sealed class SearchResult{
            data class Success(val data: SearchDomain): SearchResult()
            data class NotFound(val message:String) : SearchResult()
            data class NotExist(val message:String, val code:Int):SearchResult()
        }

    suspend fun search(name:String): Flow<SearchResult>{
        return execute {
            searchRepository.searchRepository(name).catch {
                if(it is SearchNetworkRepository.VacioException){
                    emit(SearchResult.NotExist(it.message.orEmpty(), 404))
                } else {
                    emit(SearchResult.NotExist(it.message.orEmpty(), 408))
                }
            }.collect { searchDomain ->
                emit(SearchResult.Success(searchDomain))
            }
        }
    }
}