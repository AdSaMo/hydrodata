package com.logocito.atlas.data

import androidx.room.Query


abstract class Muestra {
    abstract val codigo : String
    abstract val idTramo:Long
}


interface MuestraDao <T>{
    //@Query("SELECT id FROM muestras_longitudinales WHERE idTramo = :idTramo")
    fun obtenerIds(idTramo: Long): List<Long>

    //@Query("SELECT codigo FROM muestras_longitudinales WHERE idTramo = :idTramo")
    fun obtenerCodigos(idTramo: Long): List<String>

    //@Query("SELECT id , codigo  FROM muestras_longitudinales WHERE idTramo = :idTramo")
    fun cargarTodas(idTramo: Long): List<Identificador>

    //@Query("SELECT * FROM muestras_longitudinales")
    fun cargarTodasTodas(): List<T>

    //@Query("SELECT * FROM muestras_longitudinales WHERE id = :id")
    fun cargar(id: Long): T

    //@Insert
    fun añadir(muestra: T)

    //@Query("SELECT id FROM muestras_longitudinales WHERE codigo = :codigo")
    fun findId(codigo: String): Long

    //@Update
    fun modificar (muestra: T)

    //@Query("DELETE FROM tramos WHERE id = :id")
    fun eliminar (id : Long)
}