package com.example.umc_assignment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.umc_assignment2.databinding.FragmentCartBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOrder.setOnClickListener {
            requireActivity()
                .findViewById<BottomNavigationView>(R.id.main_bnv)
                .selectedItemId = R.id.shop
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

