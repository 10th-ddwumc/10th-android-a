package com.example.myapplication.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentTopsBinding

class TopsFragment : Fragment() {

    private lateinit var binding: FragmentTopsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopsBinding.inflate(inflater, container, false)
        return binding.root
    }
}