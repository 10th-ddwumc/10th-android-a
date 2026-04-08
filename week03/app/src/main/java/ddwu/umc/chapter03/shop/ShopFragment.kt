package ddwu.umc.chapter03.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import ddwu.umc.chapter03.R
import ddwu.umc.chapter03.shop.ShopProductAdapter
import ddwu.umc.chapter03.shop.ShopProductData
import ddwu.umc.chapter03.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shopDummyData = arrayListOf(
            ShopProductData(
                R.drawable.socks6pair,
                false,
                "Nike Everyday Plus Cushioned",
                "Training Ankle Socks(6Pairs)",
                "5 Colours",
                "US$10"
            ),
            ShopProductData(
                R.drawable.socks_elite_crew,
                false,
                "Nike Elite Crew",
                "Basketball Socks",
                "7 Colours",
                "US$16"
            ),
            ShopProductData(
                R.drawable.airforce107,
                true,
                "Nike Air Force 1'07",
                "Women's Shoes",
                "5 Colours",
                "US$115"
            ),
            ShopProductData(
                R.drawable.airforce107ssentials,
                true,
                "Jordan ENike Air Force 1'07ssentials",
                "Mens's Shoes",
                "2 Colours",
                "US$115"
            )

        )


        val shopAdapter = ShopProductAdapter(shopDummyData)
        binding.shopRecyclerview.adapter = shopAdapter

        binding.shopRecyclerview.layoutManager = GridLayoutManager(context, 2)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}