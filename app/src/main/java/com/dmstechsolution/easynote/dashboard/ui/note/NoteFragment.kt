package com.dmstechsolution.easynote.dashboard.ui.note

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmstechsolution.easynote.R
import com.dmstechsolution.easynote.dashboard.ui.EasyNoteViewModel
import com.dmstechsolution.easynote.dashboard.ui.EasyNoteViewModelFactory
import com.dmstechsolution.easynote.database.Note
import com.dmstechsolution.easynote.database.EasyNoteDatabase
import com.dmstechsolution.easynote.database.EasyNoteRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton


class NoteFragment : Fragment() {

  private lateinit var viewModel: EasyNoteViewModel
  private var note_list:LiveData<List<Note>>?=null
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val noteDao= EasyNoteDatabase.getDatabase(requireContext()).noteDao()
    val repository= EasyNoteRepository(noteDao)
    viewModel= ViewModelProviders.of(this, EasyNoteViewModelFactory(repository)).get(EasyNoteViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_note, container, false)
    val recyclerview=root.findViewById<RecyclerView>(R.id.note_recy)
    val empty_text=root.findViewById<TextView>(R.id.note_noting)
    note_list=viewModel.allnote
    val addnote_fab=root.findViewById<FloatingActionButton>(R.id.fab_add)
    addnote_fab.setOnClickListener {
        findNavController().navigate(R.id.action_nav_note_to_addnewnote)

    }

      val noteAdapter=NoteAdapter()
      recyclerview.adapter=noteAdapter
      recyclerview.layoutManager=LinearLayoutManager(requireContext())
      viewModel.allnote.observe(viewLifecycleOwner, Observer {
        noteAdapter.setData(it)
        if (it.isEmpty()){
          empty_text.visibility=View.VISIBLE
        }
        else{
          empty_text.visibility=View.GONE
        }
      })
setHasOptionsMenu(true)

    return root
  }
  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    return MenuInflater(requireContext()).inflate(R.menu.delet_menu,menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
   when(item.itemId){
     R.id.deletall->{
       val alert=AlertDialog.Builder(requireContext())
       alert.setCancelable(false)
       alert.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
         viewModel.deletallnote()
         findNavController().navigate(R.id.action_nav_note_to_nav_home)
         val noteperferance=requireContext().getSharedPreferences("MainActivity", Context.MODE_PRIVATE)
         val edit=noteperferance.edit()
         edit.putInt("notenum",0).apply()
       }
       alert.setNegativeButton("No"){ dialogInterface: DialogInterface, i: Int ->

         alert.setCancelable(true)
       }
       alert.setTitle("Deleting")
       alert.setMessage("Do you want to remove all your note?").show()

     }
   }
    return true
  }
}