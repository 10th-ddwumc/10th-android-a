package com.example.myapplication.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFollowing.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        lifecycleScope.launch {
            // 내 프로필 API
            val profileResponse = RetrofitInstance.api.getMyInfo("reqres_98c878f44187433bb956c563b32560b9", 1)
            if (profileResponse.isSuccessful) {
                val profile = profileResponse.body()?.data ?: return@launch
                binding.tvNickname.text = "${profile.first_name} ${profile.last_name}"
                Glide.with(this@ProfileFragment)
                    .load(profile.avatar)
                    .circleCrop()
                    .into(binding.profileCircle)
            }

            // 팔로잉 API
            val usersResponse = RetrofitInstance.api.getUsers("reqres_98c878f44187433bb956c563b32560b9")
            if (usersResponse.isSuccessful) {
                val userList = usersResponse.body()?.data ?: emptyList()
                Log.d("API_TEST", "size: ${userList.size}")
                binding.rvFollowing.adapter = FollowingAdapter(userList)
                binding.tvFollowing.text = "팔로잉 (${userList.size})"
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}