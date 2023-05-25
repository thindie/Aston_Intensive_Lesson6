package com.example.thindie.aston_intensive_lesson5.domain

import com.example.thindie.aston_intensive_lesson5.domain.entity.Contact

interface ContactsRepository {
    fun updateContact(oldContact: Contact, newContact: Contact)
    fun getContacts(criteria: String?): List<Contact>
    fun deleteContact(contact: Contact)
}