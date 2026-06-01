package com.example.myapplication.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.network.RetrofitInstance
import com.example.myapplication.data.model.User
import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Repository한테 데이터 요청하고 Screen에 전달
class ProfileViewModel(
    private val userRepository: UserRepository = UserRepository(RetrofitInstance.api)
) : ViewModel() {
    private val _profile = MutableStateFlow<User?>(null)
    val profile: StateFlow<User?> = _profile.asStateFlow()

    private val _followingList = MutableStateFlow<List<User>>(emptyList())
    val followingList: StateFlow<List<User>> = _followingList.asStateFlow()

    private val apiKey = "reqres_98c878f44187433bb956c563b32560b9"

    init {
        fetchProfile()
        fetchFollowing()
    }

    private fun fetchProfile() {
        viewModelScope.launch {
            val response = userRepository.getUser(apiKey, 1)
            if (response.isSuccessful) {
                _profile.value = response.body()?.data
            }
        }
    }

    private fun fetchFollowing() {
        viewModelScope.launch {
            val response = userRepository.getUsers(apiKey)
            if (response.isSuccessful) {
                _followingList.value = response.body()?.data ?: emptyList()
            }
        }
    }
}
