package bme.mobweb.hf.justrateapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bme.mobweb.hf.justrateapp.R
import bme.mobweb.hf.justrateapp.data.Place

class PlacesAdapter() : RecyclerView.Adapter<PlacesAdapter.ViewHolder>(){
    private val postList: MutableList<Place> = mutableListOf()
    var itemClickListener: PlaceItemClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
        val tvPageUrl: TextView = itemView.findViewById(R.id.tvPageUrl)

        //dont forget to initalize it in onBindViewHolder with viewHolder.restaurant = tmpPost
        var place: Place? = null
        //var restaurant: Restaurant = Restaurant()

        init {
            itemView.setOnClickListener {
                if (place != null) Log.d("TAG", "gggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg")
                else Log.d("TAG", "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo")
                place?.let { place -> itemClickListener?.onItemClick(place) }
                //itemClickListener?.onItemClick(restaurant)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(viewGroup.context)
            .inflate(R.layout.place_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val tmpPost = postList[position]
        viewHolder.place = tmpPost
        viewHolder.tvTitle.text = tmpPost.title
        viewHolder.tvAddress.text = tmpPost.address
        viewHolder.tvPageUrl.text = tmpPost.pageUrl

    }

    override fun getItemCount() = postList.size

    fun addPost(post: Place?) {
        post ?: return

        postList.add(post)
        notifyDataSetChanged()
    }

    fun clear(){
        postList.clear()
    }

    interface PlaceItemClickListener {
        fun onItemClick(place: Place)
    }
}