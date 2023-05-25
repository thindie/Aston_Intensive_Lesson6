package com.example.thindie.aston_intensive_lesson5.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.thindie.aston_intensive_lesson5.FragmentsRouter
import com.example.thindie.aston_intensive_lesson5.R
import com.example.thindie.aston_intensive_lesson5.databinding.FragmentAllBinding
import com.example.thindie.aston_intensive_lesson5.ui.recyclerviewadapter.ContactsListRecyclerViewListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactsListFragment : Fragment() {

    private val viewModel: ContactsScreenViewModel by activityViewModels()
    private val router: FragmentsRouter? by lazy { requireActivity() as? FragmentsRouter }

    private var _binding: FragmentAllBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        setupSearchBar()
        viewModel.onFetchContact()
    }

    private fun setupSearchBar() {
        binding.searchAllContactsEdit.addTextChangedListener { editable ->
            viewModel.onFetchContact(editable.toString())
        }
        binding.buttonSearch.setOnClickListener {
            binding.searchAllContactsEdit.setText(R.string.reset_editable_text_field)
        }
    }

    private fun setupRecycleView() {
        val recyclerView: RecyclerView = binding.recyclerViewContacts
        recyclerView.recycledViewPool.setMaxRecycledViews(
            ContactsListRecyclerViewListAdapter.VIEW_TYPE,
            resources.getInteger(R.integer.recycler_view_pool)
        )
        recyclerView.adapter = viewModel.contactListAdapter
        setClickListeners(viewModel.contactListAdapter)
        lifecycleScope.launch {
            viewModel.contacts
                .observe(viewLifecycleOwner) { contacts ->
                    viewModel.contactListAdapter.submitList(contacts.contactList)
                }
        }
    }

    private fun setClickListeners(adapter: ContactsListRecyclerViewListAdapter) {
        adapter.onContactLongClickListener = { contact, position ->
            binding.recyclerViewContacts.recycledViewPool.clear()
            viewModel.onDelete(contact)
        }

        adapter.onContactClickListener = { contact ->
            viewModel.onShowConcreteContact(contact)
            requireNotNull(router)
                .router
                .navigate(ConcreteContactFragment())
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}