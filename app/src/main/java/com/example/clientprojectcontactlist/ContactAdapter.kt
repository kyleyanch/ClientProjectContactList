package com.example.clientprojectcontactlist

import android.content.Intent
import android.net.Uri
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

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = dataSet[position]
        holder.nameTxt.text = contact.name
        holder.detailsTxt.text = contact.info

        val context = holder.itemView.context
        val imgIcon: Int

        if (contact.type == ContactType.Email) {
            imgIcon = R.drawable.round_email_24
            holder.itemView.setOnClickListener {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${contact.info}")
                }
                context.startActivity(emailIntent)
            }
        } else {
            imgIcon = R.drawable.round_contacts_24
            holder.itemView.setOnClickListener {
                val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${contact.info}")
                }
                context.startActivity(dialIntent)
            }
        }

        holder.img.setImageResource(imgIcon)
    }


}