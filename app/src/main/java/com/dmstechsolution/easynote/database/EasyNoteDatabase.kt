package com.dmstechsolution.easynote.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class,Todo::class,Appointment::class,Schedule::class], version =10, exportSchema = false)
abstract class EasyNoteDatabase :RoomDatabase(){
    abstract fun noteDao(): EasyNoteDao

    companion object {
        @Volatile
        private var INSTANCE: EasyNoteDatabase? = null

        fun getDatabase(context: Context): EasyNoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EasyNoteDatabase::class.java,
                    "note_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}