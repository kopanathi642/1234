package com.example.fitx

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class SocialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inbox_modern) // Your specific layout file

        setupFakeData()
        setupTabsLogic()
        setupBottomNavigation()
        setupFab()
    }

    private fun setupFakeData() {
        // --- PART 1: CHATS (You already had this) ---
        // Note: I changed colors to 'R.drawable.ic_launcher_background'
        // so they act like real images. You can change back to colors if you prefer.

        val activeUsers = listOf(
            ChatUser("Sarah", "Online", "", R.drawable.ic_launcher_background),
            ChatUser("Mike", "Online", "", R.drawable.ic_launcher_background),
            ChatUser("Coach", "Online", "", R.drawable.ic_launcher_background)
        )

        val chatUsers = listOf(
            ChatUser("Alex Johnson", "Did you hit your PR today?", "2m", R.drawable.ic_launcher_background),
            ChatUser("Fitness Group", "Meet at 5 PM!", "14m", R.drawable.ic_launcher_background),
            ChatUser("Yoga Instructor", "Don't forget to stretch.", "1h", R.drawable.ic_launcher_background)
        )

        val recyclerActive = findViewById<RecyclerView>(R.id.recyclerActive)
        recyclerActive.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerActive.adapter = ChatAdapter(activeUsers)

        val recyclerChats = findViewById<RecyclerView>(R.id.recyclerChats)
        recyclerChats.layoutManager = LinearLayoutManager(this)
        recyclerChats.adapter = ChatAdapter(chatUsers)


        // --- PART 2: POSTS (THIS WAS MISSING!) ---
        // This is the code required to make the "Community Feed" work.

        val posts = listOf(
            PostModel(
                "Sarah Fitness",
                "2 hours ago",
                "Just crushed my leg day workout! Hit a new PR on squats. 100kg for 5 reps! 💪 #LegDay",
                "124", "22",
                R.drawable.ic_launcher_background, // Profile Pic
                R.drawable.ic_launcher_background  // Post Image
            ),
            PostModel(
                "Mike The Trainer",
                "5 hours ago",
                "Remember: Consistency is key. You don't have to be extreme, just consistent.",
                "85", "10",
                R.drawable.ic_launcher_background,
                null // This post has NO image (null)
            ),
            PostModel(
                "Healthy Foodie",
                "1 day ago",
                "Made this amazing avocado toast for breakfast. Recipe in bio! 🥑",
                "200", "45",
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background
            )
        )

        // Connect the Adapter to the RecyclerView
        val recyclerPosts = findViewById<RecyclerView>(R.id.recyclerPosts)
        recyclerPosts.layoutManager = LinearLayoutManager(this)
        recyclerPosts.adapter = PostAdapter(posts)
    }

    private fun setupTabsLogic() {
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val layoutChats = findViewById<LinearLayout>(R.id.layoutChats)
        val recyclerPosts = findViewById<RecyclerView>(R.id.recyclerPosts)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> { // Chats Tab
                        layoutChats.visibility = View.VISIBLE
                        recyclerPosts.visibility = View.GONE
                    }
                    1 -> { // Community Feed Tab
                        layoutChats.visibility = View.GONE
                        recyclerPosts.visibility = View.VISIBLE
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav.selectedItemId = R.id.navigation_community

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.navigation_community -> true
                R.id.navigation_training -> {
                    Toast.makeText(this, "Training", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigation_profile -> {
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupFab() {
        findViewById<FloatingActionButton>(R.id.fabAction).setOnClickListener {
            Toast.makeText(this, "Create new Message/Post", Toast.LENGTH_SHORT).show()
        }
    }
}