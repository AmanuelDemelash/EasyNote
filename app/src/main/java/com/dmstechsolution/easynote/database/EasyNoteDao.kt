package com.dmstechsolution.easynote.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dmstechsolution.easynote.database.Note

@Dao
interface EasyNoteDao {
    //note
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)
    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getallnote(): LiveData<List<Note>>
    @Query("DELETE FROM note_table")
    suspend fun deleteAll()
    @Query("DELETE FROM note_table WHERE id==:id")
    fun deletnote(id:Int)

    // todo
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserttodo(todo: Todo)
    @Query("SELECT * FROM todo_table ORDER BY id DESC")
    fun getalltodo():LiveData<List<Todo>>
    @Query("DELETE FROM todo_table")
    suspend fun deletalltodo()
    @Query("DELETE FROM todo_table WHERE id==:id")
    fun delettodo(id:Int)
    //appointment
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertappointment(appointment: Appointment)
    @Query("SELECT * FROM appointment_table ORDER BY id DESC")
    fun getallappointment():LiveData<List<Appointment>>
    @Query("DELETE FROM appointment_table")
    suspend fun deletallappointment()
    @Query("DELETE FROM appointment_table WHERE id==:id")
    fun deleteappointment(id:Int)


    // schedule
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertschedule(schedule: Schedule)
    @Query("SELECT * FROM schedule_table ORDER BY ID ASC")
    fun getallschedule():LiveData<List<Schedule>>
    @Query("DELETE FROM schedule_table")
    suspend fun deletallschedul()
    @Query("DELETE FROM schedule_table WHERE id==:id")
    fun deletschedul(id:Int)

}