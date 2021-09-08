package com.dmstechsolution.easynote.dashboard.ui.home


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmstechsolution.easynote.R
import com.dmstechsolution.easynote.dashboard.Home
import com.dmstechsolution.easynote.dashboard.ui.EasyNoteViewModel
import com.dmstechsolution.easynote.dashboard.ui.EasyNoteViewModelFactory
import com.dmstechsolution.easynote.database.EasyNoteDatabase
import com.dmstechsolution.easynote.database.EasyNoteRepository


class HomeFragment : Fragment() {
  private lateinit var viewModel: EasyNoteViewModel

  @SuppressLint("SetTextI18n")
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    val root = inflater.inflate(R.layout.fragment_home, container, false)
    val dao=EasyNoteDatabase.getDatabase(requireContext()).noteDao()
    val repository=EasyNoteRepository(dao)
    viewModel=ViewModelProviders.of(this,EasyNoteViewModelFactory(repository)).get(EasyNoteViewModel::class.java)

    val total_notenumber=root.findViewById<TextView>(R.id.note_counter)
    val total_todonumber=root.findViewById<TextView>(R.id.todo_counter)
    val total_appointmentnum=root.findViewById<TextView>(R.id.appoint_count)

    val noteperferance=requireContext().getSharedPreferences("MainActivity", Context.MODE_PRIVATE)
    total_notenumber.text=noteperferance.getInt("notenum",0).toString()
    val appoperferance=requireContext().getSharedPreferences("MainActivity", Context.MODE_PRIVATE)
    total_appointmentnum.text=appoperferance.getInt("apponum",0).toString()
    val todoperferance=requireContext().getSharedPreferences("MainActivity", Context.MODE_PRIVATE)
    total_todonumber.text=todoperferance.getInt("todonum",0).toString()

    val username=root.findViewById<TextView>(R.id.username_dash)
    val perferance=requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
    val value=perferance.getString("name","")
    username.text = "Hi "+value.toString()

    val list_item= listOf(
      List(R.drawable.appointment,"Appointment"),List(R.drawable.todo,"Todo"),
    List(R.drawable.classschedule,"Class Schedule"),
    List(R.drawable.note,"Note"))
    val adapter=HomeAdapter(list_item)
    val rec=root.findViewById<RecyclerView>(R.id.rec_view)
    rec.layoutManager= GridLayoutManager(requireContext(),2)
    rec.adapter=adapter

    setHasOptionsMenu(true)
    return root
  }
  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    MenuInflater(requireContext()).inflate(R.menu.menu_homefra,menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when(item.itemId){
      R.id.aboutus->{
       findNavController().navigate(R.id.action_nav_home_to_aboutUs)
      }
    }
    return super.onOptionsItemSelected(item)
  }
}