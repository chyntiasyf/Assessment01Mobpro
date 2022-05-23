package org.d3if2125.persegipanjang.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if2125.persegipanjang.model.HasilHitung

class MainViewModel : ViewModel() {
    private val hitungLuas = MutableLiveData<HasilHitung?>()

    fun hitungLuas(panjang: Float, lebar: Float) {
        val hasilLuas = panjang * lebar
        hitungLuas.value = HasilHitung(hasilLuas)
    }

    fun getHasilHitung():LiveData<HasilHitung?> = hitungLuas
}