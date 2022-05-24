package org.d3if2125.persegipanjang.ui.histori

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3if2125.persegipanjang.R
import org.d3if2125.persegipanjang.databinding.ItemHistoriBinding
import org.d3if2125.persegipanjang.db.PersegiPanjangEntity
import org.d3if2125.persegipanjang.model.hitungLuas
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<PersegiPanjangEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK){

    companion object{
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<PersegiPanjangEntity>(){
                override fun areItemsTheSame(
                    oldItem: PersegiPanjangEntity,
                    newItem: PersegiPanjangEntity
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: PersegiPanjangEntity,
                    newItem: PersegiPanjangEntity
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

        override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int
        ): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemHistoriBinding.inflate(inflater, parent, false)
            return ViewHolder(binding)
        }
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(getItem(position))
        }

        class ViewHolder(
            private val binding: ItemHistoriBinding
        ) : RecyclerView.ViewHolder(binding.root){
            private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
                Locale("id", "ID")
            )

            fun bind(item: PersegiPanjangEntity) = with(binding) {
                val HasilHitung = item.hitungLuas()

                tanggalPp.text = dateFormatter.format(Date(item.tanggal))
                persegiPanjang2.text = root.context.getString(R.string.hasil_x, HasilHitung.persegiPanjang)
                dataText.text = root.context.getString(R.string.data_x, item.panjang, item.lebar)
            }
        }
    }