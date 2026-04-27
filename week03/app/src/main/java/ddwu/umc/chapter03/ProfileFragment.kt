package ddwu.umc.chapter03

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ddwu.umc.chapter03.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var followingAdapter: FollowingAdapter
    private val myApiKey = "reqres_def8c0b458de43c6a6d1a13bbe845d90"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        fetchMyProfile()
        fetchFollowingList()
    }

    private fun setupRecyclerView() {
        followingAdapter = FollowingAdapter(emptyList())
        binding.rvFollowing.apply {
            adapter = followingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    // 1번 유저 정보 가져오기 (닉네임, 프로필 사진)
    private fun fetchMyProfile() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = ApiClient.reqResService.getUser(myApiKey, 1)
                if (response.isSuccessful) {
                    response.body()?.data?.let { user ->
                        binding.tvNickname.text = "${user.firstName} ${user.lastName}"
                        Glide.with(this@ProfileFragment)
                            .load(user.avatar)
                            .circleCrop()
                            .into(binding.ivProfileImage)
                    }
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "내 정보 호출 실패: ${e.message}")
            }
        }
    }

    // 팔로잉 리스트 가져오기
    private fun fetchFollowingList() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = ApiClient.reqResService.getUserList(myApiKey)
                if (response.isSuccessful) {
                    val list = response.body()?.data ?: emptyList()
                    followingAdapter.updateData(list)
                    binding.tvFollowingTitle.text = "팔로잉 (${list.size})"
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "리스트 호출 실패: ${e.message}")
            }
        }
    }

 }