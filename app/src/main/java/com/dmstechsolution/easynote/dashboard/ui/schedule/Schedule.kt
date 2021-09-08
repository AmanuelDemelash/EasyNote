package com.dmstechsolution.easynote.dashboard.ui.schedule


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmstechsolution.easynote.R
import com.dmstechsolution.easynote.dashboard.ui.EasyNoteViewModel
import com.dmstechsolution.easynote.dashboard.ui.EasyNoteViewModelFactory
import com.dmstechsolution.easynote.database.EasyNoteDatabase
import com.dmstechsolution.easynote.database.EasyNoteRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Schedule : Fragment() {
    private lateinit var viewModel:EasyNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root= inflater.inflate(R.layout.schedule_fragment, container, false)
        val easyNoteDao=EasyNoteDatabase.getDatabase(requireContext()).noteDao()
        val repository=EasyNoteRepository(easyNoteDao)
        viewModel=ViewModelProviders.of(this,EasyNoteViewModelFactory(repository)).get(EasyNoteViewModel::class.java)

        val recy=root.findViewById<RecyclerView>(R.id.schedule_recy)
        val empty=root.findViewById<TextView>(R.id.sch_empty)
        val fab_add_schedule=root.findViewById<FloatingActionButton>(R.id.fab_schedule)
        fab_add_schedule.setOnClickListener {
            findNavController().navigate(R.id.action_schedule_to_addnew_schedule2)
        }
        val adapter=ScheduleAdapter()
        recy.adapter=adapter
        recy.layoutManager=LinearLayoutManager(requireContext())
        viewModel.allschedule.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
            if (it.isNotEmpty()){
                empty.visibility=View.GONE
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
                    viewModel.deletallschedule()
                    findNavController().navigate(R.id.action_schedule_to_nav_home)
                }
                alert.setNegativeButton("No"){ dialogInterface: DialogInterface, i: Int ->

                    alert.setCancelable(true)
                }
                alert.setTitle("Deleting")
                alert.setMessage("Do you want to remove all your Schedules?").show()

            }
        }
        return true
    }

}