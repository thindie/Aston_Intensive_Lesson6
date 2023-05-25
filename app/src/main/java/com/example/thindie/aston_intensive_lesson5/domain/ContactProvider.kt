package com.example.thindie.aston_intensive_lesson5.domain

import com.example.thindie.aston_intensive_lesson5.domain.entity.Contact

interface ContactProvider {
    fun onUpdate(oldContact: Contact, newContact: Contact)
    fun onRequestContacts(criteria: String?): List<Contact>
    fun onDeleteContact(contact: Contact)


}