package bme.mobweb.hf.justrateapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bme.mobweb.hf.justrateapp.R
import bme.mobweb.hf.justrateapp.data.Review

class ReviewsAdapter: RecyclerView.Adapter<ReviewsAdapter.ViewHolder>() {
    private val reviewList: MutableList<Review> = mutableListOf()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvReview: TextView = itemView.findViewById(R.id.tvReview)
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(viewGroup.context)
            .inflate(R.layout.review_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ReviewsAdapter.ViewHolder, position: Int) {

        val tmpReview = reviewList[position]

        viewHolder.tvReview.text = tmpReview.review
    }

    override fun getItemCount() = reviewList.size
}