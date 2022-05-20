package com.example.tvshowcatalog.viewmodel.vmschedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tvshowcatalog.model_schedule.ScheduleResponse
import com.example.tvshowcatalog.repository.repositoryschedule.MainRepositorySchedule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModelSchedule(private val repositorySchedule: MainRepositorySchedule) : ViewModel() {
    val scheduleList = MutableLiveData<List<ScheduleResponse>>()
    val scheduleError = MutableLiveData<String>()

    fun getAllSchedule(){
        val _response = repositorySchedule.getAllSchedule()
        _response.enqueue(object : Callback<List<ScheduleResponse>>{
            override fun onResponse(
                call: Call<List<ScheduleResponse>>,
                response: Response<List<ScheduleResponse>>
            ) {
                scheduleList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<ScheduleResponse>>, t: Throwable) {
                scheduleError.postValue(t.message)
            }

        })
    }

}