package com.example.tvshowcatalog.viewmodel.vmtvshow



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tvshowcatalog.repository.repositorytvshows.TvShowsRepository

class TvShowsViewModelFactory constructor(private val tvShowsRepository: TvShowsRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TvShowsViewModel::class.java)) {
            TvShowsViewModel(this.tvShowsRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}