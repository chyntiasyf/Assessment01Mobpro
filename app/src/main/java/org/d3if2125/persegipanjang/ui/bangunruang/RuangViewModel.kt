package org.d3if2125.persegipanjang.ui.bangunruang

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2125.persegipanjang.MainActivity
import org.d3if2125.persegipanjang.R
import org.d3if2125.persegipanjang.internet.ApiStatus
import org.d3if2125.persegipanjang.internet.RuangApi
import org.d3if2125.persegipanjang.internet.RuangApiService
import org.d3if2125.persegipanjang.internet.UpdateWorker
import java.lang.Exception
import java.util.concurrent.TimeUnit

class RuangViewModel : ViewModel() {
    private val data = MutableLiveData<List<BangunRuang>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData(){
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(RuangApi.service.getBangunRuang())
                status.postValue(ApiStatus.SUCCESS)
            }
            catch (e:Exception){
                Log.d("RuangViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData() : LiveData<List<BangunRuang>> = data
    fun getStatus() : LiveData<ApiStatus> = status
    fun scheduleUpdater(app: Application){
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(20, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID, ExistingWorkPolicy.REPLACE, request
        )
    }
}