package com.jordiortuno.cleanmvvm.framework.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.jordiortuno.cleanmvvm.R
import com.jordiortuno.cleanmvvm.databinding.MainActivityBinding
import com.jordiortuno.cleanmvvm.framework.ui.auth.AuthActivity
import com.jordiortuno.cleanmvvm.framework.ui.main.library.LibraryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {//,ShowDialogRealtimeListener
    private lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)
        binding.navView.menu.findItem(R.id.nav_library).isChecked = true
        binding.navView.menu.performIdentifierAction(R.id.nav_library, 0)
//        RealtimeDatabaseFacade.setShowDialogRealtime(this)
    }


    fun closeSession(){
        val startIntent = Intent(applicationContext, AuthActivity::class.java)
        startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(startIntent)
        finish()
    }

    /*override fun showDialog(dialogType: DialogType) {
        DialogsRealtime.manageDialogsRealtime(this,dialogType)
    }*/
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_library -> supportFragmentManager.beginTransaction()
                .replace(R.id.content, LibraryFragment.newInstance())
                .commit()
            R.id.nav_reader -> {}
        }
        val drawerLayout: DrawerLayout = findViewById(
            R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}