package com.logocito.atlas.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.logocito.atlas.data.muestras.*

@Database(
    entities = [
        MasaAgua::class,
        Tramo::class,
        MuestraTransversal::class,
        MuestraLongitudinal::class,
        MuestraSubtramo::class,
    ],
    version = 3,
)
abstract class Database : RoomDatabase() {
    abstract fun masaAguaDao(): MasaAguaDao
    abstract fun tramoDao(): TramoDao
    abstract fun muestrasTransversalesDao(): MuestrasTransversalesDao
    abstract fun muestrasLongitudinalesDao(): MuestrasLongitudinalesDao
    abstract fun muestrasSubtramosDao(): MuestrasSubtramosDao

}

data class Identificador (
    var id : Long,
    var codigo : String,
)