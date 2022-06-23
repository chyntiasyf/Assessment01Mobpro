package org.d3if2125.persegipanjang.ui.bangunruang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if2125.persegipanjang.databinding.FragmentRuangBinding


class FragmentRuang : Fragment() {
    private val viewModel : RuangViewModel by lazy {
        ViewModelProvider(this).get(RuangViewModel :: class.java)
    }
    private lateinit var binding: FragmentRuangBinding
    private lateinit var myAdapter: RuangAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRuangBinding.inflate(layoutInflater, container, false)
        myAdapter = RuangAdapter()
        with(binding.recyclerView2){
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })
    }
}