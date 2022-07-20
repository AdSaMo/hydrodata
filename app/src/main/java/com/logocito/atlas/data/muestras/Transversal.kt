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
)
@Dao
interface MuestrasTransversalesDao {
    @Query("SELECT codigo FROM muestras_transversales WHERE idTramo = :idTramo")
    fun obtenerCodigosMuestrasTransversales(idTramo: Int) : List<String>
    @Insert
    fun crearMuestraTransversal(muestraTransversal: MuestraTransversal)
    @Query ("SELECT id FROM muestras_transversales WHERE codigo = :codigo")
    fun findId (codigo:String) : Int

}