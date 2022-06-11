package com.karetolabs.cinemapp.pokemon.domain.repository

import com.karetolabs.cinemapp.pokemon.domain.model.SearchDomain
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchRepository(name:String): Flow<SearchDomain>
}