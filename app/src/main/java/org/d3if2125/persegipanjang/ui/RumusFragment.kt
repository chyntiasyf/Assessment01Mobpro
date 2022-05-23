package org.d3if2125.persegipanjang.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.d3if2125.persegipanjang.databinding.FragmentRumus2Binding

class RumusFragment: Fragment() {

    private lateinit var binding: FragmentRumus2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     binding = FragmentRumus2Binding.inflate(layoutInflater, container, false)
     return binding.root
    }
}