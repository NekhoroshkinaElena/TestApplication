package com.nekhoroshkina.testapplication.registration.domain

import com.example.androidtutorial2.resources.StringProvider
import com.nekhoroshkina.testapplication.R
import com.nekhoroshkina.testapplication.registration.domain.model.RegistrationResult
import com.nekhoroshkina.testapplication.registration.domain.model.User
import javax.inject.Inject

class RegistrationInteractorImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val stringProvider: StringProvider
) : RegistrationInteractor {

    override suspend fun registerUser(
        user: User
    ): RegistrationResult {
        val (isValid, message) = validateInput(user)
        if (!isValid) {
            return RegistrationResult.Error(message)
        }

        if (userRepository.isUserExists(user.email)) {
            return RegistrationResult.Error(stringProvider.getString(R.string.error_email_exists))
        }

        userRepository.saveUser(user)
        return RegistrationResult.Success
    }

    private fun validateInput(
        user: User
    ): Pair<Boolean, String> {
        return when {
            user.name.isBlank() || user.name.length > 35 -> false to stringProvider.getString(R.string.error_name_length)
            user.email.isBlank() || user.email.length > 50 || !android.util.Patterns.EMAIL_ADDRESS.matcher(
                user.email
            ).matches() ->
                false to stringProvider.getString(R.string.error_invalid_email)

            user.password.length !in 5..64 -> false to stringProvider.getString(R.string.error_password_length)
            user.password != user.confirmPassword -> false to stringProvider.getString(R.string.error_passwords_do_not_match)
            else -> true to ""
        }
    }
}
