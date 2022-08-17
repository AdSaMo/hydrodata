package com.logocito.atlas.data.muestras

import androidx.room.*
import com.logocito.atlas.data.Identificador
import com.logocito.atlas.data.Muestra

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
interface MuestrasTransversalesDao : Muestra<MuestraTransversal> {

    @Query("SELECT id FROM muestras_transversales WHERE idTramo = :idTramo")
    abstract override fun obtenerIds(idTramo: Int): List<Int>

    @Query("SELECT codigo FROM muestras_transversales WHERE idTramo = :idTramo")
    abstract override fun obtenerCodigos(idTramo: Int) : List<String>

    @Insert
    abstract override fun añadir(muestra: MuestraTransversal)

    @Query ("SELECT id FROM muestras_transversales WHERE codigo = :codigo")
    abstract override fun findId (codigo:String) : Int

    @Update
    abstract override fun actualizar (muestra: MuestraTransversal)

    @Query("SELECT id , codigo  FROM muestras_transversales WHERE idTramo = :idTramo")
    abstract override fun cargarTodas(idTramo: Int): List<Identificador>

    @Query("SELECT * FROM muestras_transversales")
    abstract override fun cargarTodasTodas(): List<MuestraTransversal>

    @Query("SELECT * FROM muestras_transversales WHERE id = :id")
    abstract override fun cargar(id: Int): MuestraTransversal
}