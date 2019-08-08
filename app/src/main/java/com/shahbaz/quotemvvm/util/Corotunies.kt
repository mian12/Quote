package com.shahbaz.quotemvvm.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Corotunies {

    fun main(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()

        }


    fun Io(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.IO).launch {
            work()

        }
}