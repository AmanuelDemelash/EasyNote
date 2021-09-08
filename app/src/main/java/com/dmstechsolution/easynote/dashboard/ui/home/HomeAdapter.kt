package com.dmstechsolution.easynote.dashboard.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dmstechsolution.easynote.R
import pl.droidsonroids.gif.GifImageView
import kotlin.collections.List

class HomeAdapter(private val list:List<com.dmstechsolution.easynote.dashboard.ui.home.List>):RecyclerView.Adapter<HomeAdapter.ViewHolder>(){

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val image: GifImageView=view.findViewById(R.id.image_item)
        val title: TextView=view.findViewById(R.id.item_title)
        val item_root: ConstraintLayout=view.findViewById(R.id.item_root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=list[position]
        holder.image.setImageResource(item.gif)
        holder.title.text=item.title.toString()
        holder.item_root.setOnClickListener {
            when (item.title) {
                "Appointment" -> {
                    it.findNavController().navigate(R.id.action_nav_home_to_appointment)
                }
                "Todo" -> {
                    it.findNavController().navigate(R.id.action_nav_home_to_nav_todo)
                }
                "Class Schedule" -> {
                    it.findNavController().navigate(R.id.action_nav_home_to_schedule)
                }
                "Note" -> {
                    it.findNavController().navigate(R.id.action_nav_home_to_nav_note)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater=LayoutInflater.from(parent.context).inflate(R.layout.dashbord_item,parent,false)
        return ViewHolder(inflater)
    }

}