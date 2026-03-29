package ddwu.umc.chapter03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ddwu.umc.chapter03.databinding.FragmentCartBinding
import ddwu.umc.chapter03.shop.ShopFragment

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null

    //? 말고 _binding이 절대 null이 아님(!!)을 보장해두기
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

        // '주문하기' 버튼 클릭 이벤트
        binding.orderBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_fragmentContainer, ShopFragment())
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}