package com.karetolabs.cinemapp.pokemon.domain.mappers

import com.karetolabs.cinemapp.pokemon.data.network.model.response.SearchResponse
import com.karetolabs.cinemapp.pokemon.domain.model.SearchDomain


fun searchPokeToDomain(searchResponse: SearchResponse): SearchDomain{
    return SearchDomain(
        searchResponse.id,
        searchResponse.name,
        searchResponse.sprites?.frontDefault.orEmpty()
    )
}