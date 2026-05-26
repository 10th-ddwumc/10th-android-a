//package com.example.umc_assignment2
//
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object RetrofitClient {
//    private const val BASE_URL = "https://reqres.in/"
//
//    private val okHttpClient: OkHttpClient by lazy {
//        val logging = HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }
//
//        OkHttpClient.Builder()
//            .addInterceptor { chain ->
//                val original = chain.request()
//                val requestBuilder = original.newBuilder()
//                    .header("x-api-key", "reqres_3b658757be8345b4a732cfafd80882ee")
//                    .method(original.method, original.body)
//
//                val request = requestBuilder.build()
//                chain.proceed(request)
//            }
//            .addInterceptor(logging)
//            .build()
//    }
//
//    val instance: Retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    val userService: UserService by lazy {
//        instance.create(UserService::class.java)
//    }
//}