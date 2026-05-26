package com.example.umc_assignment2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.umc_assignment2.databinding.FragmentProfileBinding
import com.example.umc_assignment2.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("LIFECYCLE_CHECK", "ProfileFragment 떴음!")

        viewModel.fetchUserList(1)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userList.collectLatest { users ->
                if (users.isNotEmpty()) {
                    val user = users[0]
                    binding.tvProfileName.text = "${user.firstName} ${user.lastName}"
                    Glide.with(this@ProfileFragment)
                        .load(user.avatar)
                        .circleCrop()
                        .into(binding.ivProfileImage)

                    binding.tvFollowingTitle.text = "팔로잉 (${users.size})"
                    binding.rvFollowing.apply {
                        layoutManager = LinearLayoutManager(
                            context,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        adapter = FollowingAdapter(users)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}