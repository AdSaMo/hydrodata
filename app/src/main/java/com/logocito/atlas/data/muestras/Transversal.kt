package com.logocito.atlas.data.muestras

import androidx.room.*
//import com.logocito.atlas.data.Muestra

@Entity(
    tableName ="muestras_transversales"
)
data class MuestraTransversal (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val codigo : String,
    val idTramo:Int,
    var longitud : Int,
    var coordenadaX : Long,
    var coordenadaY : Long,
)

@Dao
interface MuestrasTransversalesDao {
    @Query("SELECT id FROM muestras_transversales WHERE idTramo = :idTramo")
    fun obtenerIds(idTramo: Int): List<Int>
    @Query("SELECT codigo FROM muestras_transversales WHERE idTramo = :idTramo")
    fun obtenerCodigos(idTramo: Int) : List<String>
    @Insert
    fun añadir(muestraTransversal: MuestraTransversal)
    @Query ("SELECT id FROM muestras_transversales WHERE codigo = :codigo")
    fun findId (codigo:String) : Int
    @Update
    fun actualizar (muestraTransversal: MuestraTransversal)

}