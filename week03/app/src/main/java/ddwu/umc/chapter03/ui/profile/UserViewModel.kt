package ddwu.umc.chapter03.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ddwu.umc.chapter03.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel(){

    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    private val API_KEY = "reqres_def8c0b458de43c6a6d1a13bbe845d90"

    fun fetchSingleUser(userId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) } // 로딩 시작!

            try {
                val response = userRepository.getUser(API_KEY, userId)
                if (response.isSuccessful) {
                    val data = response.body()
                    _uiState.update { it.copy(isLoading = false, singleUser = data) } // 성공! 데이터 저장
                } else {
                    _uiState.update { it.copy(isLoading = false, errorMessage = "내 정보 불러오기 실패") }
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "에러 발생: ${e.message}")
                _uiState.update { it.copy(isLoading = false, errorMessage = "네트워크 오류") }
            }
        }
    }


    //팔로잉 리스트 가져오기
    fun fetchUserList() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) } // 로딩 시작!

            try {
                val response = userRepository.getUserList(API_KEY)
                if (response.isSuccessful) {
                    val data = response.body()
                    _uiState.update { it.copy(isLoading = false, userList = data) } // 성공! 데이터 저장
                } else {
                    _uiState.update { it.copy(isLoading = false, errorMessage = "리스트 불러오기 실패") }
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "에러 발생: ${e.message}")
                _uiState.update { it.copy(isLoading = false, errorMessage = "네트워크 오류") }
            }
        }
    }

}