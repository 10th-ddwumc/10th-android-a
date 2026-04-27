package ddwu.umc.chapter03

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://reqres.in/"


    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // 서비스 연결
    val reqResService: ReqResService = retrofit.create(ReqResService::class.java)
    //이 객체를 이용해 API를 호출합니다.
}