package com.example.tvshowcatalog.rest

import com.example.tvshowcatalog.model_schedule.ScheduleResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface RetrofitServiceSchedule {
    @GET("schedule")
    fun getAllSchedule() : Call<List<ScheduleResponse>>

    companion object{
        private val retrofitServiceSchedule : RetrofitServiceSchedule by lazy{
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.tvmaze.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(RetrofitServiceSchedule::class.java)
        }
        fun getAllInstanceSchedulle() : RetrofitServiceSchedule{
            return retrofitServiceSchedule
        }
    }

}