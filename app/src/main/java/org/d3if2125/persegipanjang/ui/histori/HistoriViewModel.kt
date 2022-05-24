package org.d3if2125.persegipanjang.ui.histori

import androidx.lifecycle.ViewModel
import org.d3if2125.persegipanjang.db.PersegiPanjangDao

class HistoriViewModel(db: PersegiPanjangDao) : ViewModel() {
    val data = db.getLastPersegiPanjang()
}