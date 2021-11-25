package bme.mobweb.hf.justrateapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "review")
data class Review(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "restaurantID") var restaurantID: String? = null,
    @ColumnInfo(name = "review") var review: String? = null,
)