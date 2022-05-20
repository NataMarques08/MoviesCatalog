package com.example.tvshowcatalog.viewmodel.vmschedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tvshowcatalog.repository.repositoryschedule.MainRepositorySchedule

class MainViewModelScheduleFactory constructor(private val repositorySchedule: MainRepositorySchedule) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModelSchedule::class.java)){
            MainViewModelSchedule(this.repositorySchedule) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}