package org.d3if2125.persegipanjang.model

import org.d3if2125.persegipanjang.db.PersegiPanjangEntity

fun PersegiPanjangEntity.hitungLuas(): HasilHitung{
    val persegiPanjang = panjang * lebar
    return HasilHitung(persegiPanjang)
}