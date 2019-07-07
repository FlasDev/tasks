package com.example.epamtasks.data

data class Contact(
    var id: String = "",
    var name: String = "",
    var phone: List<String> = emptyList(),
    var organization: List<String> = emptyList(),
    var email: List<String> = emptyList()
)