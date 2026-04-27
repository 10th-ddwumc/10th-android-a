package ddwu.umc.chapter03

import com.google.gson.annotations.SerializedName

//유저 1명의 진짜 데이터 모양
data class UserData(
    val id: Int,
    val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val avatar: String
)

//내 정보(1명) 불러올 때 받을 바구니
data class SingleUserResponse(
    val data: UserData
)

// 팔로잉 리스트(여러명) 불러올 때 받을 바구니
data class UserListResponse(
    val page: Int,
    val data: List<UserData>
)