package com.example.myapplication.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.RetrofitInstance
import com.example.myapplication.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _profile = MutableStateFlow<User?>(null)
    val profile: StateFlow<User?> = _profile

    private val _followingList = MutableStateFlow<List<User>>(emptyList())
    val followingList: StateFlow<List<User>> = _followingList

    private val apiKey = "reqres_98c878f44187433bb956c563b32560b9"

    init {
        fetchProfile()
        fetchFollowing()
    }

    private fun fetchProfile() {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getUser(apiKey, 1)
            if (response.isSuccessful) {
                _profile.value = response.body()?.data
            }
        }
    }

    private fun fetchFollowing() {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getUsers(apiKey)
            if (response.isSuccessful) {
                _followingList.value = response.body()?.data ?: emptyList()
            }
        }
    }
}