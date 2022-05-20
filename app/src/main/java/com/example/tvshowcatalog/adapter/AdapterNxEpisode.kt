package com.example.tvshowcatalog.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.tvshowcatalog.databinding.ListMovieActivityBinding
import com.example.tvshowcatalog.model_nextEpisodes.NextEpisodes



class AdapterNxEpisode(private val onItemClicked : (NextEpisodes) -> Unit) : RecyclerView.Adapter<AdapterNxEpisode.MyViewHolder>() {

    private var nxEpisodeList = mutableListOf<NextEpisodes>()


    fun setNxEpisodeList(nxEpisode : List<NextEpisodes>){
        this.nxEpisodeList = nxEpisode.toMutableList()
        notifyDataSetChanged()
    }

    class MyViewHolder(private val binding: ListMovieActivityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(nxep: NextEpisodes, onItemClicked: (NextEpisodes) -> Unit) {
            binding.textViewMovie.text = nxep.name
            binding.imageViewMovie.load(nxep.image.original) {
                crossfade(true)
                crossfade(1000)
            }
            itemView.setOnClickListener {
                onItemClicked(nxep)
            }
        }
    }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val inflate = LayoutInflater.from(parent.context)
            val binding = ListMovieActivityBinding.inflate(inflate,parent,false)
            return MyViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val nxEpi = nxEpisodeList[position]
            holder.bind(nxEpi,onItemClicked)
        }

        override fun getItemCount(): Int {
            return nxEpisodeList.size
        }
    }

