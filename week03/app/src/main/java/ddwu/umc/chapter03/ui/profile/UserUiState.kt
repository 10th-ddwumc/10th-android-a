package ddwu.umc.chapter03.ui.profile

import ddwu.umc.chapter03.data.model.SingleUserResponse
import ddwu.umc.chapter03.data.model.UserListResponse

data class UserUiState(
    val isLoading: Boolean = false,
    val singleUser : SingleUserResponse? = null,
    val userList: UserListResponse? = null,
    val errorMessage: String? = null
)