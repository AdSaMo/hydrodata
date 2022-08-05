package com.logocito.atlas.data.muestras

import androidx.room.*
import com.logocito.atlas.data.Muestra

@Entity(
    tableName ="muestras_subtramos"
)
data class MuestraSubtramo (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val codigo : String,
    val idTramo:Int,
)
@Dao
interface MuestrasSubtramosDao {
    @Query("SELECT id FROM muestras_subtramos WHERE idTramo = :idTramo")
    fun obtenerIds(idTramo: Int): List<Int>

    @Query("SELECT codigo FROM muestras_subtramos WHERE idTramo = :idTramo")
    fun obtenerCodigos(idTramo: Int): List<String>

    @Insert
    fun añadir(muestraSubtramos: MuestraSubtramo)

    @Query("SELECT id FROM muestras_subtramos WHERE codigo = :codigo")
    fun findId(codigo: String): Int

    @Update
    fun actualizar (muestraSubtramo: MuestraSubtramo)
}