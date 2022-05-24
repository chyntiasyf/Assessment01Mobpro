package org.d3if2125.persegipanjang.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2125.persegipanjang.db.PersegiPanjangDao

class HistoriViewModel(private val db: PersegiPanjangDao) : ViewModel() {
    val data = db.getLastPersegiPanjang()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO){
            db.clearData()
        }
    }
}