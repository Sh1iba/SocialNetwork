package com.example.social_network
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.widget.Toolbar  // правильный импорт
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)  // Устанавливаем Toolbar как ActionBar

        viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottomNavigation)

        toolbar.setBackgroundColor(resources.getColor(R.color.toolbar_background_color, null))
        toolbar.setTitleTextColor(resources.getColor(R.color.toolbar_text_color, null))
        bottomNavigationView.setBackgroundColor(ContextCompat.getColor(this, R.color.bottom_navigation_background))
        bottomNavigationView.itemIconTintList = ContextCompat.getColorStateList(this, R.color.bottom_navigation_icon_color)
        bottomNavigationView.itemTextColor = ContextCompat.getColorStateList(this, R.color.bottom_navigation_text_color)



        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        // Слушатель для выбора элементов в BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profile -> {
                    viewPager.currentItem = 0
                    true
                }
                R.id.chats -> {
                    viewPager.currentItem = 1
                    true
                }
                R.id.settings -> {
                    viewPager.currentItem = 2
                    true
                }
                else -> false
            }
        }

        // Синхронизация выбора вкладки в BottomNavigation и ViewPager
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        bottomNavigationView.selectedItemId = R.id.profile
                        toolbar.title = "Profile"  // Устанавливаем заголовок для вкладки "Profile"
                    }
                    1 -> {
                        bottomNavigationView.selectedItemId = R.id.chats
                        toolbar.title = "Chats"  // Устанавливаем заголовок для вкладки "Chats"
                    }
                    2 -> {
                        bottomNavigationView.selectedItemId = R.id.settings
                        toolbar.title = "Settings"  // Устанавливаем заголовок для вкладки "Settings"
                    }
                }
            }
        })
    }
}
