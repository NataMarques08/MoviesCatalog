package com.example.tvshowcatalog.viewmodel.vmtvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tvshowcatalog.model.TvShowsResponse
import com.example.tvshowcatalog.repository.repositorytvshows.TvShowsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowsViewModel constructor(private val tvShowsRepository: TvShowsRepository) : ViewModel(){
    val tvShowList = MutableLiveData<List<TvShowsResponse>>()
    val tvShowErrorList = MutableLiveData<String>()

   fun getAllTvShows(){
       val _response = tvShowsRepository.getAllTvShows()
       _response.enqueue(object: Callback<List<TvShowsResponse>>{
           override fun onResponse(
               call: Call<List<TvShowsResponse>>,
               response: Response<List<TvShowsResponse>>
           ) {
               tvShowList.postValue(response.body())
           }

           override fun onFailure(call: Call<List<TvShowsResponse>>, t: Throwable) {
                tvShowErrorList.postValue(t.message)
           }

       })
   }


}