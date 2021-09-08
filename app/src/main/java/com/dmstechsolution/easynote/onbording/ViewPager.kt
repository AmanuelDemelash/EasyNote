package com.dmstechsolution.easynote.onbording

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.get
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.dmstechsolution.easynote.R
import com.dmstechsolution.easynote.dashboard.Home
import com.dmstechsolution.easynote.onbording.screen.Screen1
import com.dmstechsolution.easynote.onbording.screen.Screen2
import com.dmstechsolution.easynote.onbording.screen.Screen3
import com.dmstechsolution.easynote.onbording.screen.Screen4


class ViewPager : Fragment() {

    private lateinit var viewpager:ViewPager2
    private lateinit var next:ImageView
    private lateinit var back:ImageView
    private lateinit var next_but:Button
    private lateinit var getstart:Button
     var item:Int=0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_view_pager, container, false)
        viewpager=root.findViewById(R.id.viewpager_add)
        next_but=root.findViewById(R.id.button6)
        getstart=root.findViewById(R.id.button7)
        val fragmentlist= arrayListOf(
            Screen1(),
            Screen2(),
            Screen3(),
            Screen4(),)
        val adapter=ViewPagerAdapter(fragmentlist,requireActivity().supportFragmentManager,lifecycle)
       viewpager.adapter=adapter

        next_but.setOnClickListener {
            item += 1
            viewpager.currentItem=item
            if (item==3){
                next_but.visibility=View.GONE
                getstart.visibility=View.VISIBLE
            }
        }
        getstart.setOnClickListener {
             val perferance=requireActivity().getSharedPreferences("OnBording", Context.MODE_PRIVATE)
                     val edit=perferance.edit()
                     edit.putBoolean("introfinsh",true).apply()

            val user_perfer=requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
            val value=user_perfer.getBoolean("saved",false)
            if (value){
                startActivity(Intent(context,Home::class.java))
            }else{
                findNavController().navigate(R.id.action_viewPager_to_bio2)
            }
        }
        return root
    }

}