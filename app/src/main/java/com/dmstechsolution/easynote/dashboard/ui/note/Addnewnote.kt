package com.dmstechsolution.easynote.dashboard.ui.note


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.dmstechsolution.easynote.R
import com.dmstechsolution.easynote.dashboard.ui.EasyNoteViewModel
import com.dmstechsolution.easynote.dashboard.ui.EasyNoteViewModelFactory
import com.dmstechsolution.easynote.database.Note
import com.dmstechsolution.easynote.database.EasyNoteDatabase
import com.dmstechsolution.easynote.database.EasyNoteRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.DelicateCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*

class Addnewnote : Fragment() {

    private lateinit var viewModel: EasyNoteViewModel
    private lateinit var description: TextView
    private lateinit var otherradio: RadioButton
    private lateinit var famradio: RadioButton
    private lateinit var personalradio: RadioButton
    private lateinit var workradio: RadioButton
    private lateinit var radiogroup:RadioGroup
    private var note_catagory:String?=null

    @DelicateCoroutinesApi
    @SuppressLint("SimpleDateFormat", "CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.addnewnote_fragment, container, false)
        val noteDao=EasyNoteDatabase.getDatabase(requireContext()).noteDao()
        val repository=EasyNoteRepository(noteDao)
        viewModel=ViewModelProviders.of(this, EasyNoteViewModelFactory(repository)).get(EasyNoteViewModel::class.java)
        radiogroup=root.findViewById(R.id.radio_group)
        description = root.findViewById(R.id.not_disc)
        otherradio = root.findViewById(R.id.other_radio)
        famradio = root.findViewById(R.id.fam_radio)
        personalradio = root.findViewById(R.id.perso_radio)
        workradio = root.findViewById(R.id.work_radio)
        radiogroup.setOnCheckedChangeListener { radioGroup, i ->
            when(radioGroup.checkedRadioButtonId){
                R.id.work_radio->note_catagory=workradio.text.toString()
                R.id.perso_radio->note_catagory=personalradio.text.toString()
                R.id.fam_radio->note_catagory=famradio.text.toString()
                R.id.other_radio->note_catagory=otherradio.text.toString()
            }
        }
        val note_text=arguments?.getString("note")
        val note_cata=arguments?.getString("catagory")
        val note_id= arguments?.getString("id")
        description.text=note_text.toString()


        val savefab: FloatingActionButton = root.findViewById(R.id.fab_save)
        savefab.setOnClickListener {
            val note_text=description.text.toString()
            val sdf= SimpleDateFormat("dd/M/yyyy")
            val currentDate: String =sdf.format(Date())
            if (TextUtils.isEmpty(description.text)) {
                description.error = "empty"
            }
            else if (note_catagory.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Choose category",Toast.LENGTH_SHORT).show()
            }
            else{
                var id:Int=1
                if (note_id==null){
                    id=0
                }
                val note=Note(id,note_text,note_catagory.toString(),currentDate.toString())
                viewModel.insertnote(note)
                val noteperferance=requireContext().getSharedPreferences("MainActivity", Context.MODE_PRIVATE)
                val edit=noteperferance.edit()
                val value:Int=noteperferance.getInt("notenum",0)
                edit.putInt("notenum",value+1).apply()
                Toast.makeText(requireContext(),"Saved",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addnewnote_to_nav_note)
            }
        }
        return root
    }
}