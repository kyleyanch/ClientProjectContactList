package com.example.clientprojectcontactlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clientprojectcontactlist.Model.Contact
import com.example.clientprojectcontactlist.Model.ContactType

class ContactAdapter (private val dataSet: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>(){

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.nameView)
        val nameTxt: TextView = view.findViewById(R.id.nameOutputView)
        val details: TextView = view.findViewById(R.id.detailsView)
        val detailsTxt: TextView = view.findViewById(R.id.detailedOutputView)
        val img: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = dataSet?.get(position)
        if(contact != null){
            holder.nameTxt.text = contact.name
            holder.detailsTxt.text = contact.info

            val imgIcon: Int

            if(contact.type == ContactType.Email){
                imgIcon = R.drawable.round_email_24
            }
            else{
                imgIcon = R.drawable.round_contacts_24
            }

            holder.img.setImageResource(imgIcon)

        }
    }


}