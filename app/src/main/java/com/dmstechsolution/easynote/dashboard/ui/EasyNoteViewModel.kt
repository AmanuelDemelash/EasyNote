package com.dmstechsolution.easynote.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dmstechsolution.easynote.database.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EasyNoteViewModel(private val easyNoteRepository: EasyNoteRepository) : ViewModel(){

    val allnote:LiveData<List<Note>> =easyNoteRepository.allnote
    fun insertnote(note: Note){
        viewModelScope.launch {
            easyNoteRepository.insetnote(note)
        }
    }
    fun deletallnote(){
        viewModelScope.launch {
            easyNoteRepository.deletall()
        }
    }
    //todo
    val alltodo:LiveData<List<Todo>> =easyNoteRepository.alltodo
    fun inserttodo(todo: Todo){
        viewModelScope.launch {
            easyNoteRepository.inserttodo(todo)
        }
    }
    fun deletealltodo(){
        viewModelScope.launch {
            easyNoteRepository.deletalltodo()
        }
    }

    //appointement
    val allappointment:LiveData<List<Appointment>> =easyNoteRepository.allappointment
    fun insertappointemnt(appointment: Appointment){
        viewModelScope.launch {
            easyNoteRepository.insetappointemt(appointment)
        }
    }
    fun deletallappointment(){
        viewModelScope.launch {
            easyNoteRepository.deleteallappointemnt()
        }
    }
    // schedule
    val allschedule:LiveData<List<Schedule>> =easyNoteRepository.allschedule
    fun insertschedule(schedule: Schedule){
        viewModelScope.launch {
            easyNoteRepository.insertschedule(schedule)
        }
    }
    fun deletallschedule(){
        viewModelScope.launch {
            easyNoteRepository.deletallschedule()
        }
    }

}
class EasyNoteViewModelFactory(private val easyNoteRepository: EasyNoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EasyNoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EasyNoteViewModel(easyNoteRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}