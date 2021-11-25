package bme.mobweb.hf.justrateapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Place::class], version = 1)       //Todo: kiboviteni reviews osztallyal, ha kesz.
abstract class RateAppDatabase : RoomDatabase() {
    abstract fun PlaceDao(): PlaceDao

    companion object {
        fun getDatabase(applicationContext: Context): RateAppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                RateAppDatabase::class.java,
                "places_list"
            ).build();
        }
    }
}