package konradrutkowski.com.tapapp.online

import android.content.Context
import android.net.ConnectivityManager


object NetworkConnection {

    fun isInternetReady(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}
