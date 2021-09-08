package com.dmstechsolution.easynote.dashboard.ui.about

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dmstechsolution.easynote.R

class About : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root= inflater.inflate(R.layout.about_fragment, container, false)
        val perferance=requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        val name=perferance.getString("name","")
        val bio=perferance.getString("bio","")

        val pro_name=root.findViewById<TextView>(R.id.profile_name)
        val p_bio=root.findViewById<TextView>(R.id.profile_bio)
        pro_name.text = name.toString()
        p_bio.text = bio.toString()



        return root
    }


}