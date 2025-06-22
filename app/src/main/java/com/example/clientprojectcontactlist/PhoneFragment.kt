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

class PhoneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val contactNameInput = view.findViewById<EditText>(R.id.cNamePhone)
        val contactPhoneInput = view.findViewById<EditText>(R.id.cPhoneNum)
        val submitPhoneBtn = view.findViewById<Button>(R.id.phoneSubmitBtn)

        submitPhoneBtn.setOnClickListener{
            val contactName = contactNameInput.text.toString().trim()
            val contactPhone = contactPhoneInput.text.toString().trim()

            if(contactName.isEmpty() || contactPhone.isEmpty()){
                Toast.makeText(requireContext(), "Field cannot be empty.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val contacts = Contact(
                name = contactName,
                info = contactPhone,
                type = ContactType.Phone
            )

            val phoneActivity = activity
            if (phoneActivity is MainActivity){
                phoneActivity.addContact(contacts)
            }


            contactNameInput.text.clear()
            contactPhoneInput.text.clear()
        }
    }
}