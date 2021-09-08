package com.dmstechsolution.easynote.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val description:String,
    val category:String,
    val date: String
)
