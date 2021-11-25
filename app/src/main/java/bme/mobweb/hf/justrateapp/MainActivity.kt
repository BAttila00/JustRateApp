package bme.mobweb.hf.justrateapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import bme.mobweb.hf.justrateapp.adapter.PlacesAdapter
import bme.mobweb.hf.justrateapp.data.Place
import bme.mobweb.hf.justrateapp.data.RateAppDatabase
import bme.mobweb.hf.justrateapp.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var placesAdapter: PlacesAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: RateAppDatabase
    private lateinit var list: List<Place>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //connecting the layout with RecyclerView adapter
        placesAdapter = PlacesAdapter()
        binding.rvPlaces.layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true
            stackFromEnd = true
        }
        binding.rvPlaces.adapter = placesAdapter

        binding.fab.setOnClickListener {
            val createPostIntent = Intent(this, CreatePlaceActivity::class.java)
            startActivity(createPostIntent)
        }

        database = RateAppDatabase.getDatabase(applicationContext)

    }

    override fun onStart() {
        super.onStart()
        initRV()
    }

    private fun initRV() {
        placesAdapter.clear()

        thread {
            //database.PlaceDao().deleteAllFromPlaceTable()
            list = database.PlaceDao().getAll()
            runOnUiThread {
                list.forEach {
                    placesAdapter.addPost(it)
                }
            }
        }


//        val t = Thread { list = database.PlaceDao().getAll() }
//
//        t.start() // spawn thread
//        t.join() // wait for thread to finish
//
//        list.forEach {
//            placesAdapter.addPost(it)
//        }
    }
}
