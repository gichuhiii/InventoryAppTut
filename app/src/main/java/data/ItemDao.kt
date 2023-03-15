package data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    //onconflict tells room what to do when there is a conflict in this case it will ignore
    @Insert(onConflict = OnConflictStrategy.IGNORE)

    //make function a suspend function, so that it can be called from a coroutine
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    //SQL query to retrieve a particular item from the item table based on the id
    @Query("SELECT * from item WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    //Using Flow or LiveData as return type will ensure you get notified whenever the data in the database changes.

    //Query to return all columns from the item table, ordered in ascending order
    //use Flow which room will keep updated for you
    @Query("SELECT * from item ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>
}