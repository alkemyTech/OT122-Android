package com.alkemy.ongsomosmas.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import com.alkemy.ongsomosmas.R
import com.alkemy.ongsomosmas.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toogle: ActionBarDrawerToggle

    private lateinit var binding: ActivityHomeBinding
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBar.toolbar)
        navigationDrawerConfig()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toogle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //TODO Replace with navigation logic
            R.id.nav_home -> {Toast.makeText(this, "Fragment home", Toast.LENGTH_SHORT).show()}
            R.id.nav_about_us -> {Toast.makeText(this, "Fragment about us", Toast.LENGTH_SHORT).show()}
            R.id.nav_activities -> {Toast.makeText(this, "Fragment activities", Toast.LENGTH_SHORT).show()}
            R.id.nav_news -> {Toast.makeText(this, "Fragment news", Toast.LENGTH_SHORT).show()}
            R.id.nav_testimonials -> {Toast.makeText(this, "Fragment testimonials", Toast.LENGTH_SHORT).show()}
            R.id.nav_contact_us -> {Toast.makeText(this, "Fragment contact us", Toast.LENGTH_SHORT).show()}
            R.id.nav_contribute -> {Toast.makeText(this, "Fragment contribute", Toast.LENGTH_SHORT).show()}
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toogle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toogle.onConfigurationChanged(newConfig)
    }

    fun navigationDrawerConfig(){
        drawerLayout = binding.drawerLayout

        toogle = ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toogle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = binding.navView
        navigationView.setNavigationItemSelectedListener(this)

        setTitle(R.string.menu_item_home)
    }


}
