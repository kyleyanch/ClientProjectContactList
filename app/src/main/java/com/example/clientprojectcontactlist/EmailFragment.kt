package com.example.clientprojectcontactlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.clientprojectcontactlist.Model.Contact
import com.example.clientprojectcontactlist.Model.ContactType

class EmailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_email, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val contactNameInput = view.findViewById<EditText>(R.id.cEmailName)
        val contactEmailInput = view.findViewById<EditText>(R.id.cEmailAddress)
        val submitEmailBtn = view.findViewById<Button>(R.id.emailSubmitBtn)

        submitEmailBtn.setOnClickListener{
            val contactName = contactNameInput.text.toString().trim()
            val contactEmail = contactEmailInput.text.toString().trim()

            if(contactName.isEmpty() || contactEmail.isEmpty()){
                Toast.makeText(requireContext(), "Field cannot be empty.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val contacts = Contact(
                name = contactName,
                info = contactEmail,
                type = ContactType.Email
            )

            val emailActivity = activity
            if (emailActivity is MainActivity){
                emailActivity.addContact(contacts)
            }

            contactNameInput.text.clear()
            contactEmailInput.text.clear()
        }
    }
}