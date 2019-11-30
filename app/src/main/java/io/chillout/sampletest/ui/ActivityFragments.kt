package io.chillout.sampletest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import io.chillout.sampletest.R
import kotlinx.android.synthetic.main.activity_fragments.*

class ActivityFragments : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)
        setSupportActionBar(toolbar)
        toolbar.title = "Fragments Demo boi"
        val navController = Navigation.findNavController(this,R.id.fragment_container_host)
        bottomNav.let {
            NavigationUI.setupWithNavController(it,navController)
        }
    }
}
