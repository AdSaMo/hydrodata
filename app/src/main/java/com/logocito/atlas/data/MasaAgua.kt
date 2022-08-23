package com.logocito.atlas.data

import androidx.room.*

@Entity(
    tableName ="masas_agua"
)
data class MasaAgua(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val codigo: String,  // Creamos una variable vacía que contendrá el código de campaña
    /* Es una matriz que contendrá los tramos asociados a esta campaña
     */
    //@ColumnInfo (name = "tramos")
    //val tramos: ArrayList<Tramo>,
    //val tramos: ArrayList<String>?,

)

@Dao
interface MasaAguaDao {
    @Query ("SELECT codigo FROM masas_agua")
    fun obtenerCodigosMasasAgua() : List<String>

    @Insert
    fun añadirMasaAgua (masaAgua:MasaAgua)

    @Delete
    fun eliminar (vararg masaAgua: MasaAgua)

    @Query ("SELECT * FROM masas_agua WHERE codigo = :codigo")
    fun cargar (codigo: String) : MasaAgua
    @Update
    fun modificar (masaAgua: MasaAgua)

    @Query ("SELECT id FROM masas_agua WHERE codigo = :codigo")
    fun findId (codigo:String) : Int

    @Query("SELECT codigo FROM masas_agua WHERE id = :id ")
    fun obtenerCodigo (id : Int) : String
}