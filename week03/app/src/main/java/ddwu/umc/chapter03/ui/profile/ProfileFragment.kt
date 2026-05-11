package ddwu.umc.chapter03.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ddwu.umc.chapter03.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var followingAdapter: FollowingAdapter

    //뷰모델 연결
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        // 1. 뷰모델에게 데이터 가져오라고 명령
        viewModel.fetchSingleUser(1)
        viewModel.fetchUserList()

        // 2. 뷰모델이 포장해준 데이터(uiState)를 지켜보다가 화면 업데이트
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->

                //내 정보 (닉네임, 프로필 사진) 반영
                state.singleUser?.data?.let { user ->
                    binding.tvNickname.text = "${user.firstName} ${user.lastName}"
                    Glide.with(this@ProfileFragment)
                        .load(user.avatar)
                        .circleCrop()
                        .into(binding.ivProfileImage)
                }

                //팔로잉 리스트 반영
                state.userList?.data?.let { list ->
                    followingAdapter.updateData(list)
                    binding.tvFollowingTitle.text = "팔로잉 (${list.size})"
                }

                //에러 났을 때
                state.errorMessage?.let { error ->
                    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        followingAdapter = FollowingAdapter(emptyList())
        binding.rvFollowing.apply {
            adapter = followingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}