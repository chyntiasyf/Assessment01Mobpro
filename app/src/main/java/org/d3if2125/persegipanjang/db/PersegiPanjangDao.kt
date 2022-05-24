package org.d3if2125.persegipanjang.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersegiPanjangDao {

    @Insert
    fun insert(luas: PersegiPanjangEntity)

    @Query("SELECT * FROM luas ORDER BY id DESC")
    fun getLastPersegiPanjang(): LiveData<List<PersegiPanjangEntity>>
}