package com.example.fitx

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

// 1. The Data Model
data class ChatUser(
    val name: String,
    val message: String,
    val time: String,
    val imageRes: Int // Pass R.drawable.your_image OR R.color.your_color
)

// 2. The Adapter Class
class ChatAdapter(private val userList: List<ChatUser>) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvUserName)
        val tvMessage: TextView = view.findViewById(R.id.tvLastMessage)
        val tvTime: TextView = view.findViewById(R.id.tvTime)
        val imgProfile: ShapeableImageView = view.findViewById(R.id.imgUserAvatar)

        // Null safety checks (?) in case you are using an older XML layout
        val viewOnlineStatus: View? = view.findViewById(R.id.viewOnlineStatus)
        val tvUnreadCount: TextView? = view.findViewById(R.id.tvUnreadCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_beautiful, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val user = userList[position]

        // --- ASSIGN TEXT DATA ---
        holder.tvName.text = user.name
        holder.tvMessage.text = user.message
        holder.tvTime.text = user.time

        // --- CRITICAL FIX FOR IMAGES ---
        // Use setImageResource() to make real images appear correctly.
        // setBackgroundResource() is only for colors or shapes.
        holder.imgProfile.setImageResource(user.imageRes)

        // --- REALISTIC LOGIC (Online/Unread) ---
        // This forces the first 2 users (index 0 and 1) to look "Online"
        if (position <= 1) {
            holder.viewOnlineStatus?.visibility = View.VISIBLE
            holder.tvUnreadCount?.visibility = View.VISIBLE
            holder.tvUnreadCount?.text = "2"
            holder.tvTime.setTextColor(Color.parseColor("#B4F656")) // Neon Green
        } else {
            holder.viewOnlineStatus?.visibility = View.GONE
            holder.tvUnreadCount?.visibility = View.GONE
            holder.tvTime.setTextColor(Color.parseColor("#666666")) // Grey
        }
    }

    override fun getItemCount() = userList.size
}