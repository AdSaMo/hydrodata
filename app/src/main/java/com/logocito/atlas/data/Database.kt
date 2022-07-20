package com.logocito.atlas.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.logocito.atlas.data.muestras.MuestraTransversal
import com.logocito.atlas.data.muestras.MuestrasTransversalesDao

@Database(
    entities = [
        MasaAgua::class,
        Tramo::class,
        MuestraTransversal::class,
    ],
    version = 3,
)
abstract class Database : RoomDatabase() {
    abstract fun masaAguaDao(): MasaAguaDao
    abstract fun tramoDao(): TramoDao
    abstract fun muestrasTransversalesDao(): MuestrasTransversalesDao
}

