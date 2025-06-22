package com.example.clientprojectcontactlist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clientprojectcontactlist.Model.Contact

class ContactFragment : Fragment() {
    private val contactList = mutableListOf<Contact>()
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.contactList)
        adapter = ContactAdapter(contactList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    fun setContacts(newListContact: List<Contact>) {
        val oldSize = contactList.size
        contactList.addAll(newListContact)
        adapter.notifyItemRangeInserted(oldSize, newListContact.size)
    }

    fun addOneContact(contact: Contact){
        contactList.add(contact)
        adapter.notifyItemInserted(contactList.size -1)
    }
}