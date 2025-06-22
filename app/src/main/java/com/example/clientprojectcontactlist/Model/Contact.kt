package com.example.clientprojectcontactlist.Model

import java.io.Serializable

data class Contact (
    val name: String,
    val info: String,
    val type: ContactType
) : Serializable