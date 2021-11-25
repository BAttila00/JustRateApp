package bme.mobweb.hf.justrateapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bme.mobweb.hf.justrateapp.adapter.PlacesAdapter
import bme.mobweb.hf.justrateapp.data.Place
import bme.mobweb.hf.justrateapp.data.RateAppDatabase
import bme.mobweb.hf.justrateapp.databinding.ActivityCreatePlaceBinding
import bme.mobweb.hf.justrateapp.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class CreatePlaceActivity : AppCompatActivity() {

    private lateinit var placesAdapter: PlacesAdapter
    private lateinit var binding: ActivityCreatePlaceBinding
    private lateinit var database: RateAppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreatePlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        placesAdapter = PlacesAdapter()
        database = RateAppDatabase.getDatabase(applicationContext)

        binding.btnSend.setOnClickListener {
            if (!validateForm()) {

            } else{
                uploadPost()
            }
        }

    }

//    private fun sendClick() {
//        if (!validateForm()) {
//            return
//        }
//
//        uploadPost()
//    }

    private fun validateForm() = !(binding.etTitle.text.isEmpty() && binding.etAddress.text.isEmpty())

    private fun uploadPost() {
        val newPlace = Place(title= binding.etTitle.text.toString(), address = binding.etAddress.text.toString(), pageUrl = binding.etPageUrl.text.toString())
        thread {
            database.PlaceDao().insert(newPlace)
        }
        //placesAdapter.addPost(newPlace)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode != Activity.RESULT_OK) {
//            return
//        }
//    }
}