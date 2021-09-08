package com.dmstechsolution.easynote.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
        @PrimaryKey(autoGenerate = true)
        val id:Int,
        val title:String,
        val descri:String,
        val day:String,
        val category:String
)
