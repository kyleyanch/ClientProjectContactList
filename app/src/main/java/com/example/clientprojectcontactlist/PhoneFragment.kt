package com.example.clientprojectcontactlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.clientprojectcontactlist.Model.Contact
import com.example.clientprojectcontactlist.Model.ContactType

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PhoneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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
        val subitPhoneBtn = view.findViewById<EditText>(R.id.phoneSubmitBtn)

        subitPhoneBtn.setOnClickListener{
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

            contactNameInput.text.clear()
            contactPhoneInput.text.clear()
        }
    }
}