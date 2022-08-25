package com.logocito.atlas.data

import androidx.room.*

@Entity(
    tableName ="tramos"
)
data class Tramo (
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    var codigo : String,
    val masaAgua:Long,
    //val muestras : ArrayList<Muestra>,
)
@Dao
interface TramoDao {
    @Query("SELECT codigo FROM tramos WHERE masaAgua = :idMasaAgua ")
    fun obtenerCodigosTramos(idMasaAgua: Long) : List<String>

    @Insert
    fun crearTramo(tramo : Tramo)

    @Query ("SELECT * FROM tramos WHERE id = :id")
    fun cargar (id : Long) : Tramo

    @Update
    fun modificar (tramo: Tramo)

    @Query ("SELECT id FROM tramos WHERE codigo = :codigo")
    fun findId (codigo:String) : Long

    @Query("SELECT codigo FROM tramos WHERE id = :id ")
    fun obtenerCodigo (id : Long) : String

    @Query("SELECT id , codigo  FROM tramos WHERE masaAgua = :idMasaAgua ")
    fun cargarTodos(idMasaAgua: Long): List<Identificador>

    @Query("DELETE FROM tramos WHERE id = :id")
    fun eliminar (id : Long)

    @Query("DELETE FROM tramos WHERE id IN (:ids)")
    fun eliminar (ids : List<Long>)

    @Query("SELECT id  FROM tramos WHERE masaAgua = :idMasaAgua")
    fun obtenerIdsDeTramosQuePertenezcanAUnaMasaDeAgua(idMasaAgua: Long): List<Long>
}
