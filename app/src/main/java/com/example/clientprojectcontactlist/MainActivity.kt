package com.example.clientprojectcontactlist

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.clientprojectcontactlist.Model.Contact
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private val contactList = mutableListOf<Contact>()
    private lateinit var contactFragment: ContactFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize fragment
        contactFragment = ContactFragment()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, contactFragment)
                .commit()
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navEmail -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, EmailFragment())
                        .commit()
                    true
                }

                R.id.navPhone -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, PhoneFragment())
                        .commit()
                    true
                }

                R.id.navList -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, contactFragment)
                        .commit()
                    contactFragment.setContacts(contactList)
                    true
                }

                else -> false
            }
        }
    }

    fun addContact(contact: Contact) {
        contactList.add(contact)
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        if (currentFragment is ContactFragment) {
            currentFragment.addOneContact(contact)
        }
    }

    override fun onPause() {
        super.onPause()
        saveContactsToPrefs()
    }

    override fun onResume() {
        super.onResume()
        loadContactsFromPrefs()
    }

    private fun saveContactsToPrefs() {
        val prefs = getSharedPreferences("contact_prefs", Context.MODE_PRIVATE)
        val json = Gson().toJson(contactList)
        prefs.edit().putString("contact_list", json).apply()
    }

    private fun loadContactsFromPrefs() {
        val prefs = getSharedPreferences("contact_prefs", Context.MODE_PRIVATE)
        val json = prefs.getString("contact_list", null)
        if (!json.isNullOrEmpty()) {
            val type = object : TypeToken<MutableList<Contact>>() {}.type
            val savedList: MutableList<Contact> = Gson().fromJson(json, type)
            contactList.clear()
            contactList.addAll(savedList)

            // Safely update fragment if it's visible
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
            if (currentFragment is ContactFragment) {
                currentFragment.setContacts(contactList)
            }
        }
    }
}