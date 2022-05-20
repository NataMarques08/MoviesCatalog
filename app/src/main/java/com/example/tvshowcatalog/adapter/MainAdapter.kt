package com.example.tvshowcatalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tvshowcatalog.databinding.ListMovieActivityBinding
import com.example.tvshowcatalog.model.TvShowsResponse
import com.example.tvshowcatalog.model_schedule.ScheduleResponse

class MainAdapter(private val onItemClicked : (TvShowsResponse) -> Unit) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {


    private var movieList = mutableListOf<TvShowsResponse>()


    fun setMovieList(movieList : List<TvShowsResponse>){
        this.movieList = movieList.toMutableList()
        notifyDataSetChanged()
    }

    class MyViewHolder(private val binding: ListMovieActivityBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(tvShowsContent : TvShowsResponse, onItemClicked: (TvShowsResponse) -> Unit){

            binding.imageViewMovie.load(tvShowsContent.image.original){
                crossfade(true)
                crossfade(1000)
            }
            itemView.setOnClickListener {
                onItemClicked(tvShowsContent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListMovieActivityBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var movie = movieList[position]
        holder.bind(movie,onItemClicked)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}