package com.example.tvshowcatalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tvshowcatalog.databinding.ListMovieActivityBinding

import com.example.tvshowcatalog.model_schedule.ScheduleResponse

class AdapterSchedule(private val onItemClicked : (ScheduleResponse) -> Unit) : RecyclerView.Adapter<AdapterSchedule.MyViewHolder>() {


    private var movieListSchedule = mutableListOf<ScheduleResponse>()


    fun setScheduleList(movieList: List<ScheduleResponse>) {
        this.movieListSchedule = movieList.toMutableList()
        notifyDataSetChanged()
    }

    class MyViewHolder(private val binding: ListMovieActivityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(scheduleContent: ScheduleResponse, onItemClicked: (ScheduleResponse) -> Unit) {
            binding.textViewMovie.text = scheduleContent.name
            binding.imageViewMovie.load(scheduleContent.show.image.original) {
                crossfade(true)
                crossfade(1000)
            }
            itemView.setOnClickListener {
                onItemClicked(scheduleContent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListMovieActivityBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movieListSchedule[position]
        holder.bind(movie,onItemClicked)
    }

    override fun getItemCount(): Int {
        return movieListSchedule.size
    }
}
