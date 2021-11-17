package com.example.mvvm.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.databinding.AdapterCommentBinding
import com.example.mvvm.models.Comment
import javax.inject.Inject

class CommentsAdapter @Inject constructor(val context: Context) :
    RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    var commentsList: List<Comment> = ArrayList()

    @SuppressLint("notifyDataSetChanged")
    fun setAllComments(commentsList: List<Comment>) {
        this.commentsList = commentsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CommentsViewHolder(
            DataBindingUtil.inflate(
                inflater,
                R.layout.adapter_comment,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val comment = commentsList[position]
        holder.bindView(comment)

        holder.adapterCommentBinding.root.setOnClickListener {
            holder.clickItem(comment, context)
        }
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }


    class CommentsViewHolder(val adapterCommentBinding: AdapterCommentBinding) :
        RecyclerView.ViewHolder(adapterCommentBinding.root) {

        fun bindView(comment: Comment) {
            adapterCommentBinding.comment = comment
        }

        fun clickItem(comment: Comment, context: Context) {
            Toast.makeText(context, comment.name, Toast.LENGTH_SHORT).show()
        }
    }
}