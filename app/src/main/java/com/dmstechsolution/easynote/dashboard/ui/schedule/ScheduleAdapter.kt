package com.dmstechsolution.easynote.dashboard.ui.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dmstechsolution.easynote.R
import com.dmstechsolution.easynote.database.EasyNoteDatabase
import com.dmstechsolution.easynote.database.Schedule
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ScheduleAdapter:RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    private var listschedule= emptyList<Schedule>()
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val day: TextView=view.findViewById(R.id.item_sche_day)
        val msub1: TextView=view.findViewById(R.id.item_mor_sub1)
        val msub2: TextView=view.findViewById(R.id.item_mor_sub2)
        val msub3: TextView=view.findViewById(R.id.item_mor_sub3)
        val msub4: TextView=view.findViewById(R.id.item_mor_sub4)
        val msub5: TextView=view.findViewById(R.id.item_mor_sub5)
        val msub6: TextView=view.findViewById(R.id.item_mor_sub6)

        val mtime1: TextView=view.findViewById(R.id.item_mor_time1)
        val mtime2: TextView=view.findViewById(R.id.item_mor_time2)
        val mtime3: TextView=view.findViewById(R.id.item_mor_time3)
        val mtime4: TextView=view.findViewById(R.id.item_mor_time4)
        val mtime5: TextView=view.findViewById(R.id.item_mor_time5)
        val mtime6: TextView=view.findViewById(R.id.item_mor_time6)

        val asub1: TextView=view.findViewById(R.id.item_after_sub1)
        val asub2: TextView=view.findViewById(R.id.item_after_sub2)
        val asub3: TextView=view.findViewById(R.id.item_after_sub3)
        val asub4: TextView=view.findViewById(R.id.item_after_sub4)
        val asub5: TextView=view.findViewById(R.id.item_after_sub5)
        val asub6: TextView=view.findViewById(R.id.item_after_sub6)

        val atime1: TextView=view.findViewById(R.id.item_after_time1)
        val atime2: TextView=view.findViewById(R.id.item_after_time2)
        val atime3: TextView=view.findViewById(R.id.item_after_time3)
        val atime4: TextView=view.findViewById(R.id.item_after_time4)
        val atime5: TextView=view.findViewById(R.id.item_after_time5)
        val atime6: TextView=view.findViewById(R.id.item_after_time6)

        val delet: ImageView=view.findViewById(R.id.item_schedul_delet)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.schedule_item,parent,false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val schedule=listschedule[position]
        holder.day.text=schedule.day.toString()
        holder.msub1.text=schedule.morningclass.msub1
        holder.msub2.text=schedule.morningclass.msub2
        holder.msub3.text=schedule.morningclass.msub3
        holder.msub4.text=schedule.morningclass.msub4
        holder.msub5.text=schedule.morningclass.msub5
        holder.msub6.text=schedule.morningclass.msub6

        holder.mtime1.text=schedule.morningclass.mtime1
        holder.mtime2.text=schedule.morningclass.mtime2
        holder.mtime3.text=schedule.morningclass.mtime3
        holder.mtime4.text=schedule.morningclass.mtime4
        holder.mtime5.text=schedule.morningclass.mtime5
        holder.mtime6.text=schedule.morningclass.mtime6

        holder.asub1.text=schedule.afternoonclass.asub1
        holder.asub2.text=schedule.afternoonclass.asub2
        holder.asub3.text=schedule.afternoonclass.asub3
        holder.asub4.text=schedule.afternoonclass.asub4
        holder.asub5.text=schedule.afternoonclass.asub5
        holder.asub6.text=schedule.afternoonclass.asub6

        holder.atime1.text=schedule.afternoonclass.atime1
        holder.atime2.text=schedule.afternoonclass.atime2
        holder.atime3.text=schedule.afternoonclass.atime3
        holder.atime4.text=schedule.afternoonclass.atime4
        holder.atime5.text=schedule.afternoonclass.atime5
        holder.atime6.text=schedule.afternoonclass.atime6

        holder.delet.setOnClickListener {
            val easyNoteDao=EasyNoteDatabase.getDatabase(it.context).noteDao()
            GlobalScope.launch {
                easyNoteDao.deletschedul(schedule.id)
            }
        }
    }
    override fun getItemCount(): Int {
        return listschedule.size
    }
    fun setData(schedul:List<Schedule>){
        this.listschedule=schedul
        notifyDataSetChanged()
    }
}