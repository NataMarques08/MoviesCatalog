package com.example.tvshowcatalog.rest

import com.example.tvshowcatalog.model.TvShowsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitServiceTvShows {
    @GET("shows")
   fun getAllTvShows() : Call<List<TvShowsResponse>>


   companion object{
       private val retrofitServiceTvShows : RetrofitServiceTvShows by lazy{
           val retrofit = Retrofit.Builder()
               .baseUrl("https://api.tvmaze.com/")
               .addConverterFactory(GsonConverterFactory.create())
               .build()
           retrofit.create(RetrofitServiceTvShows::class.java)
       }
       fun getInstance() : RetrofitServiceTvShows{
           return retrofitServiceTvShows
       }
   }

}