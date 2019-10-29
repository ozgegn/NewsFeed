package com.comenginar.newsfeed.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.comenginar.newsfeed.R
import com.comenginar.newsfeed.databinding.ActivityMainBinding
import com.comenginar.newsfeed.utils.extensions.setupWithNavController

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (savedInstanceState == null)
            setupBottomNavigationBar()

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state
     */
    private fun setupBottomNavigationBar() {

        val navGraphIds =
            listOf(R.navigation.nav_newsfeed, R.navigation.nav_allnews, R.navigation.nav_profile)

        //Setup the bottom navigation view with a list of navigation graphs
        val controller = binding!!.mainBottomView.setupWithNavController(
            navGraphIds = navGraphIds,
            containerId = R.id.nav_host_container,
            fragmentManager = supportFragmentManager,
            intent = intent
        )

        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })

        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

}
