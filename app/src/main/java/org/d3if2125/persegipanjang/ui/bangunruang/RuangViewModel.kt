package org.d3if2125.persegipanjang.ui.bangunruang

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2125.persegipanjang.R
import org.d3if2125.persegipanjang.internet.RuangApi
import org.d3if2125.persegipanjang.internet.RuangApiService
import java.lang.Exception

class RuangViewModel : ViewModel() {
    private val data = MutableLiveData<List<BangunRuang>>()

    init {
        data.value = initData()
        retrieveData()
    }

    private fun retrieveData(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = RuangApi.service.getBangunRuang()
                Log.d("RuangViewModel", "Success: $result")
            }
            catch (e:Exception){
                Log.d("RuangViewModel", "Failure: ${e.message}")
            }
        }
    }

    private fun initData():List<BangunRuang>{
        return listOf(
            BangunRuang(R.drawable.kubus, "Rumus Luas Kubus, yaitu: L = 6 x s\nKeliling Kubus, yaitu: K = 12 x s\nVolume Kubus, yaitu:  V = s x s x s (s3)"),
            BangunRuang(R.drawable.balok, "Luas balok, yaitu: L = 2 x [(p x l) + (p x t) + (l x t)]\nKeliling balok, yaitu: K = 4 x (p + l + t)\nVolume balok, yaitu: V = p x l x t"),
            BangunRuang(R.drawable.tabung, "Luas alas = luas lingkaran = πr^2\nKeliling alas tabung = 2 x π x r atau π x d\nRumus Volume tabung = luas alas (lingkaran) x tinggi = (π x r2) x t"),
            BangunRuang(R.drawable.kerucut, "Luas Kerucut, yaitu: L = π x r x (r + S)\nVolume Kerucut, yaitu: V = 1/3 x π x r^2 x t\nKeliling Kerucut, yaitu: K alas = 2 x π x r atau π x d"),
            BangunRuang(R.drawable.bola, "Volume Bola, yaitu: V = 4/3 x π x r^3\nLuas Bola, yaitu: L = 4 × π × r²")
        )
    }

    fun getData() : LiveData<List<BangunRuang>> = data
}