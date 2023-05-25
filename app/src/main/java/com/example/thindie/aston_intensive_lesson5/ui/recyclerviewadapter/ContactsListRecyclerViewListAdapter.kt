package com.example.thindie.aston_intensive_lesson5.ui.recyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import coil.load
import coil.transform.CircleCropTransformation
import com.example.thindie.aston_intensive_lesson5.R
import com.example.thindie.aston_intensive_lesson5.ui.ContactUiModel
import com.example.thindie.aston_intensive_lesson5.databinding.ItemContactBinding
import kotlinx.coroutines.Dispatchers

class ContactsListRecyclerViewListAdapter :
    ListAdapter<ContactUiModel, ContactsListViewHolder>(ContactDiffUtilCallBack()) {

    private var binding: ItemContactBinding? = null


    var onContactLongClickListener: ((ContactUiModel, Int) -> Unit)? = null
    var onContactClickListener: ((ContactUiModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsListViewHolder {
        binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactsListViewHolder(requireNotNull(binding))
    }


    override fun getItemId(position: Int): Long {
        return currentList[position].id
    }

    override fun getItemViewType(position: Int): Int {
        return  VIEW_TYPE
    }

    override fun onBindViewHolder(holder: ContactsListViewHolder, position: Int) {
        val contact = getItem(position)
        holder.name.text = contact.name
        holder.surname.text = contact.surname
        holder.image.load(contact.imageUrl) {
            transformationDispatcher(Dispatchers.IO)
            transformations(CircleCropTransformation())
            allowConversionToBitmap(true)
        }
        holder.click.setOnLongClickListener {
            requireNotNull(onContactLongClickListener).invoke(contact, holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            true
        }
        holder.click.setOnClickListener {
            requireNotNull(onContactClickListener).invoke(contact)
        }

    }
    companion object{
        const val VIEW_TYPE = R.id.contact_item_layout
    }

}


class ContactsListViewHolder(viewBinding: ViewBinding) :
    ViewHolder(viewBinding.root) {
    private val contactBinding: ItemContactBinding? = viewBinding as? ItemContactBinding
    val click = requireNotNull(contactBinding).contactItemLayout
    val image = requireNotNull(contactBinding).imageviewContact
    val surname = requireNotNull(contactBinding).textviewSurname
    val name = requireNotNull(contactBinding).textviewName

}


class ContactDiffUtilCallBack : DiffUtil.ItemCallback<ContactUiModel>() {
    override fun areItemsTheSame(oldItem: ContactUiModel, newItem: ContactUiModel) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ContactUiModel, newItem: ContactUiModel) = oldItem == newItem

}
