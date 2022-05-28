package com.example.loginfirebase

import java.util.*

data class UserModel (
    var id: Int = getAutoId(),
    var name: String = "",
    var ident: String = "",
    var password: String = ""
){
    companion object {
        fun getAutoId(): Int {
            val random = Random()
            return random.nextInt(100)
        }
    }
}