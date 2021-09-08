package com.dmstechsolution.easynote.dashboard.ui.todo

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dmstechsolution.easynote.R
import com.dmstechsolution.easynote.database.EasyNoteDatabase
import com.dmstechsolution.easynote.database.EasyNoteRepository
import com.dmstechsolution.easynote.database.Todo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TodoAdapter :RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    private var todolist= emptyList<Todo>()
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val title: TextView=view.findViewById(R.id.todo_item_title)
        val desc: TextView=view.findViewById(R.id.todo_item_cata)
        val date: TextView=view.findViewById(R.id.todo_item_date)
        val delet: ImageView=view.findViewById(R.id.todo_item_delet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate= LayoutInflater.from(parent.context).inflate(R.layout.todo_item,parent,false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo=todolist[position]
        holder.title.text=todo.title.toString()
        holder.desc.text=todo.descri.toString()
        holder.date.text=todo.day.toString()
        if (todo.category=="High"){
            holder.title.setTextColor(Color.RED)
            holder.desc.setTextColor(Color.RED)
        }else if(todo.category=="Medium"){
            holder.title.setTextColor(Color.BLUE)
            holder.desc.setTextColor(Color.BLUE)
        }
        else if(todo.category=="Low"){
            holder.title.setTextColor(Color.GREEN)
            holder.desc.setTextColor(Color.GREEN)
        }
        holder.delet.setOnClickListener {
            val easyNoteDao=EasyNoteDatabase.getDatabase(it.context).noteDao()
            val easyNoteRepository=EasyNoteRepository(easyNoteDao)
            GlobalScope.launch {
                easyNoteRepository.delettodo(todo.id)
            }
            val todoperferance=it.context.getSharedPreferences("MainActivity", Context.MODE_PRIVATE)
            val edit=todoperferance.edit()
            val value:Int=todoperferance.getInt("todonum",0)
            edit.putInt("todonum",value-1).apply()
        }

    }

    override fun getItemCount(): Int {
       return todolist.size
    }
    fun setData(todo:List<Todo>){
        this.todolist=todo
        notifyDataSetChanged()
    }
}