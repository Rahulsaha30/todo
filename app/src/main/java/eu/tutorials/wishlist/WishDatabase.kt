package eu.tutorials.wishlist

import androidx.room.Database
import androidx.room.RoomDatabase
import eu.tutorials.wishlist.data.Wish

@Database(
    entities = [Wish::class],
    version = 1,
    exportSchema = false
)
abstract class WishDatabase:RoomDatabase() {
    abstract fun WishDao():WishDao
}