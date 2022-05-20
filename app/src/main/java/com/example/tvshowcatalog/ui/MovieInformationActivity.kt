package com.example.tvshowcatalog.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.example.tvshowcatalog.databinding.ActivityMovieInformationBinding

class MovieInformationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startComponents()
    }

    private fun startComponents() {
        val data = intent.extras
        val image = data?.getString("image")
        val summary = data?.getString("summary")

        binding.imageViewInformation.load(image)
        binding.tvMovieInformation.text = summary.toString()

    }

}