package com.example.tvshowcatalog.viewmodel.vmnxepisode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tvshowcatalog.repository.repositorynxepisode.NextEpisodeRepository

class ViewModelNextEpisodeFactory constructor(private val nextEpisodeRepository: NextEpisodeRepository) :
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(ViewModelNextEpisode::class.java)){
            ViewModelNextEpisode(this.nextEpisodeRepository) as T
        }else{
            throw IllegalArgumentException("View Model not Found!")
        }
    }

    }