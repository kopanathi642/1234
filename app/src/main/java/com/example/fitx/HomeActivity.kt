package com.example.fitx

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard) // Ensure XML is named 'dashboard.xml'

        setupCharacterImage()
        setupDashboardMetrics()
        setupHeaderInteractions()
        setupBottomNavigation()
    }

    private fun setupCharacterImage() {
        val characterImageView = findViewById<ImageView>(R.id.character_video)
        // Ensure you have your fox image in 'res/drawable'
        characterImageView.setImageResource(R.drawable.male_character)
    }

    private fun setupDashboardMetrics() {
        // 1. Steps Card
        findViewById<TextView>(R.id.steps_value).text = "8,240 / 10,000"

        // 2. Calories Card
        findViewById<TextView>(R.id.calories_value).text = "640 kcal"

        // 3. Active Minutes Card
        findViewById<TextView>(R.id.minutes_value).text = "52 / 60 mins"

        // 4. Water Intake Card
        findViewById<TextView>(R.id.water_value).text = "1.5 / 2.5 L"

        // Interaction Example
        findViewById<MaterialCardView>(R.id.steps_card).setOnClickListener {
            Toast.makeText(this, "Opening Steps Details...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupHeaderInteractions() {
        findViewById<ImageView>(R.id.notification_button).setOnClickListener {
            Toast.makeText(this, "No new notifications", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageView>(R.id.streak_icon).setOnClickListener {
            Toast.makeText(this, "You are on a 5-day streak! Keep it up!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        // Set the "Home" item as currently selected
        bottomNav.selectedItemId = R.id.navigation_home

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                // 1. HOME (Already here)
                R.id.navigation_home -> true

                // 2. TRAINING
                R.id.navigation_training -> {
                    val intent = Intent(this, TrainingActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }

                // 3. COMMUNITY
                R.id.navigation_community -> {
                    val intent = Intent(this, SocialActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }

                // 4. PROFILE (UPDATED: Now opens ProfileActivity)
                R.id.navigation_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }
    }
}