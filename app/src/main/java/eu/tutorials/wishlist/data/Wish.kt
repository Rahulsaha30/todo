package eu.tutorials.wishlist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long=0L,
    @ColumnInfo(name = "wish-title")
    val title:String="",
    @ColumnInfo(name = "wish-desc")
    val description:String="")

object DummyWish{
    val wishList= listOf(
        Wish(title = "google",
            description = "watch"),
        Wish(title = "apple",
            description = "apple watch"),
        Wish(title = "samsung",
            description = "samsung watch"),
        Wish(title = "xiomi",
            description = "xiomi watch"),
    )
}
