package com.dmstechsolution.easynote.database

import androidx.lifecycle.LiveData

class EasyNoteRepository(private val easyNoteDao: EasyNoteDao) {

    //note
    val allnote:LiveData<List<Note>> =easyNoteDao.getallnote()
    suspend fun insetnote(note: Note){
        easyNoteDao.insert(note)
    }
    fun deletnote(id:Int){
        easyNoteDao.deletnote(id)
    }
    suspend fun deletall(){
        easyNoteDao.deleteAll()
    }
    //todo
    val alltodo:LiveData<List<Todo>> =easyNoteDao.getalltodo()
    suspend fun inserttodo(todo: Todo){
        easyNoteDao.inserttodo(todo)
    }
    fun delettodo(id:Int){
        easyNoteDao.delettodo(id)
    }
    suspend fun deletalltodo(){
        easyNoteDao.deletalltodo()
    }
    //appointment
    val allappointment:LiveData<List<Appointment>> =easyNoteDao.getallappointment()
    suspend fun insetappointemt(appointment: Appointment){
        easyNoteDao.insertappointment(appointment)
    }
    fun deleteappointment(id:Int){
        easyNoteDao.deleteappointment(id)
    }
    suspend fun deleteallappointemnt(){
        easyNoteDao.deletallappointment()
    }
    //schedule
    val allschedule:LiveData<List<Schedule>> =easyNoteDao.getallschedule()
    suspend fun insertschedule(schedule: Schedule){
        easyNoteDao.insertschedule(schedule)
    }
    fun deletschedule(id:Int){
        easyNoteDao.deletschedul(id)
    }
    suspend fun deletallschedule(){
        easyNoteDao.deletallschedul()
    }

}