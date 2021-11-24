package bme.mobweb.hf.justrateapp.data

import androidx.room.*

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place")
    fun getAll(): List<Place>

    @Insert
    fun insert(shoppingItems: Place): Long

    @Update
    fun update(shoppingItem: Place)

    @Delete
    fun deleteItem(shoppingItem: Place)
}