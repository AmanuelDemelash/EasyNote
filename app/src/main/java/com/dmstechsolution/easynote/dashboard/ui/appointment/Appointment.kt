package com.dmstechsolution.easynote.dashboard.ui.appointment

import android.app.AlertDialog
import android.content.Context
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
import com.google.android.material.textfield.TextInputEditText

class Appointment : Fragment() {
    private lateinit var viewModel: EasyNoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root= inflater.inflate(R.layout.appointment_fragment, container, false)
        val noteDao= EasyNoteDatabase.getDatabase(requireContext()).noteDao()
        val repository= EasyNoteRepository(noteDao)
        viewModel= ViewModelProviders.of(this, EasyNoteViewModelFactory(repository)).get(EasyNoteViewModel::class.java)
        val recy=root.findViewById<RecyclerView>(R.id.appoint_recy)
        val empty_text=root.findViewById<TextView>(R.id.app_empty)
        val addappoint=root.findViewById<FloatingActionButton>(R.id.fab_addappoint)
        addappoint.setOnClickListener {
            findNavController().navigate(R.id.action_appointment_to_addnewAppointment)
        }
        val adapter=AppointAdapter()
        recy.adapter=adapter
        recy.layoutManager=LinearLayoutManager(requireContext())
        viewModel.allappointment.observe(viewLifecycleOwner, Observer{
            adapter.setData(it)
            if (it.isNotEmpty()){
                empty_text.visibility=View.GONE
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
                    viewModel.deletallappointment()
                    findNavController().navigate(R.id.action_appointment_to_nav_home)
                    val appoperferance=requireContext().getSharedPreferences("MainActivity", Context.MODE_PRIVATE)
                    val edit=appoperferance.edit()
                    edit.putInt("apponum",0).apply()
                }
                alert.setNegativeButton("No"){ dialogInterface: DialogInterface, i: Int ->

                    alert.setCancelable(true)
                }
                alert.setTitle("Deleting")
                alert.setMessage("Do you want to remove all your Appointments?").show()

            }
        }

        return true
    }

}