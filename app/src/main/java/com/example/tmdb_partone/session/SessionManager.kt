package com.example.tmdb_partone.session

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdb_partone.models.AuthToken
import com.example.tmdb_partone.persistence.AuthTokenDao
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SessionManager
@Inject
constructor(
        val authTokenDao: AuthTokenDao,
        val application: Application
) {

    private val TAG: String = "AppDebug"

    private val _cachedToken = MutableLiveData<AuthToken>()

    val cachedToken: LiveData<AuthToken>
        get() = _cachedToken

    fun login(newValue: AuthToken){
        setValue(newValue)
    }

    fun logout(){
        Log.d(TAG, "logout: ")


        CoroutineScope(IO).launch{
            var errorMessage: String? = null
            try{
                _cachedToken.value!!.account_pk?.let { authTokenDao.nullifyToken(it)
                } ?: throw CancellationException("Token Error. Logging out user.")
            }catch (e: CancellationException) {
                Log.e(TAG, "logout: ${e.message}")
                errorMessage = e.message
            }
            catch (e: Exception) {
                Log.e(TAG, "logout: ${e.message}")
                errorMessage = errorMessage + "\n" + e.message
            }
            finally {
                errorMessage?.let{
                    Log.e(TAG, "logout: $errorMessage")
                }
                Log.d(TAG, "logout: finally")
                setValue(null)
            }
        }
    }

    fun setValue(newValue: AuthToken?) {
        GlobalScope.launch(Main) {
            if (_cachedToken.value != newValue) {
                _cachedToken.value = newValue
            }
        }
    }

    fun isConnectedToTheInternet(): Boolean{

        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork

        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        if(networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)){
            Log.d("AppDebug","True")
        }else{
            Log.d("AppDebug","false")
        }
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}