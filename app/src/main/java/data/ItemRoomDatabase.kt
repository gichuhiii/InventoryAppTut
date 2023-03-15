package data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//export Schema is false so as to not keep schema version history backups
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    //companion object allows access to the methods for creating or getting the database using the class name as the qualifier
    companion object {
        //The value of a volatile will never be cached, and all writes and reads will be done to and from the main memory.
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null
        fun getDatabase(context: Context): ItemRoomDatabase {

            //to prevent multiple instances of the database from being opened at the same time
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database"
                )
                    //A migration object is an object that defines how you take all rows with the old schema and convert them to rows in the new schema, so that no data is lost. Migration is beyond the scope of this codelab. A simple solution is to destroy and rebuild the database, which means that the data is lost
                    .fallbackToDestructiveMigration()
                    //to create database instance
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
    }

