package bme.mobweb.hf.justrateapp.data

import androidx.room.*

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place")
    fun getAll(): List<Place>

    @Insert
    fun insert(placeItem: Place): Long      //TODO: megváltoztatni a shoppingItems nevet másra

    @Update
    fun update(placeItem: Place)

    @Delete
    fun deleteItem(placeItem: Place)

    @Query("DELETE FROM place")
    fun deleteAllFromPlaceTable();
}