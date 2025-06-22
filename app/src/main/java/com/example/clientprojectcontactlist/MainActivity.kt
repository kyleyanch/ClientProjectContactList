package com.example.clientprojectcontactlist

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
import com.example.clientprojectcontactlist.Model.Contact
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var submitBtn: Button
    private val contactList = mutableListOf<Contact>()
    private lateinit var adapter: ContactAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var contactFragment: ContactFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        contactFragment = ContactFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, contactFragment)
            .commit()

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
                        .commitNow()
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
}