package com.example.thindie.aston_intensive_lesson5.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.thindie.aston_intensive_lesson5.FragmentsRouter
import com.example.thindie.aston_intensive_lesson5.R
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ConcreteContactFragment : Fragment() {
    private val viewModel: ContactsScreenViewModel by activityViewModels()
    private val router: FragmentsRouter? by lazy { requireActivity() as? FragmentsRouter }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_concrete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(
            ContactLayoutHolder(
                name = view.findViewById(R.id.input_name),
                surname = view.findViewById(R.id.input_surname),
                phone = view.findViewById(R.id.input_phone),
            )
        )

        val button: Button = view.findViewById(R.id.button_save)

        button.setOnClickListener {
            viewModel.onUpdateContact()
            requireNotNull(router)
                .router
                .navigate(ContactsListFragment())
        }
    }

    private fun observe(viewHolder: ContactLayoutHolder) {
            viewModel.contacts.observe(viewLifecycleOwner) { contactsUIstate ->
                viewHolder.name
                    .findViewById<TextInputEditText>(R.id.text_input_edit)
                    .addTextChangedListener { changingText ->
                        viewModel.onUpdateContactName(changingText.toString())
                    }
                viewHolder.name
                    .findViewById<TextView>(R.id.textview_input_field_id)
                    .text = getString(R.string.contact_name).plus(contactsUIstate.contact?.name)


                viewHolder.surname
                    .findViewById<TextInputEditText>(R.id.text_input_edit)
                    .addTextChangedListener { changingText ->
                        viewModel.onUpdateContactSurname(changingText.toString())
                    }
                viewHolder.surname
                    .findViewById<TextView>(R.id.textview_input_field_id)
                    .text =
                    getString(R.string.contact_surname).plus(contactsUIstate.contact?.surname)

                viewHolder.phone
                    .findViewById<TextInputEditText>(R.id.text_input_edit)
                    .addTextChangedListener { changingText ->
                        viewModel.onUpdateContactPhone(changingText.toString())
                    }
                viewHolder.phone
                    .findViewById<TextView>(R.id.textview_input_field_id)
                    .text =
                    getString(R.string.contact_phone).plus(contactsUIstate.contact?.phoneNumber)
            }
    }
}

data class ContactLayoutHolder(
    val name: LinearLayout,
    val surname: LinearLayout,
    val phone: LinearLayout,
)

