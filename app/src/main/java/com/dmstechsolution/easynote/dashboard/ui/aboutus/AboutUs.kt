package com.dmstechsolution.easynote.dashboard.ui.aboutus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dmstechsolution.easynote.R
import com.vansuita.materialabout.builder.AboutBuilder
import com.vansuita.materialabout.views.AboutView


class AboutUs : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val packageName="com.dmstechsolution.easynote"
        val view: AboutView = AboutBuilder.with(requireContext())
                .setPhoto(R.drawable.amana)
                .setCover(R.drawable.android)
                .setName("Amanuel Demelash")
                .setSubTitle("Mobile app Developer and web developer")
                .setBrief("I'm warmed of mobile app technologies and web technologies. Ideas maker, curious and nature lover.")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGooglePlayStoreLink("https://play.google.com/store/apps/details?id=$packageName")
                .addGitHubLink("https://www.github.com/AmanuelDemelash")
                .addWebsiteLink("https://www.dmstechsolution.com")
                .addFacebookLink("user")
                .addInstagramLink("user")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build()
        return view
    }
}