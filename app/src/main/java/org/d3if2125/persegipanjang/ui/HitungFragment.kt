package org.d3if2125.persegipanjang.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if2125.persegipanjang.R
import org.d3if2125.persegipanjang.databinding.FragmentHitungBinding
import org.d3if2125.persegipanjang.model.HasilHitung

class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener {hitung()}
        binding.buttonRumus.setOnClickListener{
            it.findNavController().navigate(
                R.id.action_hitungFragment_to_rumusFragment2
            )
        }
        viewModel.getHasilHitung().observe(requireActivity(), { showResult(it)})
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about){
            findNavController().navigate(
                R.id.action_hitungFragment_to_aboutFragment
            )
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hitung(){
        val panjang = binding.panjangInp.text.toString()
        if (TextUtils.isEmpty(panjang)) {
            Toast.makeText(context, R.string.panjang_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val lebar = binding.lebarInp.text.toString()
        if (TextUtils.isEmpty(lebar)) {
            Toast.makeText(context, R.string.lebar_invalid, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungLuas(
            panjang.toFloat(),
            lebar.toFloat()
        )
    }

    private fun hitungLuas(panjang: Float, lebar: Float): HasilHitung {
        val hasilLuas = panjang * lebar

        return HasilHitung(hasilLuas)
    }

    private fun showResult(result: HasilHitung?) {
        if (result == null) return
        binding.hasil.text = getString(R.string.hasilLuas, result.persegiPanjang)
        binding.buttonRumus.visibility = View.VISIBLE
    }

}