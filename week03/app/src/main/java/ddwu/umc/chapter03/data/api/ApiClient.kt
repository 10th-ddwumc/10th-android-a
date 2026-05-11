package ddwu.umc.chapter03.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//retrofit 객체를 생성. 5주차.
object ApiClient {

    //swagger 주소
    private const val BASE_URL = "https://reqres.in/"


    //retrofit 객체 생성
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // 서비스 정의
    val reqResService: ReqResService = retrofit.create(ReqResService::class.java)
    //이 객체를 이용해 API를 호출합니다.
}