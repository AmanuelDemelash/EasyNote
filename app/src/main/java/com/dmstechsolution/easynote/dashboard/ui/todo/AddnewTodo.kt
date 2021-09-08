package com.dmstechsolution.easynote.dashboard.ui.todo


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.dmstechsolution.easynote.R
import com.dmstechsolution.easynote.dashboard.ui.EasyNoteViewModel
import com.dmstechsolution.easynote.dashboard.ui.EasyNoteViewModelFactory
import com.dmstechsolution.easynote.database.EasyNoteDatabase
import com.dmstechsolution.easynote.database.EasyNoteRepository
import com.dmstechsolution.easynote.database.Todo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class AddnewTodo : Fragment() {

    private lateinit var viewModel: EasyNoteViewModel
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var highradio: RadioButton
    private lateinit var midumradio: RadioButton
    private lateinit var lowradio: RadioButton
    private lateinit var radiogroup: RadioGroup
    private var category: String?=null

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root= inflater.inflate(R.layout.addnew_todo_fragment, container, false)
        val easyNoteDao=EasyNoteDatabase.getDatabase(requireContext()).noteDao()
        val repository=EasyNoteRepository(easyNoteDao)
        viewModel=ViewModelProviders.of(this,EasyNoteViewModelFactory(repository)).get(EasyNoteViewModel::class.java)

        radiogroup=root.findViewById(R.id.radio_group)
        title=root.findViewById(R.id.todo_title)
        description = root.findViewById(R.id.todo_desc)
        highradio = root.findViewById(R.id.radio_highe)
        midumradio = root.findViewById(R.id.radio_medium)
        lowradio = root.findViewById(R.id.radio_low)

        radiogroup.setOnCheckedChangeListener { radioGroup, i ->
            when(radioGroup.checkedRadioButtonId){
                R.id.radio_highe->category=highradio.text.toString()
                R.id.radio_medium->category=midumradio.text.toString()
                R.id.radio_low->category=lowradio.text.toString()
            }
        }
        val fab_save=root.findViewById<FloatingActionButton>(R.id.fab_addtodo)
        fab_save.setOnClickListener {
            val todo_title=title.text.toString()
            val todo_desc=description.text.toString()
            val sdf= SimpleDateFormat("dd/M/yyyy")
            val currentDate: String =sdf.format(Date())

            if (TextUtils.isEmpty(todo_title)){
            title.error="empty"
        }
            else  if (TextUtils.isEmpty(todo_desc)) {
                description.error = "empty"
            }
            else if (category.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Choose category", Toast.LENGTH_SHORT).show()
            }
            else{
                val todo=Todo(0,todo_title,todo_desc,currentDate.toString(),category.toString())
                viewModel.inserttodo(todo)
                val todoperferance=requireContext().getSharedPreferences("MainActivity", Context.MODE_PRIVATE)
                val edit=todoperferance.edit()
                val value:Int=todoperferance.getInt("todonum",0)
                edit.putInt("todonum",value+1).apply()
                Toast.makeText(requireContext(),"Saved",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addnewTodo_to_nav_todo)
            }
        }

        return root
    }



}