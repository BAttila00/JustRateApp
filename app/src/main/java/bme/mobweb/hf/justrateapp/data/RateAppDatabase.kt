package bme.mobweb.hf.justrateapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Place::class, Review::class], version = 2)       //Todo: kiboviteni reviews osztallyal, ha kesz.
abstract class RateAppDatabase : RoomDatabase() {       //1-esen hagytanm a version-t, és kaptam egy hibaüzenetet(runtime) h növeljem meg
                                                        //Miután megnöveltem meg ilyen hibaüzit kaptam: A migration from 1 to 2 was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(Migration ...)
                                                        //Megoldás: hozzá kellett adni azt a build elé (fallbackToDestructiveMigration). Ez törölni fogja a meglévő adatokat új migrációnál
    abstract fun PlaceDao(): PlaceDao
    abstract fun ReviewDao(): ReviewDao

    companion object {
        fun getDatabase(applicationContext: Context): RateAppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                RateAppDatabase::class.java,
                "places_list"
            ).fallbackToDestructiveMigration().build();
        }
    }
}