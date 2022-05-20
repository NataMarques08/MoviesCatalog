package com.example.tvshowcatalog.rest

import com.example.tvshowcatalog.model_nextEpisodes.NextEpisodes
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitServiceNextEpisodes {
    @GET("episodes")
    fun getAllNextEpisodes() : Call<List<NextEpisodes>>

    companion object{
        private val retrofitServiceNextEpisodes : RetrofitServiceNextEpisodes by lazy{
            val retrofit = Retrofit.Builder()
                .baseUrl(" https://api.tvmaze.com/shows/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
           retrofit.create(RetrofitServiceNextEpisodes::class.java)
        }
        fun getInstanceNextEpisodes() : RetrofitServiceNextEpisodes{
            return retrofitServiceNextEpisodes
        }
    }

}