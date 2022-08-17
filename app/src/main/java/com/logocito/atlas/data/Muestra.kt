package com.logocito.atlas.data

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface Muestra <T>{
    //@Query("SELECT id FROM muestras_longitudinales WHERE idTramo = :idTramo")
    fun obtenerIds(idTramo: Int): List<Int>

    //@Query("SELECT codigo FROM muestras_longitudinales WHERE idTramo = :idTramo")
    fun obtenerCodigos(idTramo: Int): List<String>

    //@Query("SELECT id , codigo  FROM muestras_longitudinales WHERE idTramo = :idTramo")
    fun cargarTodas(idTramo: Int): List<Identificador>

    //@Query("SELECT * FROM muestras_longitudinales")
    fun cargarTodasTodas(): List<T>

    //@Query("SELECT * FROM muestras_longitudinales WHERE id = :id")
    fun cargar(id: Int): T

    //@Insert
    fun añadir(muestra: T)

    //@Query("SELECT id FROM muestras_longitudinales WHERE codigo = :codigo")
    fun findId(codigo: String): Int

    //@Update
    fun actualizar (muestra: T)
}