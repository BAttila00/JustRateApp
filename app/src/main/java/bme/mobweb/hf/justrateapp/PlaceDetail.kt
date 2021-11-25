package bme.mobweb.hf.justrateapp

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.URLUtil
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import bme.mobweb.hf.justrateapp.adapter.PlacesAdapter
import bme.mobweb.hf.justrateapp.adapter.ReviewsAdapter
import bme.mobweb.hf.justrateapp.data.Place
import bme.mobweb.hf.justrateapp.data.RateAppDatabase
import bme.mobweb.hf.justrateapp.data.Review
import bme.mobweb.hf.justrateapp.databinding.ActivityMainBinding
import bme.mobweb.hf.justrateapp.databinding.ActivityPlaceDetailBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.concurrent.thread

class PlaceDetail : AppCompatActivity() {

    private lateinit var binding: ActivityPlaceDetailBinding
    private lateinit var reviewsAdapter: ReviewsAdapter
    private lateinit var database: RateAppDatabase
    private lateinit var list: List<Review>

    companion object {
        const val KEY_TITTLE = "KEY_TITTLE"
        const val KEY_ADDRESS = "KEY_ADDRESS"
        const val KEY_URL = "KEY_URL"
        const val KEY_RESTAURANT_ID = "KEY_RESTAURANT_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reviewsAdapter = ReviewsAdapter()
        binding.rvReviews.layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true
            stackFromEnd = true
        }

        binding.rvReviews.adapter = reviewsAdapter

        binding.tvPlaceTitle.text = intent.getStringExtra(KEY_TITTLE)
        binding.tvPlaceAddress.text = intent.getStringExtra(KEY_ADDRESS)
        binding.tvPlaceURL.text = intent.getStringExtra(KEY_URL)

        val fab: FloatingActionButton = binding.fab

        database = RateAppDatabase.getDatabase(applicationContext)

        //add restaurant posts button listener
        fab.setOnClickListener {
            //Toast.makeText(applicationContext,"fab pushed",Toast.LENGTH_SHORT).show()

            val newIntent = Intent(this, CreateReviewActivity::class.java)
            newIntent.putExtra(CreateReviewActivity.KEY_TITTLE, intent.getStringExtra(KEY_TITTLE))
            newIntent.putExtra(CreateReviewActivity.KEY_ADDRESS, intent.getStringExtra(KEY_ADDRESS))
            newIntent.putExtra(CreateReviewActivity.KEY_URL, intent.getStringExtra(KEY_URL))
            newIntent.putExtra(CreateReviewActivity.KEY_RESTAURANT_ID, intent.getLongExtra(KEY_RESTAURANT_ID, 1))
            startActivity(newIntent)
        }
    }

    fun onClickTitle(view: View) {
        val textView: TextView = view as TextView
        val str: String = textView.text.toString()
        //Toast.makeText(applicationContext,str,Toast.LENGTH_SHORT).show()
        val intent = Intent(Intent.ACTION_WEB_SEARCH)
        intent.putExtra(SearchManager.QUERY, str)
        startActivity(intent)
    }

    fun onClickAddress(view: View) {
        //val intentUri = Uri.parse("geo:0,0?q=Budapest, Magyar Tudósok Körútja 2, 1117")
        val textView: TextView = view as TextView
        val str: String = textView.text.toString()
        val intentUri = Uri.parse("geo:0,0?q=${str}")
        val mapIntent = Intent(Intent.ACTION_VIEW, intentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    fun onClickUrl(view: View) {
        //val intentUri = Uri.parse("http://www.stackoverflow.com")
        val textView: TextView = view as TextView
        val str: String = textView.text.toString()
        if(!URLUtil.isValidUrl(str)){
            Toast.makeText(applicationContext,"Not valid URL! Can't open in browser. Make sure it starts with HTTP or HTTPS",Toast.LENGTH_SHORT).show()
            return
        }
        val intentUri = Uri.parse(str)
        val urlIntent = Intent(Intent.ACTION_VIEW, intentUri)
        startActivity(urlIntent)
    }

    override fun onStart() {
        super.onStart()
        initRV()
    }


    private fun initRV() {
        reviewsAdapter.clear()

        thread {
            //database.ReviewDao().deleteAllFromReviewTable()
            list = database.ReviewDao().getAll()
            runOnUiThread {
                list.forEach {
                    if(it.restaurantID == intent.getLongExtra(KEY_RESTAURANT_ID, 1).toString())
                        reviewsAdapter.addReview(it)
                }
            }
        }
    }
}