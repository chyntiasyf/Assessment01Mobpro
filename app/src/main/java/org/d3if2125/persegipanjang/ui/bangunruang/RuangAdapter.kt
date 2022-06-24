package org.d3if2125.persegipanjang.ui.bangunruang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if2125.persegipanjang.R
import org.d3if2125.persegipanjang.databinding.ItemRuangBinding
import org.d3if2125.persegipanjang.internet.RuangApi

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
            Glide.with(pictKubus.context)
                .load(RuangApi.getBangunRuangUrl(bangunRuang.gambar))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(pictKubus)
            ruangRumus.text = bangunRuang.rumus
        }
    }
}