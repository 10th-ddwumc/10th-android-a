package com.example.umc_assignment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.umc_assignment2.databinding.FragmentHomeBinding
import com.example.umc_assignment2.ProductAdapter

class HomeFragment : Fragment() {

    // ViewBinding 설정 (권장 방식)
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. 임시 데이터 생성 (실제로는 서버나 리스트에서 가져오겠죠?)
        val productList = arrayListOf(
            Product("Air Jordan 1 Mid", "US$125", R.drawable.shoes),
            Product("Nike Everyday Plus", "US$10", R.drawable.shoes),
//            Product("Air Force 1 '07", "US$115"),
//            Product("Nike Elite Crew", "US$16")
        )

        // 2. 어댑터 설정
        val productAdapter = ProductAdapter(productList)

        // 3. 리사이클러뷰 설정 (이미지처럼 2줄 격자)
        binding.homeProductRv.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true) // 성능 최적화
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}