package com.logocito.atlas.data

import androidx.room.*

@Entity(
    tableName ="tramos"
)
data class Tramo (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val codigo : String,
    val masaAgua:Int,
    //val muestras : ArrayList<Muestra>,
)
@Dao
interface TramoDao {
    @Query("SELECT codigo FROM tramos WHERE masaAgua = :idMasaAgua ")
    fun obtenerCodigosTramos(idMasaAgua: Int) : List<String>
    @Insert
    fun crearTramo(tramo : Tramo)
    @Query ("SELECT * FROM tramos WHERE codigo = :codigo")
    fun cargar (codigo: String) : Tramo
    @Update
    fun modificar (tramo: Tramo)
    @Query ("SELECT id FROM tramos WHERE codigo = :codigo")
    fun findId (codigo:String) : Int

}
