package com.example.clientprojectcontactlist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.clientprojectcontactlist.Model.Contact

class ContactFragment : Fragment() {
    private val contactList = mutableListOf<Contact>()
    private lateinit var adapter: ContactAdapter

    fun onContactSubmit(contact: Contact) {
        contactList.add(contact)
        adapter.notifyItemInserted(contactList.size - 1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val nameInput = view.findViewById<EditText>(R.id.)

    }



}