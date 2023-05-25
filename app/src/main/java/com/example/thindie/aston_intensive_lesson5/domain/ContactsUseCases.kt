package com.example.thindie.aston_intensive_lesson5.domain


import com.example.thindie.aston_intensive_lesson5.domain.entity.Contact
import javax.inject.Inject

internal class ContactsUseCases @Inject constructor(
    private val repository: ContactsRepository,
) :
    ContactProvider {
    override fun onUpdate(oldContact: Contact, newContact: Contact) {
        repository.updateContact(oldContact, newContact)
    }

    override fun onRequestContacts(criteria: String?): List<Contact> {
        return repository
            .getContacts(criteria)

    }

    override fun onDeleteContact(contact: Contact) {
        return repository.deleteContact(contact)
    }
}