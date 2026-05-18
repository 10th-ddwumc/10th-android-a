package com.example.umc_assignment2.repository

import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {
    override suspend fun getUserOne(): Response<UserResponse<UserData>> = userService.getUserOne()
    override suspend fun getUserList(page: Int): Response<UserResponse<List<UserData>>> = userService.getUserList(page)
}