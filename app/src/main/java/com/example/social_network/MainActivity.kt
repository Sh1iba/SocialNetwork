package com.example.social_network
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.widget.Toolbar  // правильный импорт
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.social_network.fragments.ChatsFragment
import com.example.social_network.fragments.ProfileFragment
import com.example.social_network.fragments.SearchFragment
import com.example.social_network.fragments.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var fullName : String
    private lateinit var bio: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        bottomNavigationView = findViewById(R.id.bottomNavigation)

        toolbar.setBackgroundColor(resources.getColor(R.color.toolbar_background_color, null))
        toolbar.setTitleTextColor(resources.getColor(R.color.toolbar_text_color, null))
        bottomNavigationView.setBackgroundColor(ContextCompat.getColor(this, R.color.bottom_navigation_background))
        bottomNavigationView.itemIconTintList = ContextCompat.getColorStateList(this, R.color.bottom_navigation_icon_color)
        bottomNavigationView.itemTextColor = ContextCompat.getColorStateList(this, R.color.bottom_navigation_text_color)

        fullName = intent.getStringExtra("fullName").toString()
        bio = intent.getStringExtra("bio").toString()
        Log.d("MainACCCCCC","fullname: $fullName |  bio: $bio")

        val profileFragment = ProfileFragment().apply {
            arguments = Bundle().apply {
                putString("fullName", fullName)
                putString("bio", bio)
            }
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, profileFragment)
                .commit()
            toolbar.title = "Профиль"
        }


        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profile -> {
                    loadFragment(profileFragment)
                    true
                }
                R.id.search -> {
                    loadFragment(SearchFragment())
                    true
                }
                R.id.chats -> {
                    loadFragment(ChatsFragment())
                    true
                }
                R.id.settings -> {

                    loadFragment(SettingsFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        when (fragment) {
            is ProfileFragment -> toolbar.title = "Профиль"
            is ChatsFragment -> toolbar.title = "Чаты"
            is SearchFragment -> toolbar.title = "Поиск"
            is SettingsFragment -> toolbar.title = "Настройки"
        }
    }


}

