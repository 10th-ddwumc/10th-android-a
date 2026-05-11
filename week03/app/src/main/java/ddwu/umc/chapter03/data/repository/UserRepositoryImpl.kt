package ddwu.umc.chapter03.data.repository

import ddwu.umc.chapter03.data.api.ReqResService
import ddwu.umc.chapter03.data.model.SingleUserResponse
import ddwu.umc.chapter03.data.model.UserListResponse
import ddwu.umc.chapter03.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject
//
// 계약서를 바탕으로, 실제로 API를 호출해서 데이터를 가져오는
// @Inject constructor를 붙이면 Hilt가 알아서 ReqResService를 주입
class UserRepositoryImpl @Inject constructor(
    private val reqResService: ReqResService
) : UserRepository {

   override suspend fun getUser(apiKey: String, userID: Int) : Response<SingleUserResponse> {
       return reqResService.getUser(apiKey, userID)

   }
    override suspend fun getUserList(apiKey:String) : Response<UserListResponse> {
        return reqResService.getUserList(apiKey)
    }

}