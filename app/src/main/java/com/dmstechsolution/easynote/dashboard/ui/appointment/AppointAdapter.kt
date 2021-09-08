package com.dmstechsolution.easynote.dashboard.ui.appointment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.dmstechsolution.easynote.R
import com.dmstechsolution.easynote.database.Appointment
import com.dmstechsolution.easynote.database.EasyNoteDatabase
import com.dmstechsolution.easynote.database.Note
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AppointAdapter:RecyclerView.Adapter<AppointAdapter.ViewHolder>() {

    private var appoinlist= emptyList<Appointment>()

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val title: TextView =view.findViewById(R.id.item_titleap)
        val desc: TextView =view.findViewById(R.id.item_descap)
        val time: TextView =view.findViewById(R.id.item_timeap)
        val date: TextView =view.findViewById(R.id.item_dateap)
        val whom: TextView =view.findViewById(R.id.item_whoap)
        val delet: ImageView =view.findViewById(R.id.item_deletap)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.appoint_item,parent,false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment=appoinlist[position]
        holder.date.text=appointment.date.toString()
        holder.time.text=appointment.time.toString()
        holder.title.text=appointment.title.toString()
        holder.desc.text=appointment.description.toString()
        holder.whom.text=appointment.whom.toString()
        holder.delet.setOnClickListener {
          val dao= EasyNoteDatabase.getDatabase(it.context).noteDao()
            GlobalScope.launch {
                dao.deleteappointment(appointment.id)
            }
            val appoperferance=it.context.getSharedPreferences("MainActivity", Context.MODE_PRIVATE)
            val edit=appoperferance.edit()
            val value:Int=appoperferance.getInt("apponum",0)
            edit.putInt("apponum",value-1).apply()
        }

    }

    override fun getItemCount(): Int {
        return appoinlist.size
    }

    fun setData(appoint:List<Appointment>){
        this.appoinlist=appoint
        notifyDataSetChanged()
    }
}