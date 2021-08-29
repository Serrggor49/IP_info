package ip.nikolo.poloma49.model

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import javax.inject.Inject


class NetworkStatus_new @Inject constructor() {

    @Inject
    lateinit var application: Application

    fun getNetworkStatus() : Boolean{
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}