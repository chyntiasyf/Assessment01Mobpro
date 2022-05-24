package org.d3if2125.persegipanjang.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2125.persegipanjang.db.PersegiPanjangDao
import org.d3if2125.persegipanjang.db.PersegiPanjangEntity
import org.d3if2125.persegipanjang.model.HasilHitung

class HitungViewModel (private val db: PersegiPanjangDao) : ViewModel() {
    private val hitungLuas = MutableLiveData<HasilHitung?>()

    val data = db.getLastPersegiPanjang()

    fun hitungLuas(panjang: Float, lebar: Float) {
        val hasilLuas = panjang * lebar
        hitungLuas.value = HasilHitung(hasilLuas)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataPersegiPanjang = PersegiPanjangEntity(
                    panjang = panjang,
                    lebar = lebar
                )
                db.insert(dataPersegiPanjang)
            }
        }
    }

    fun getHasilHitung():LiveData<HasilHitung?> = hitungLuas
}