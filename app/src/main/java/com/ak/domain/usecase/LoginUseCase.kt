package com.ak.domain.usecase

import com.ak.data.repository.AKRepository
import com.ak.model.LoginModel
import com.ak.model.LoginResponseModel
import javax.inject.Inject
import com.ak.util.Result

class LoginUseCase @Inject constructor(
    private val loginRepository: AKRepository
) {
    suspend operator fun invoke(userId: LoginModel): Result<LoginResponseModel> =
        loginRepository.login(userId)
}