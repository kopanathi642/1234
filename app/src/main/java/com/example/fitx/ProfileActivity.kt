package com.example.fitx

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.switchmaterial.SwitchMaterial

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile) // Ensure your XML file is named activity_profile.xml

        setupUserInfo()
        setupButtons()
        setupBottomNavigation()
    }

    private fun setupUserInfo() {
        // Example: You could load these from SharedPreferences or a Database
        val tvName = findViewById<TextView>(R.id.tvUserName)
        val tvEmail = findViewById<TextView>(R.id.tvUserEmail)

        // tvName.text = "Alex Johnson" // Already set in XML, but this is how you'd update it dynamically
    }

    private fun setupButtons() {
        // 1. Edit Profile Button
        findViewById<MaterialButton>(R.id.btnEditProfile).setOnClickListener {
            Toast.makeText(this, "Edit Profile clicked", Toast.LENGTH_SHORT).show()
            // val intent = Intent(this, EditProfileActivity::class.java)
            // startActivity(intent)
        }

        // 2. Notification Switch
        val notifSwitch = findViewById<SwitchMaterial>(R.id.swNotifications)

        notifSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Notifications Enabled", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifications Disabled", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. Privacy & Security
        findViewById<LinearLayout>(R.id.optPrivacy).setOnClickListener {
            Toast.makeText(this, "Opening Privacy Settings...", Toast.LENGTH_SHORT).show()
        }

        // 4. Logout
        findViewById<LinearLayout>(R.id.optLogout).setOnClickListener {
            performLogout()
        }

        // 5. FAB (Floating Action Button)
        findViewById<FloatingActionButton>(R.id.fabEdit).setOnClickListener {
            Toast.makeText(this, "Notifications / Quick Action", Toast.LENGTH_SHORT).show()
        }
    }

    private fun performLogout() {
        Toast.makeText(this, "Logging Out...", Toast.LENGTH_SHORT).show()

        // Logic to clear user session would go here

        // Redirect to Login Screen
        // val intent = Intent(this, LoginActivity::class.java)
        // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        // startActivity(intent)
        // finish()
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        // Highlight the "Profile" item
        bottomNav.selectedItemId = R.id.navigation_profile

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                // 1. HOME
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }

                // 2. TRAINING
                R.id.navigation_training -> {
                    startActivity(Intent(this, TrainingActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }

                // 3. COMMUNITY
                R.id.navigation_community -> {
                    startActivity(Intent(this, SocialActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }

                // 4. PROFILE (Stay here)
                R.id.navigation_profile -> true

                else -> false
            }
        }
    }
}