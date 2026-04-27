package com.example.umc_assignment2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.umc_assignment2.databinding.FragmentProfileBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("LIFECYCLE_CHECK", "ProfileFragment 떴음!")
        loadUserProfile()
        loadFollowingList()
    }


    private fun loadUserProfile() {
        binding.tvProfileName.text = "George Bluth"
        binding.tvFollowingTitle.text = "팔로잉 (6)"

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            try {
                val response = RetrofitClient.userService.getUserOne()
                if (response.isSuccessful) {
                    val userData = response.body()?.data
                    userData?.let {
                        binding.tvProfileName.text = "${it.firstName} ${it.lastName}"
                        Glide.with(this@ProfileFragment)
                            .load(it.avatar)
                            .circleCrop()
                            .into(binding.ivProfileImage)
                    }
                }
            } catch (e: Exception) {
                Log.e("FINAL_CHECK", "에러 무시: ${e.message}")
            }
        }
    }
//    private fun loadUserProfile() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            try {
//                val response = RetrofitClient.userService.getUserOne()
//                if (response.isSuccessful) {
//                    val userData = response.body()?.data
//                    userData?.let {
//                        binding.tvProfileName.text = "${it.firstName} ${it.lastName}"
//
//                        Glide.with(this@ProfileFragment)
//                            .load(it.avatar)
//                            .circleCrop()
//                            .into(binding.ivProfileImage)
//                    }
//                }
//            } catch (e: Exception) {
//                Log.e("API_ERROR", "내 정보 로드 실패: ${e.message}")
//            }
//        }
//    }

    private fun loadFollowingList() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = RetrofitClient.userService.getUserList(1)
                if (response.isSuccessful) {
                    val users = response.body()?.data ?: listOf()
                    binding.tvFollowingTitle.text = "팔로잉 (${users.size})"

                    binding.rvFollowing.apply {
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = FollowingAdapter(users)
                    }
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "팔로잉 리스트 로드 실패: ${e.message}")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}