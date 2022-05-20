package com.example.tvshowcatalog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshowcatalog.adapter.AdapterNxEpisode
import com.example.tvshowcatalog.adapter.AdapterSchedule
import com.example.tvshowcatalog.adapter.MainAdapter
import com.example.tvshowcatalog.databinding.ActivityMainBinding
import com.example.tvshowcatalog.model.TvShowsResponse
import com.example.tvshowcatalog.repository.repositoryschedule.MainRepositorySchedule
import com.example.tvshowcatalog.repository.repositorynxepisode.NextEpisodeRepository
import com.example.tvshowcatalog.repository.repositorytvshows.TvShowsRepository
import com.example.tvshowcatalog.rest.RetrofitServiceNextEpisodes
import com.example.tvshowcatalog.rest.RetrofitServiceSchedule
import com.example.tvshowcatalog.rest.RetrofitServiceTvShows
import com.example.tvshowcatalog.viewmodel.vmnxepisode.ViewModelNextEpisode
import com.example.tvshowcatalog.viewmodel.vmnxepisode.ViewModelNextEpisodeFactory
import com.example.tvshowcatalog.viewmodel.vmschedule.MainViewModelSchedule
import com.example.tvshowcatalog.viewmodel.vmschedule.MainViewModelScheduleFactory
import com.example.tvshowcatalog.viewmodel.vmtvshow.TvShowsViewModel
import com.example.tvshowcatalog.viewmodel.vmtvshow.TvShowsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private lateinit var adapterSchedule: AdapterSchedule
    private lateinit var adapterNxEpisode: AdapterNxEpisode
    private lateinit var viewModel: TvShowsViewModel
    private lateinit var viewModelSchedule: MainViewModelSchedule
    private lateinit var viewModelNextEpisode: ViewModelNextEpisode
    private var retrofitServiceTvShows = RetrofitServiceTvShows.getInstance()
    private var retrofitServiceSchedule = RetrofitServiceSchedule.getAllInstanceSchedulle()
    private var retrofitServiceNextEpisodes = RetrofitServiceNextEpisodes.getInstanceNextEpisodes()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        startViewModelComponents()
        setUpSeriesList()
    }
    private fun startViewModelComponents(){
        viewModel = ViewModelProvider(this,
            TvShowsViewModelFactory(TvShowsRepository(retrofitServiceTvShows))
        )
            .get(TvShowsViewModel::class.java)
        viewModelSchedule = ViewModelProvider(this,
            MainViewModelScheduleFactory(MainRepositorySchedule(retrofitServiceSchedule))
        )
            .get(MainViewModelSchedule::class.java)
        viewModelNextEpisode = ViewModelProvider(this, ViewModelNextEpisodeFactory(
            NextEpisodeRepository(retrofitServiceNextEpisodes)
        )
        ).get(ViewModelNextEpisode::class.java)
    }
    private fun setUpSeriesList(){
        mainAdapter = MainAdapter{ tvshow ->
            openLink(tvshow)
        }
        adapterSchedule = AdapterSchedule{
            val intent = Intent(this,MovieInformationActivity::class.java)
            intent.putExtra("image",it.show.image.original)
            intent.putExtra("summary",it.show.summary)
            startActivity(intent)
        }
        adapterNxEpisode = AdapterNxEpisode{
            val intent = Intent(this,MovieInformationActivity::class.java)
            intent.putExtra("image",it.image.original)
            intent.putExtra("summary",it.summary)
            startActivity(intent)
        }

        binding.rvEmAuta.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
        }
        binding.rvSeries.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
        }
        // Schedule
        binding.rvSchedule.apply {
            adapter = adapterSchedule
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
        }
        viewModelSchedule.scheduleList.observe(this@MainActivity, Observer {
            adapterSchedule.setScheduleList(it)
        })
        viewModelSchedule.getAllSchedule()
        viewModel.tvShowList.observe(this@MainActivity, Observer {
            mainAdapter.setMovieList(it)
        })
        viewModel.getAllTvShows()

        //Next Episodes
        binding.rvNextEpisodes.apply {
           adapter = adapterNxEpisode
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
        }
        viewModelNextEpisode.nxEpisode.observe(this@MainActivity, Observer {
            adapterNxEpisode.setNxEpisodeList(it)
        })
        viewModelNextEpisode.getAllNextEpisodes()
    }

    private fun openLink(tvshow: TvShowsResponse) {
        val intent = Intent(this,MovieInformationActivity::class.java)
        intent.putExtra("image",tvshow.image.original)
        intent.putExtra("summary",tvshow.summary)
        startActivity(intent)
    }

}