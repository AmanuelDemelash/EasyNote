package com.dmstechsolution.easynote.dashboard.ui.note


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dmstechsolution.easynote.R
import com.dmstechsolution.easynote.database.Note
import com.dmstechsolution.easynote.database.EasyNoteDatabase
import com.dmstechsolution.easynote.database.EasyNoteRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteAdapter :RecyclerView.Adapter<NoteAdapter.ViewHolder>(){

    private var notelist= emptyList<Note>()
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val note_text: TextView=view.findViewById(R.id.note_item_desc)
        val note_cata: TextView=view.findViewById(R.id.note_item_catagory)
        val note_date: TextView=view.findViewById(R.id.note_item_date)
        val delet: ImageView= view.findViewById(R.id.note_item_delet)
        val notecard: CardView=view.findViewById(R.id.note_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val inflate=LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return ViewHolder(inflate)
    }
    @DelicateCoroutinesApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notelist[position]
        holder.note_text.text = note.description.toString()
        holder.note_cata.text = note.category.toString()
        holder.note_date.text = note.date.toString()
        holder.delet.setOnClickListener {
            val noteDao = EasyNoteDatabase.getDatabase(it.context).noteDao()
            val repository = EasyNoteRepository(noteDao)
            GlobalScope.launch {
                repository.deletnote(note.id)
            }
            val noteperferance = it.context.getSharedPreferences("MainActivity", Context.MODE_PRIVATE)
            val edit = noteperferance.edit()
            val value:Int=noteperferance.getInt("notenum", 0)
            edit.putInt("notenum", value-1).apply()
        }
        holder.notecard.setOnClickListener {
            val noteDao = EasyNoteDatabase.getDatabase(it.context).noteDao()
            val repository = EasyNoteRepository(noteDao)
            val bundle=bundleOf("note" to note.description,"catagory" to note.category,"id" to note.id)
            it.findNavController().navigate(R.id.action_nav_note_to_addnewnote, bundle)
            GlobalScope.launch {
                repository.deletnote(note.id)
            }
            val noteperferance=it.context.getSharedPreferences("MainActivity", Context.MODE_PRIVATE)
            val edit=noteperferance.edit()
            val value:Int=noteperferance.getInt("notenum",0)
            edit.putInt("notenum",value-1).apply()
        }
    }
    override fun getItemCount(): Int {
        return notelist.size
    }

    fun setData(note:List<Note>){
        this.notelist=note
        notifyDataSetChanged()
    }

}