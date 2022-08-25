package com.logocito.atlas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import android.app.Application
import android.view.View
import android.widget.Button
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.logocito.atlas.R
import com.logocito.atlas.databinding.ActivityMainBinding
import com.logocito.atlas.databinding.FragmentPortadaBinding

class PortadaActivity : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portada)

        val empezar = findViewById<View>(R.id.buttonEmpezar1)
        empezar.setOnClickListener {

        }



    }


}