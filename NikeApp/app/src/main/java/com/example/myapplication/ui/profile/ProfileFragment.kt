package com.example.myapplication.ui.profile

import android.os.Bundle
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

        val apiKey = "reqres_98c878f44187433bb956c563b32560b9"

        lifecycleScope.launch {
            val usersResponse = RetrofitInstance.api.getUsers(apiKey)
            if (usersResponse.isSuccessful) {
                val userList = usersResponse.body()?.data ?: emptyList()
                binding.rvFollowing.adapter = FollowingAdapter(userList)
                binding.tvFollowing.text = "팔로잉 (${userList.size})"
            }

            val profileResponse = RetrofitInstance.api.getUser(apiKey, 1)
            if (profileResponse.isSuccessful) {
                val user = profileResponse.body()?.data ?: return@launch
                binding.tvNickname.text = "${user.first_name} ${user.last_name}"
                Glide.with(this@ProfileFragment)
                    .load(user.avatar)
                    .circleCrop()
                    .into(binding.profileCircle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}