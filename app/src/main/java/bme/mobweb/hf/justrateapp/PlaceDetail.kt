package bme.mobweb.hf.justrateapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bme.mobweb.hf.justrateapp.databinding.ActivityMainBinding
import bme.mobweb.hf.justrateapp.databinding.ActivityPlaceDetailBinding

class PlaceDetail : AppCompatActivity() {

    private lateinit var binding: ActivityPlaceDetailBinding

    companion object {
        const val KEY_TITTLE = "KEY_TITTLE"
        const val KEY_ADDRESS = "KEY_ADDRESS"
        const val KEY_URL = "KEY_URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvPlaceTitle.text = intent.getStringExtra(KEY_TITTLE)
        binding.tvPlaceAddress.text = intent.getStringExtra(KEY_ADDRESS)
        binding.tvPlaceURL.text = intent.getStringExtra(KEY_URL)


    }
}