package com.logocito.atlas.data.muestras

import androidx.room.*
import com.logocito.atlas.data.Muestra

@Entity(
    tableName ="muestras_longitudinales"
)
data class MuestraLongitudinal (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val codigo : String,
    val idTramo:Int,
)
@Dao
interface MuestrasLongitudinalesDao {
    @Query("SELECT codigo FROM muestras_longitudinales WHERE idTramo = :idTramo")
    fun obtenerCodigos(idTramo: Int): List<String>

    @Insert
    fun añadir(muestraLongitudinal: MuestraLongitudinal)

    @Query("SELECT id FROM muestras_longitudinales WHERE codigo = :codigo")
    fun findId(codigo: String): Int
}