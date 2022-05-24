package org.d3if2125.persegipanjang.ui.hitung

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if2125.persegipanjang.R
import org.d3if2125.persegipanjang.databinding.FragmentHitungBinding
import org.d3if2125.persegipanjang.db.PersegiPanjangDb
import org.d3if2125.persegipanjang.model.HasilHitung

class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding
    private val viewModel: HitungViewModel by lazy {
        val db = PersegiPanjangDb.getIntance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
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
        viewModel.data.observe(viewLifecycleOwner,{
            if (it == null) return@observe
            Log.d("HitungFragment", "Data tersimpan. ID = ${it.id}")
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_historiFragment
                )
                return true
            }
        R.id.menu_about -> {
            findNavController().navigate(
                R.id.action_hitungFragment_to_aboutFragment
            )
            return true
        }
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