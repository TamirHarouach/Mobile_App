package com.example.cirestechnologiesmobilechallenge.core.util

import com.example.cirestechnologiesmobilechallenge.domain.model.User

object Constants {
    const val SHOW_ON_BOARDING: String = "show_on_boarding"

    val USER_VALID : User = User("muser", "mpassw0rd")
    val USER_BLOCKED : User = User("muser02", "mpassword")

    const val SUCCESS : String = "success"
    const val ERROR : String = "error"
    const val BLOCKED : String = "blocked"
}