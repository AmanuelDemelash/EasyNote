package com.dmstechsolution.easynote.dashboard

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import com.dmstechsolution.easynote.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController:NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val bottomnav:BottomNavigationView=findViewById(R.id.bottomNavigationView)
         drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
         navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home,R.id.nav_note,R.id.nav_todo,R.id.appointment,R.id.schedule), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val toogle=ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()
        navView.setNavigationItemSelectedListener(this)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.nav_home,R.id.about->{
                    bottomnav.visibility= View.VISIBLE
                    bottomnav.setupWithNavController(navController)
                    toolbar.visibility=View.VISIBLE
                }
                R.id.information->{
                    bottomnav.visibility= View.VISIBLE
                    bottomnav.setupWithNavController(navController)
                    toolbar.visibility=View.GONE
                }
                R.id.aboutUs->{
                    toolbar.visibility=View.GONE
                    bottomnav.visibility= View.GONE
                }
                else->{
                    bottomnav.visibility=View.GONE
                }
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        when (navController.currentDestination!!.id) {
            R.id.nav_home -> {
                val alert= android.app.AlertDialog.Builder(this)
                alert.setIcon(R.drawable.logo)
                alert.setTitle("Exit")
                alert.setMessage("Do you want to exit?")
                alert.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                    finishAffinity()
                }
                alert.setCancelable(false)
                alert.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
                      alert.setCancelable(true)
                }
                alert.show()

            }
            R.id.addnewnote -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_addnewnote_to_nav_note)
            }
            R.id.nav_note -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_nav_note_to_nav_home)
            }
            R.id.nav_todo -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_nav_todo_to_nav_home)
            }
            R.id.addnewTodo -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_addnewTodo_to_nav_todo)
            }
            R.id.appointment -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_appointment_to_nav_home)
            }
            R.id.addnewAppointment -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_addnewAppointment_to_appointment)
            }
            R.id.addnew_schedule2 -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_addnew_schedule2_to_schedule)
            }
            R.id.schedule -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_schedule_to_nav_home)
            }
            R.id.information -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_information_to_nav_home)
            }
            R.id.aboutUs -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.nav_home)
            }
            else -> {
                onSupportNavigateUp()
            }
        }


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_home->{
                    drawerLayout.close()
                    navController.navigate(R.id.nav_home)
            }
            R.id.nav_todo->{
                drawerLayout.close()
                navController.navigate(R.id.nav_todo)
            }
            R.id.nav_note->{
                drawerLayout.close()
                navController.navigate(R.id.nav_note)
            }
            R.id.appointment->{
                drawerLayout.close()
                navController.navigate(R.id.appointment)
            }
            R.id.schedule->{
                drawerLayout.close()
                navController.navigate(R.id.schedule)
            }
            R.id.rate_us->{
                drawerLayout.close()
                try {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
                }catch (error:android.content.ActivityNotFoundException){
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
                }
            }
            R.id.share->{
                drawerLayout.close()
                val intent= Intent(Intent.ACTION_SEND)
                intent.type="app/"
                startActivity(Intent.createChooser(intent,"share"))
            }
            R.id.cancel->{
                drawerLayout.close()
                finishAffinity()
            }
        }
        return true
    }
}