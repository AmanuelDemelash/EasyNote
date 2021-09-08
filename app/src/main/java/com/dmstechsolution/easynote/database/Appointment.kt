package com.dmstechsolution.easynote.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointment_table")
data class Appointment(
        @PrimaryKey(autoGenerate = true)
        val id:Int,
        val title:String,
        val description:String,
        val whom:String,
        val time:String,
        val date:String
)
