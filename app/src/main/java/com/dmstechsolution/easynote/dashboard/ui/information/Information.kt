package com.dmstechsolution.easynote.dashboard.ui.information


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import androidx.navigation.fragment.findNavController
import com.dmstechsolution.easynote.R

class Information : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root= inflater.inflate(R.layout.information_fragment, container, false)

        val aboutus=root.findViewById<Button>(R.id.aboutus)
        val rate=root.findViewById<RatingBar>(R.id.ratingBar)
        rate.setOnRatingBarChangeListener { ratingBar: RatingBar, fl: Float, b: Boolean ->
            if (b){
                val packageName="com.dmstechsolution.easynote"
                try {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
                }catch (error:android.content.ActivityNotFoundException){
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
                }

            }
        }
        aboutus.setOnClickListener {
            findNavController().navigate(R.id.action_information_to_aboutUs)
        }

        return root
    }
}