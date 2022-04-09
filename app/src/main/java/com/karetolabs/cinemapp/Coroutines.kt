package com.karetolabs.cinemapp

import android.util.Log
import kotlinx.coroutines.*

fun main() {
   first()
}

fun first(){
    Log.d("Coroutine","Primera")
    CoroutineScope(Dispatchers.Default).launch {
        Hilo()
//            Log.d("Coroutine","BEfore")
//            delay(2000L)
//            Log.d("Coroutine","DEspues del tiempo")
//            val crednecial = ImprimirSaludo()
//            ImprimirSaludo()
//            Log.d("Coroutine","multiplica ${Multiplica()}")
//            withContext(Dispatchers.Main){
//                activityFavoriteBinding.textView2.text = "Saludos"
//            }
//            Log.d("Coroutine","BEfore")
//            delay(2000L)
//            Log.d("Coroutine","DEspues del tiempo")
//            ImprimirSaludo()
//            ImprimirSaludo()
//            Log.d("Coroutine","multiplica ${Multiplica()}")
    }
}

suspend fun ImprimirSaludo (){
    Log.d("Coroutine","Saludos")
}

suspend fun Multiplica() = 2*2

suspend fun Hilo() = coroutineScope{
    Log.d("Coroutine","BEfore")
    delay(2000L)
    Log.d("Coroutine","DEspues del tiempo")
    ImprimirSaludo()
    ImprimirSaludo()
    Log.d("Coroutine","multiplica ${Multiplica()}")

    val job = launch {
        repeat(100) { i  ->
            Log.d("Coroutine","multi I´m sleeping $i")
            delay(500L)
        }
    }
    delay(1300L)
    Log.d("Coroutine","termine")
    job.cancel()
    Log.d("Coroutine","termine")
}


