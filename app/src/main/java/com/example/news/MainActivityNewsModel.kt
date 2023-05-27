package com.example.news

import android.app.Application
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivityNewsModel(val app : Application) : AndroidViewModel(app) {

    private val _networkStateFlow = MutableStateFlow(NetworkStatus.CONNECTED)
    val networkStatFlow = _networkStateFlow.asStateFlow()

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()


    fun checkInternetConnection() {
        val connectivityManager = app.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallBack())
    }

    private fun networkCallBack() : ConnectivityManager.NetworkCallback{
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            // network is available for use
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                _networkStateFlow.value = NetworkStatus.CONNECTED
            }

            // Network capabilities have changed for the network
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
//                val unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
            }

            // lost network connection
            override fun onLost(network: Network) {
                super.onLost(network)
                _networkStateFlow.value = NetworkStatus.DISCONNECTED
            }
        }
        return networkCallback
    }

}

enum class NetworkStatus(val stringId : Int){
    CONNECTED(stringId = R.string.internet_connection_is_restored),
    DISCONNECTED(stringId = R.string.internet_connection_is_lost)
}