package com.example.muslimbeibytuly.tracker.models

import android.arch.persistence.room.*
import java.util.Date
import java.util.Calendar

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

@Entity(tableName = "transaction_table")
@TypeConverters(Converters::class)
data class NewTransaction(
        @ColumnInfo(name = "content")
        var content: String,
        @ColumnInfo(name = "date")
        var date: Date = Calendar.getInstance().time,
        @ColumnInfo(name = "price")
        var price: Long = 0
    ) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

