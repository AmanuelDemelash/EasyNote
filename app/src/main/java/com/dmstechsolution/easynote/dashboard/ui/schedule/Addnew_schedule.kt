package com.dmstechsolution.easynote.dashboard.ui.schedule

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.dmstechsolution.easynote.R
import com.dmstechsolution.easynote.dashboard.ui.EasyNoteViewModel
import com.dmstechsolution.easynote.dashboard.ui.EasyNoteViewModelFactory
import com.dmstechsolution.easynote.database.*
import com.dmstechsolution.easynote.database.Schedule
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class Addnew_schedule : Fragment() {

    private lateinit var ViewModel:EasyNoteViewModel
    private lateinit var msub1:EditText
    private lateinit var msub2:EditText
   private lateinit var msub3:EditText
    private lateinit var msub4:EditText
    private lateinit var msub5:EditText
    private lateinit var msub6:EditText

    private lateinit var asub1:EditText
    private lateinit var asub2:EditText
    private lateinit var asub3:EditText
    private lateinit var asub4:EditText
    private lateinit var asub5:EditText
    private lateinit var asub6:EditText

    private lateinit var mtime1:TextView
    private lateinit var mtime2:TextView
    private lateinit var mtime3:TextView
    private lateinit var mtime4:TextView
    private lateinit var mtime5:TextView
    private lateinit var mtime6:TextView

    private lateinit var atime1:TextView
    private lateinit var atime2:TextView
    private lateinit var atime3:TextView
    private lateinit var atime4:TextView
    private lateinit var atime5:TextView
    private lateinit var atime6:TextView


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root= inflater.inflate(R.layout.addnew_schedule_fragment, container, false)
        val easyNoteDao=EasyNoteDatabase.getDatabase(requireContext()).noteDao()
        val repository=EasyNoteRepository(easyNoteDao)
        ViewModel=ViewModelProviders.of(requireActivity(),EasyNoteViewModelFactory(repository)).get(EasyNoteViewModel::class.java)
        val sppinner=root.findViewById<Spinner>(R.id.spinner)
        val list= arrayOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
        sppinner.adapter=ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,list)

         msub1=root.findViewById(R.id.sub1)
         msub2=root.findViewById(R.id.sub2)
         msub3=root.findViewById(R.id.sub3)
         msub4=root.findViewById(R.id.sub4)
         msub5=root.findViewById(R.id.sub5)
         msub6=root.findViewById(R.id.sub6)

        asub1=root.findViewById(R.id.suba1)
        asub2=root.findViewById(R.id.suba2)
        asub3=root.findViewById(R.id.suba3)
        asub4=root.findViewById(R.id.suba4)
        asub5=root.findViewById(R.id.suba5)
        asub6=root.findViewById(R.id.suba6)

        mtime1=root.findViewById(R.id.time1)
        mtime2=root.findViewById(R.id.time2)
        mtime3=root.findViewById(R.id.time3)
        mtime4=root.findViewById(R.id.time4)
        mtime5=root.findViewById(R.id.time5)
        mtime6=root.findViewById(R.id.time6)

        atime1=root.findViewById(R.id.timea1)
        atime2=root.findViewById(R.id.timea2)
        atime3=root.findViewById(R.id.timea3)
        atime4=root.findViewById(R.id.timea4)
        atime5=root.findViewById(R.id.timea5)
        atime6=root.findViewById(R.id.timea6)

        mtime1.setOnClickListener {
            gettime(mtime1)
        }
        mtime2.setOnClickListener {
            gettime(mtime2)
        }
        mtime3.setOnClickListener {
            gettime(mtime3)
        }
        mtime4.setOnClickListener {
            gettime(mtime4)
        }
        mtime5.setOnClickListener {
            gettime(mtime5)
        }
        mtime6.setOnClickListener {
            gettime(mtime6)
        }
        atime1.setOnClickListener {
            gettime(atime1)
        }
        atime2.setOnClickListener {
            gettime(atime2)
        }
        atime3.setOnClickListener {
            gettime(atime3)
        }
        atime4.setOnClickListener {
            gettime(atime4)
        }
        atime5.setOnClickListener {
            gettime(atime5)
        }
        atime6.setOnClickListener {
            gettime(atime6)
        }
        val save=root.findViewById<FloatingActionButton>(R.id.save_schedule)
        save.setOnClickListener {
            if (msub1.text.isEmpty()) {
                Toast.makeText(requireContext(), "select day and add class", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val morningclass = morningclass(
                    msub1.text.toString(),
                    mtime1.text.toString(),
                    msub2.text.toString(),
                    mtime2.text.toString(),
                    msub3.text.toString(),
                    mtime3.text.toString(),
                    msub4.text.toString(),
                    mtime4.text.toString(),
                    msub5.text.toString(),
                    mtime5.text.toString(),
                    msub6.text.toString(),
                    mtime6.text.toString()
                )
                val afternoonclass = afternoonclass(
                    asub1.text.toString(),
                    atime1.text.toString(),
                    asub2.text.toString(),
                    atime2.text.toString(),
                    asub3.text.toString(),
                    atime3.text.toString(),
                    asub4.text.toString(),
                    atime4.text.toString(),
                    asub5.text.toString(),
                    atime5.text.toString(),
                    asub6.text.toString(),
                    atime6.text.toString()
                )
                val schedule =
                    Schedule(0, sppinner.selectedItem.toString(), morningclass, afternoonclass)
                ViewModel.insertschedule(schedule)
                Toast.makeText(requireContext(), "Saved ", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addnew_schedule2_to_schedule)

            }
        }
        return root
    }

    @SuppressLint("SimpleDateFormat")
    fun gettime(viewname:TextView){
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            viewname.text = SimpleDateFormat("HH:mma").format(cal.time)
        }
        TimePickerDialog(requireContext(), timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()

    }

}