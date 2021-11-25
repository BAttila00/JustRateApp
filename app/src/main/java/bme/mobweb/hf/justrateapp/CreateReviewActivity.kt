package bme.mobweb.hf.justrateapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import bme.mobweb.hf.justrateapp.data.Place
import bme.mobweb.hf.justrateapp.data.RateAppDatabase
import bme.mobweb.hf.justrateapp.data.Review
import bme.mobweb.hf.justrateapp.databinding.ActivityCreateReviewBinding
import bme.mobweb.hf.justrateapp.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class CreateReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateReviewBinding
    private lateinit var database: RateAppDatabase

    companion object {
        const val KEY_TITTLE = "KEY_TITTLE"
        const val KEY_ADDRESS = "KEY_ADDRESS"
        const val KEY_URL = "KEY_URL"
        const val KEY_RESTAURANT_ID = "KEY_RESTAURANT_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = RateAppDatabase.getDatabase(applicationContext)

        binding.btnSend.setOnClickListener { sendClick() }
    }

    private fun sendClick() {
        if (!validateForm()) {
            return
        }

        uploadPost()
    }

    private fun validateForm() = binding.etReviewText.text.isNotEmpty()

    private fun uploadPost() {
        val newReview = Review(restaurantID = intent.getLongExtra(KEY_RESTAURANT_ID, 1).toString(), review = binding.etReviewText.text.toString())
        thread {
            database.ReviewDao().insert(newReview)
            
            val newintent = Intent(this, PlaceDetail::class.java)
            newintent.putExtra(PlaceDetail.KEY_TITTLE, intent.getStringExtra(KEY_TITTLE))
            newintent.putExtra(PlaceDetail.KEY_ADDRESS, intent.getStringExtra(KEY_ADDRESS))
            newintent.putExtra(PlaceDetail.KEY_URL, intent.getStringExtra(KEY_URL))
            newintent.putExtra(PlaceDetail.KEY_RESTAURANT_ID, intent.getLongExtra(KEY_RESTAURANT_ID, 1))
            //startActivity(newintent)
            startActivity(newintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
        //placesAdapter.addPost(newPlace)
    }
}