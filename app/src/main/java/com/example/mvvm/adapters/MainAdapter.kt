package com.example.mvvm.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm.R
import com.example.mvvm.databinding.AdapterMovieBinding
import com.example.mvvm.models.Movie
import android.os.Bundle
import javax.inject.Inject


class MainAdapter @Inject constructor() : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var movies = mutableListOf<Movie>()

    @SuppressLint("notifyDataSetChanged")
    fun setMovieList(movies: List<Movie>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.name.text = movie.name
        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)

        holder.binding.root.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("title", movie.name)
            bundle.putString("url", movie.imageUrl)
            Navigation.findNavController(it).navigate(R.id.action_home_to_detailsFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }


}