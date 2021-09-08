package com.dmstechsolution.easynote.onbording.register


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.dmstechsolution.easynote.R
import com.dmstechsolution.easynote.dashboard.Home

class Bio : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root= inflater.inflate(R.layout.bio_fragment, container, false)

        val next=root.findViewById<Button>(R.id.button)
        val name=root.findViewById<EditText>(R.id.username)
        val bio=root.findViewById<EditText>(R.id.bio)
        next.setOnClickListener {
            val user_name=name.text.toString()
            val user_bio=bio.text.toString()
            if (TextUtils.isEmpty(user_name) || TextUtils.isEmpty(user_bio)){
                Toast.makeText(requireContext(),"User Name or Bio is empty",Toast.LENGTH_SHORT).show()
            }
            else{
                 val perferance=requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
                         val edit=perferance.edit()
                         edit.putString("name",user_name.toString()).apply()
                         edit.putString("bio",user_bio.toString()).apply()
                         edit.putBoolean("saved",true).apply()
                startActivity(Intent(context,Home::class.java))
            }
        }

        return root
    }
}