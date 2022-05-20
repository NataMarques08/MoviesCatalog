package com.example.tvshowcatalog.viewmodel.vmnxepisode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tvshowcatalog.model_nextEpisodes.NextEpisodes
import com.example.tvshowcatalog.repository.repositorynxepisode.NextEpisodeRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelNextEpisode constructor(private val nextEpisodeRepository: NextEpisodeRepository) : ViewModel() {
    var nxEpisode = MutableLiveData<List<NextEpisodes>>()
    var errorNxEpisode = MutableLiveData<String>()

    fun getAllNextEpisodes() {
        val _response = nextEpisodeRepository.getAllNextEpisodes()
        _response.enqueue(object : Callback<List<NextEpisodes>>{
            override fun onResponse(
                call: Call<List<NextEpisodes>>,
                response: Response<List<NextEpisodes>>
            ) {
                nxEpisode.postValue(response.body())
            }

            override fun onFailure(call: Call<List<NextEpisodes>>, t: Throwable) {
                errorNxEpisode.postValue(t.message)
            }

        })
    }


}