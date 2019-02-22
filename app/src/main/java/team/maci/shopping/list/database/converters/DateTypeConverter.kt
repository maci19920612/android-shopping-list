package team.maci.shopping.list.database.converters

import androidx.room.TypeConverter
import java.util.*

class DateTypeConverter{
    @TypeConverter
    fun fromTimestamp(timestamp: Long) : Date = Date(timestamp)

    @TypeConverter
    fun toTimestamp(date: Date) : Long = date.time
}