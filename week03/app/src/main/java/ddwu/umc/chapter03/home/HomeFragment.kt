package ddwu.umc.chapter03.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.umc.chapter03.ProductData
import ddwu.umc.chapter03.ProductDataStoreManager
import ddwu.umc.chapter03.R
import ddwu.umc.chapter03.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    // 1. 뷰바인딩 설정 (메모리 누수 방지용) 화면죽을때 비워둠. 메모리쓰레기 안쌓임.
    //메모리 쌓여서 터지지않게.
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private lateinit var dataStoreManager: ProductDataStoreManager
    private lateinit var productAdapter: HomeProductAdapter

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

        dataStoreManager = ProductDataStoreManager(requireContext())

        productAdapter = HomeProductAdapter(emptyList())
        binding.homeRecyclerview.adapter = productAdapter
        binding.homeRecyclerview.layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.initHomeDummyDataIfNeeded()
        }
        observeDataStore()
    }


    private fun observeDataStore() {
        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.getHomeProducts().collect { productList ->
                productAdapter.updateData(productList)
            }
        }
    }


    //화면 죽을때 null로 함.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}