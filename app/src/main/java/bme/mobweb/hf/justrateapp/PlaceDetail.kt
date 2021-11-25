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
import bme.mobweb.hf.justrateapp.databinding.ActivityMainBinding
import bme.mobweb.hf.justrateapp.databinding.ActivityPlaceDetailBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

        val fab: FloatingActionButton = binding.fab

        //add restaurant posts button listener
        fab.setOnClickListener {
            Toast.makeText(applicationContext,"fab pushed",Toast.LENGTH_SHORT).show()
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
}