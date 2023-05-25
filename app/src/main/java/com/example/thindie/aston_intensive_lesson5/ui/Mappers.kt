package com.example.thindie.aston_intensive_lesson5.ui

import com.example.thindie.aston_intensive_lesson5.domain.entity.Contact

data class ContactUiModel(
    val id: Long,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val imageUrl: String
)

fun Contact.toUiModel(): ContactUiModel {
    return ContactUiModel(
        id = id,
        name = name,
        surname = surname,
        phoneNumber = phoneNumber,
        imageUrl = imageUrl
    )
}

fun ContactUiModel.toContact(): Contact {
    return Contact(
        id = id,
        name = name,
        surname = surname,
        phoneNumber = phoneNumber,
        imageUrl = imageUrl
    )
}