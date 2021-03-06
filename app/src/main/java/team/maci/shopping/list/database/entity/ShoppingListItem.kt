package team.maci.shopping.list.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*


@Entity(tableName = "shopping_list")
data class ShoppingListItem(
    @PrimaryKey
    val id: Int? = null,
    @ColumnInfo var title: String = "",
    @ColumnInfo var active: Boolean = true,
    @ColumnInfo var inactivatedDate: Date? = null,
    @ColumnInfo var order: Int = 0,
    @ColumnInfo var delimiter: Boolean = false
) : Serializable, Comparable<ShoppingListItem> {
    override fun compareTo(other: ShoppingListItem): Int {
        if (order == 0 || other.order == 0) {
            if (other.active == active) {
                return 0
            }
            if (active) {
                return -1
            }

            return 1
        }

        return order - other.order
    }
}