package com.dmstechsolution.easynote.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "schedule_table")
data class Schedule(
        @PrimaryKey(autoGenerate = true)
        val id:Int,
        val day:String,
        @Embedded
        val morningclass: morningclass,
        @Embedded
        val afternoonclass: afternoonclass
)
data class morningclass(
        val msub1:String,
        val mtime1:String,
        val msub2:String,
        val mtime2:String,
        val msub3:String,
        val mtime3:String,
        val msub4:String,
        val mtime4:String,
        val msub5:String,
        val mtime5:String,
        val msub6:String,
        val mtime6:String
)
data class afternoonclass(
        val asub1:String,
        val atime1:String,
        val asub2:String,
        val atime2:String,
        val asub3:String,
        val atime3:String,
        val asub4:String,
        val atime4:String,
        val asub5:String,
        val atime5:String,
        val asub6:String,
        val atime6:String,
)
