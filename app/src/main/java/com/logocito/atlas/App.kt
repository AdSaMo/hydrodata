package com.logocito.atlas

import android.app.Application
import androidx.room.Room
import com.logocito.atlas.data.Database

class App : Application() {
    lateinit var database: Database
    override fun onCreate() {
        super.onCreate()
        this.database = Room.databaseBuilder(this, Database::class.java,"atlas11").allowMainThreadQueries().build()

    }
}