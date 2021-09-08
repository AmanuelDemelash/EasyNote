package com.dmstechsolution.easynote.dashboard.ui.todo

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmstechsolution.easynote.R
import com.dmstechsolution.easynote.dashboard.ui.EasyNoteViewModel
import com.dmstechsolution.easynote.dashboard.ui.EasyNoteViewModelFactory
import com.dmstechsolution.easynote.database.EasyNoteDatabase
import com.dmstechsolution.easynote.database.EasyNoteRepository
import com.dmstechsolution.easynote.database.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TodoFragment : Fragment() {
  private lateinit var viewModel: EasyNoteViewModel
  private var todo_list:LiveData<List<Note>>?=null
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    val root = inflater.inflate(R.layout.fragment_todo, container, false)
    val noteDao= EasyNoteDatabase.getDatabase(requireContext()).noteDao()
    val repository= EasyNoteRepository(noteDao)
    viewModel= ViewModelProviders.of(this, EasyNoteViewModelFactory(repository)).get(EasyNoteViewModel::class.java)
    val fab_add=root.findViewById<FloatingActionButton>(R.id.fab_todo)
    val recyclerview=root.findViewById<RecyclerView>(R.id.todo_recycler)
    val mpty_txt=root.findViewById<TextView>(R.id.todo_empty)
    fab_add.setOnClickListener {
      findNavController().navigate(R.id.action_nav_todo_to_addnewTodo)
    }
    val todoadapter=TodoAdapter()
    recyclerview.adapter=todoadapter
    recyclerview.layoutManager=LinearLayoutManager(requireContext())
    viewModel.alltodo.observe(viewLifecycleOwner, {
      todoadapter.setData(it)
      if (it.isNotEmpty()){
        mpty_txt.visibility=View.GONE
      }
    })

    setHasOptionsMenu(true)
    return root
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    MenuInflater(requireContext()).inflate(R.menu.delet_menu,menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {

    when(item.itemId){
      R.id.deletall->{
        val alert= AlertDialog.Builder(requireContext())
        alert.setCancelable(false)
        alert.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
          viewModel.deletealltodo()
          findNavController().navigate(R.id.action_nav_todo_to_nav_home)
          val todoperferance=requireContext().getSharedPreferences("MainActivity", Context.MODE_PRIVATE)
          val edit=todoperferance.edit()
          edit.putInt("todonum",0).apply()
        }
        alert.setNegativeButton("No"){ dialogInterface: DialogInterface, i: Int ->
          alert.setCancelable(true)
        }
        alert.setTitle("Deleting")
        alert.setMessage("Do you want to remove all your todos?").show()

      }
      }
    return true
  }
}