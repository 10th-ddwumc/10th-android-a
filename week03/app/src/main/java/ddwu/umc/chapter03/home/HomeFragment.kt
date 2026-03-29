package ddwu.umc.chapter03.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.umc.chapter03.R
import ddwu.umc.chapter03.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    // 1. 뷰바인딩 설정 (메모리 누수 방지용)
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 2. 뷰바인딩 화면 연결
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeDummyData = arrayListOf(
            HomeProductData(R.drawable.jordan_xxxi, "Air Jordan XXXVI", "US$185"),
            HomeProductData(R.drawable.airforce107, "Nike Air Force 1 '07", "US$115")
        )


        val productAdapter = HomeProductAdapter(homeDummyData)
        binding.homeRecyclerview.adapter = productAdapter
        binding.homeRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}