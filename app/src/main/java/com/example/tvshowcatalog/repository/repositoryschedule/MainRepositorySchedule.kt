package com.example.tvshowcatalog.repository.repositoryschedule

import com.example.tvshowcatalog.rest.RetrofitServiceSchedule

class MainRepositorySchedule constructor(private val retrofitServiceSchedule: RetrofitServiceSchedule) {
    fun getAllSchedule() = retrofitServiceSchedule.getAllSchedule()
}