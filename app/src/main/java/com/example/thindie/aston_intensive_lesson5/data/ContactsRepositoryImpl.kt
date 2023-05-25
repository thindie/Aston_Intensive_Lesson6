package com.example.thindie.aston_intensive_lesson5.data

import com.example.thindie.aston_intensive_lesson5.data.someData.HavocDataModel
import com.example.thindie.aston_intensive_lesson5.data.someData.SomeDataHolder
import com.example.thindie.aston_intensive_lesson5.domain.ContactsRepository
import com.example.thindie.aston_intensive_lesson5.domain.entity.Contact
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ContactsRepositoryImpl @Inject constructor(private val someDataHolder: SomeDataHolder) :
    ContactsRepository {

    private var idCounter: Long = 0
    override fun updateContact(oldContact: Contact, newContact: Contact) {
        someDataHolder.updateSomeData(
            oldContact.toRaw(), newContact.toRaw()
        )
    }

    override fun getContacts(criteria: String?): List<Contact> {
        val list = if (criteria.isNullOrBlank()) {
            someDataHolder.getSomeData()
                .map { someData ->
                    asContact(someData)
                }
        } else onSearch(criteria)
        idCounter = 0
        return list
    }

    private fun onSearch(criteria: String): List<Contact> {
        return someDataHolder
            .getSomeData()
            .map { someData ->
                asContact(someData)
            }
            .filter { contact ->
                contact.name.matchCriteria(criteria) ||
                        contact.surname.matchCriteria(criteria)
            }
    }

    override fun deleteContact(contact: Contact) {
        someDataHolder.deleteData(contact.toRaw())
    }


    private fun asContact(havocDataModel: HavocDataModel): Contact {
        return Contact(
            id = idCounter++,
            havocDataModel.havocName,
            havocDataModel.havocFullName,
            havocDataModel.havocConnection,
            havocDataModel.havocPicUrl
        )
    }
}

fun Contact.toRaw(): HavocDataModel {
    return HavocDataModel(name, surname, phoneNumber, imageUrl)
}

fun String.matchCriteria(string: String): Boolean {
    return lowercase() == string.lowercase() ||
            contains(string, true) ||
            string.contains(this, ignoreCase = true)
}

