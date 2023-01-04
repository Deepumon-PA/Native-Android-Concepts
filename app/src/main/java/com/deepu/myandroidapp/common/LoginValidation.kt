package com.deepu.myandroidapp.common

object LoginValidation {

    /**
     * Test cases
     */
    fun validateLoginInput(userName: String, password: String): Boolean {
        return when {
            userName.isEmpty() -> {
                false
            }

            password == "" -> {
                false
            }

            password.length < 4->{
                false
            }

            else -> true
        }
    }
}