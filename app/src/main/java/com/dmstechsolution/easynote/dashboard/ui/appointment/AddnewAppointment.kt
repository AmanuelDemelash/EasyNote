package com.dmstechsolution.easynote.dashboard.ui.appointment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import com.dmstechsolution.easynote.database.Appointment
import com.dmstechsolution.easynote.database.EasyNoteDatabase
import com.dmstechsolution.easynote.database.EasyNoteRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class AddnewAppointment : Fragment() {
    private lateinit var viewModel:EasyNoteViewModel
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var otherradio: RadioButton
    private lateinit var famradio: RadioButton
    private lateinit var bfgf: RadioButton
    private lateinit var workradio: RadioButton
    private lateinit var radiogroup: RadioGroup
    private lateinit var time:TextView
    private lateinit var date:TextView
    private var appoint_catagory:String?=null
    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_addnew_appointment, container, false)
        val easyNoteDao=EasyNoteDatabase.getDatabase(requireContext()).noteDao()
        val repository=EasyNoteRepository(easyNoteDao)
        viewModel=ViewModelProviders.of(this,EasyNoteViewModelFactory(repository)).get(EasyNoteViewModel::class.java)

        radiogroup=root.findViewById(R.id.app_radiogroup)
        title=root.findViewById(R.id.appo_title)
        description = root.findViewById(R.id.appo_desc)
        otherradio = root.findViewById(R.id.radioother)
        famradio = root.findViewById(R.id.radiofam)
        bfgf = root.findViewById(R.id.radiogfbf)
        workradio = root.findViewById(R.id.radiowork)
        time=root.findViewById(R.id.appo_time)
        date=root.findViewById(R.id.appo_date)
        val fab_addappoint=root.findViewById<FloatingActionButton>(R.id.save_appoint)
        radiogroup.setOnCheckedChangeListener { radioGroup, i ->
            when(radioGroup.checkedRadioButtonId){
                R.id.radiowork->appoint_catagory=workradio.text.toString()
                R.id.radiogfbf->appoint_catagory=bfgf.text.toString()
                R.id.radiofam->appoint_catagory=famradio.text.toString()
                R.id.radioother->appoint_catagory=otherradio.text.toString()
            }
        }
        time.setOnClickListener {
            val cal=Calendar.getInstance()
            val timepicker=TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY,hour)
                cal.set(Calendar.MINUTE,minute)
                time.text=SimpleDateFormat("HH:mm a").format(cal.time)
            }
            TimePickerDialog(requireContext(),timepicker,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true).show()
        }
        date.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val datpicker=DatePickerDialog(requireContext(),DatePickerDialog.OnDateSetListener { datePicker, year, monthofyear, dayofmonth ->
                date.text = ""+dayofmonth+"/" + month + "/" + year
            },year,month,day)
            datpicker.show()
        }
        fab_addappoint.setOnClickListener {
            val aptitle=title.text.toString()
            val apdesc=description.text.toString()
            val aptime=time.text.toString()
            val apdate=date.text.toString()
            if (TextUtils.isEmpty(aptitle)){
                title.error="Empty"
            }
            else if (TextUtils.isEmpty(apdesc)){
                description.error="Empty"
            }
            else if (appoint_catagory.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Select with whom ",Toast.LENGTH_SHORT).show()
            }
            else if (TextUtils.isEmpty(aptime)){
                Toast.makeText(requireContext(),"Set Time",Toast.LENGTH_SHORT).show()
            }
            else if (TextUtils.isEmpty(apdate)){
                Toast.makeText(requireContext(),"Set Date",Toast.LENGTH_SHORT).show()
            }
            else{
                val appointment=Appointment(0,aptitle.toString(),apdesc.toString(),appoint_catagory.toString(),aptime.toString(),apdate.toString())
                 viewModel.insertappointemnt(appointment)
                val appoperferance=requireContext().getSharedPreferences("MainActivity", Context.MODE_PRIVATE)
                val edit=appoperferance.edit()
                val value:Int=appoperferance.getInt("apponum",0)
                edit.putInt("apponum",value+1).apply()
                Toast.makeText(requireContext(),"Appointment Saved",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addnewAppointment_to_appointment)
            }
        }



        return root
    }

}