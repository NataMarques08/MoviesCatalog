package com.example.tvshowcatalog.repository.repositorynxepisode

import com.example.tvshowcatalog.rest.RetrofitServiceNextEpisodes

class NextEpisodeRepository constructor(private val retrofitServiceNextEpisodes: RetrofitServiceNextEpisodes) {
    fun getAllNextEpisodes() = retrofitServiceNextEpisodes.getAllNextEpisodes()
}