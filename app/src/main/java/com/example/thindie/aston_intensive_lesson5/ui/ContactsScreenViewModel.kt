package com.example.thindie.aston_intensive_lesson5.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thindie.aston_intensive_lesson5.domain.ContactProvider
import com.example.thindie.aston_intensive_lesson5.ui.recyclerviewadapter.ContactsListRecyclerViewListAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactsScreenViewModel @Inject constructor(private val provider: ContactProvider) :
    ViewModel() {

    val contactListAdapter: ContactsListRecyclerViewListAdapter =
        ContactsListRecyclerViewListAdapter()


    private val _contacts: MutableLiveData<ContactsUIState> = MutableLiveData()
    val contacts: LiveData<ContactsUIState>
        get() = _contacts

    private val _contactToBeUpdated: MutableState<ContactUiModel?> = mutableStateOf(null)
    private val _bufferChangingContact: MutableState<ContactUiModel?> = mutableStateOf(null)

    fun onFetchContact(criteria: String? = null) {
        _contacts.value =
            ContactsUIState(provider.onRequestContacts(criteria).map { it.toUiModel() })
    }

    fun onDelete(contact: ContactUiModel) {
        provider.onDeleteContact(contact.toContact())
        onFetchContact()
    }

    fun onUpdateContact() {
        if (_contacts.value?.contact != _contactToBeUpdated.value)
            provider.onUpdate(
                requireNotNull(_contacts.value?.contact?.toContact()),
                requireNotNull(_contactToBeUpdated.value?.toContact())
            )
    }

    fun onUpdateContactName(name: String) {
        _contactToBeUpdated.value = requireNotNull(_bufferChangingContact.value).copy(name = name)
        _bufferChangingContact.value = (_contactToBeUpdated.value)

    }

    fun onUpdateContactSurname(surname: String) {
        _contactToBeUpdated.value =
            requireNotNull(_bufferChangingContact.value).copy(surname = surname)
        _bufferChangingContact.value = (_contactToBeUpdated.value)

    }

    fun onUpdateContactPhone(phone: String) {
        _contactToBeUpdated.value =
            requireNotNull(_bufferChangingContact.value).copy(phoneNumber = phone)
        _bufferChangingContact.value = (_contactToBeUpdated.value)

    }

    fun onShowConcreteContact(contact: ContactUiModel) {
        _contacts.value = ContactsUIState(contact = contact)
        _contactToBeUpdated.value = contact
        _bufferChangingContact.value = contact
    }


    data class ContactsUIState(
        val contactList: List<ContactUiModel> = emptyList(),
        val contact: ContactUiModel? = null
    )


}

inline fun <reified T : Any?> T.ok(): T {
    checkNotNull(this) { "${T::class.java} is null" }
    val obj: Any = this
    return obj as T
}





