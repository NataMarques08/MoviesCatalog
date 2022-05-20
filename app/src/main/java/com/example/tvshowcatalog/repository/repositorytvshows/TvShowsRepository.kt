package com.example.tvshowcatalog.repository.repositorytvshows

import com.example.tvshowcatalog.rest.RetrofitServiceTvShows

class TvShowsRepository constructor(private val retrofitServiceTvShows: RetrofitServiceTvShows) {
    fun getAllTvShows() = retrofitServiceTvShows.getAllTvShows()
}