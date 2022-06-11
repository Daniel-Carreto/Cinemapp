package com.karetolabs.cinemapp.pokemon.domain.interactors

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseInteractor<T>(
    protected val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
){
    fun execute(block: suspend FlowCollector<T>.() -> Unit): Flow<T>{
        return flow{
            block()
        }.flowOn(defaultDispatcher)

    }
}