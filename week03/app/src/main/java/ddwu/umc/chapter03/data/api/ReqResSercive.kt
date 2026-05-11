package ddwu.umc.chapter03.data.api

import ddwu.umc.chapter03.data.model.SingleUserResponse
import ddwu.umc.chapter03.data.model.UserListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ReqResService {

    //1. 내 정보 가져오기 (userId 자리에 1번을 넣을 예정)
    @GET("api/users/{id}")
    suspend fun getUser(
        @Header("x-api-key") apiKey: String,
        @Path("id") userID : Int
    ): Response<SingleUserResponse>

    //2. 팔로잉 리스트 가져오기 (유저 전체 목록)!!!
    @GET("api/users")
    suspend fun getUserList(
        @Header("x-api-key") apiKey: String
    ): Response<UserListResponse>
}