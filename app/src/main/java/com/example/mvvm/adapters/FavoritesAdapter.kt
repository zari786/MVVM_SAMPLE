package com.example.mvvm.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.databinding.AdapterPostBinding
import com.example.mvvm.models.Post
import javax.inject.Inject

class FavoritesAdapter @Inject constructor() : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    var posts = mutableListOf<Post>()


    @SuppressLint("notifyDataSetChanged")
    fun setPostList(posts: List<Post>) {
        this.posts = posts.toMutableList()
        notifyDataSetChanged()
    }


    class FavoritesViewHolder(val adapterPostBinding: AdapterPostBinding) :
        RecyclerView.ViewHolder(adapterPostBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val adapterPostBinding = AdapterPostBinding.inflate(inflater, parent, false)
        return FavoritesViewHolder(adapterPostBinding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val post = posts[position]
        holder.adapterPostBinding.title.text = post.title
        holder.adapterPostBinding.body.text = post.body

    }

    override fun getItemCount(): Int {
        return posts.size
    }

}


