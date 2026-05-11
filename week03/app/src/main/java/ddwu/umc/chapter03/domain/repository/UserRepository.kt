package ddwu.umc.chapter03.domain.repository

import ddwu.umc.chapter03.data.model.SingleUserResponse
import ddwu.umc.chapter03.data.model.UserListResponse
import retrofit2.Response


interface UserRepository {
    // ReqResService에 있던 함수 형태를 그대로
    suspend fun getUser(apiKey: String, userId: Int): Response<SingleUserResponse>
    suspend fun getUserList(apiKey: String): Response<UserListResponse>
}