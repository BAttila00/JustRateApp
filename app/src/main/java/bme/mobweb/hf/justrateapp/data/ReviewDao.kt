package bme.mobweb.hf.justrateapp.data

import androidx.room.*

@Dao
interface ReviewDao {
    @Query("SELECT * FROM review")
    fun getAll(): List<Review>

    @Insert
    fun insert(reviewItem: Review): Long

    @Update
    fun update(reviewItem: Review)

    @Delete
    fun deleteItem(reviewItem: Review)

    @Query("DELETE FROM review")
    fun deleteAllFromReviewTable();
}