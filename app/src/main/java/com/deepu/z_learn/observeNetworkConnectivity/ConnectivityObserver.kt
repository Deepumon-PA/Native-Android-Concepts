package com.deepu.z_learn.observeNetworkConnectivity

import kotlinx.coroutines.flow.Flow

//ConnectivityObserver to observe the states
interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status{
        Available, UnAvailable, Losing, Lost
    }
}