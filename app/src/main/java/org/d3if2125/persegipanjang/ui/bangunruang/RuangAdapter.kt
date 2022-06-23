package org.d3if2125.persegipanjang.ui.bangunruang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if2125.persegipanjang.databinding.ItemRuangBinding

class RuangAdapter : RecyclerView.Adapter<RuangAdapter.ViewHolder>() {
    private val data = mutableListOf<BangunRuang>()

    fun updateData(newData:List<BangunRuang>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRuangBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(private val binding: ItemRuangBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(bangunRuang: BangunRuang) = with(binding){
            pictKubus.setImageResource(bangunRuang.gambar)
            ruangRumus.text = bangunRuang.rumus
        }
    }
}