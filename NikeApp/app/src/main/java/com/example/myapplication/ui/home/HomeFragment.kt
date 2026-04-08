package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.DataStoreManager
import com.example.myapplication.data.HomeProduct
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.home.HomeProductAdapter
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemWidth = (resources.displayMetrics.widthPixels * 0.75).toInt()
        val adapter = HomeProductAdapter(emptyList(), itemWidth)

        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        viewLifecycleOwner.lifecycleScope.launch {
            DataStoreManager.getHomeProducts(requireContext())
                .collect { savedList ->
                    if (savedList.isEmpty()) { // 비어있으면 더미데이터 DataStore에 저장
                        val dummyList = listOf(
                            HomeProduct(R.drawable.product1, "나이키 보메로 프리미엄", 149000),
                            HomeProduct(R.drawable.product2, "잉글랜드 2026 스타디움 홈", 135000),
                            HomeProduct(R.drawable.product3, "조던 컴포트 에라", 149000),
                        )
                        DataStoreManager.saveHomeProducts(requireContext(), dummyList)
                    } else {
                        binding.rvProducts.adapter = HomeProductAdapter(savedList, itemWidth)
                    }
                }
        }
    }
}