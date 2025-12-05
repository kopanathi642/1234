package com.example.fitx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class PostAdapter(private val postList: List<PostModel>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvUserName)
        val tvTime: TextView = view.findViewById(R.id.tvPostTime)
        val tvContent: TextView = view.findViewById(R.id.tvPostContent)
        val imgAvatar: ShapeableImageView = view.findViewById(R.id.imgUserAvatar)
        val imgPost: ShapeableImageView = view.findViewById(R.id.imgPostImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        // This uses your 'item_community_post' design
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_community_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]

        holder.tvName.text = post.userName
        holder.tvTime.text = post.time
        holder.tvContent.text = post.content
        holder.imgAvatar.setImageResource(post.avatarRes)

        // Logic: Only show the big post image if one was provided
        if (post.postImageRes != null) {
            holder.imgPost.visibility = View.VISIBLE
            holder.imgPost.setImageResource(post.postImageRes)
        } else {
            holder.imgPost.visibility = View.GONE
        }
    }

    override fun getItemCount() = postList.size
}