package com.karetolabs.cinemapp.pokemon.data.repository

import com.karetolabs.cinemapp.pokemon.data.RetrofitClient
import com.karetolabs.cinemapp.pokemon.data.network.service.SearchService
import com.karetolabs.cinemapp.pokemon.domain.mappers.searchPokeToDomain
import com.karetolabs.cinemapp.pokemon.domain.model.SearchDomain
import com.karetolabs.cinemapp.pokemon.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchNetworkRepository : RetrofitClient(), SearchRepository {

    override suspend fun searchRepository(name: String): Flow<SearchDomain> {
        return flow {
            if (name.isEmpty()) {
                throw VacioException("Vacio")
            }
            emit(searchPokeToDomain(provideApi(SearchService::class.java).search(name)))
        }.flowOn(Dispatchers.IO)
    }

    class VacioException(message: String) : Exception(message)
}