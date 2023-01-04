package com.deepu.myandroidapp.common

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LoginValidationTest {

    @Test
    fun `valid userName and password returns true`() {
        val userName = "hello"
        val password = "hellohellohello"
        assertThat(
            LoginValidation.validateLoginInput(
                userName = userName,
                password = password
            )
        ).isTrue()
    }

    @Test
    fun `emptyUserNameAndPassword returns false`() {
        val userName = ""
        val password = ""
        assertThat(
            LoginValidation.validateLoginInput(
                userName = userName,
                password = password
            )
        ).isFalse()
    }

    @Test
    fun `emptyUserName returns false`() {
        val userName = ""
        val password = "hellohellohello"
        assertThat(
            LoginValidation.validateLoginInput(
                userName = userName,
                password = password
            )
        ).isFalse()
    }

    @Test
    fun `emptyPassword returns false`() {
        val userName = "hello"
        val password = ""
        assertThat(
            LoginValidation.validateLoginInput(
                userName = userName,
                password = password
            )
        ).isFalse()
    }

}