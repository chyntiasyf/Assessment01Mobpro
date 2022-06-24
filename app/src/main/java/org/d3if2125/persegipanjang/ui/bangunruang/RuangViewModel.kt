package org.d3if2125.persegipanjang.ui.bangunruang

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2125.persegipanjang.R
import org.d3if2125.persegipanjang.internet.ApiStatus
import org.d3if2125.persegipanjang.internet.RuangApi
import org.d3if2125.persegipanjang.internet.RuangApiService
import java.lang.Exception

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
}