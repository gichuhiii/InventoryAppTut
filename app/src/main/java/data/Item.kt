package data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")

data class Item (

    //set autogenerate to true so that room generates the ID for each entity
    @PrimaryKey(autoGenerate = true)
    //declare variables
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val itemName: String,

    @ColumnInfo(name = "price")
    val itemPrice: Double,

    @ColumnInfo(name = "quantity")
    val quantityInStock: Int

    )
