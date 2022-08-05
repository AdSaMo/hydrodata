package com.logocito.atlas.data.muestras

import androidx.room.*
import com.logocito.atlas.data.Campo
import com.logocito.atlas.data.Identificador
import com.logocito.atlas.data.Opcion

enum class UbicacionObra {
    MARGEN_DEL_RIO,
    LLANURA_DE_INUNDACIÓN,
}
enum class Margen {
    IZQUIERDA,
    DERECHA,
}

enum class EstadoConservacion {
    BUENO,
    MEJORABLE,
    INSUFICIENTE,
}

enum class TipoObra {
    ESCOLLERA,
    MURO,
    GAVION,
    RELLENO,
    DIQUE,
    MOTA,
    OTROS_OBSTACULOS_LONGITUDINALES,
}
enum class MaterialPrincipal{
    HORMIGON,
    ROCA,
    TIERRA,
    MADERA,
    OTROS,
}

enum class  FuncionObra  {
    ESTABILIZACION_DE_MARGENES,
    PROTECCION_INUNDACIONES,

}

enum class Cauce {
    @Opcion(
        descripcion = "Canalizado/Encauzado"
    )
    CANALIZADO_ENCAUZADO,
    COBERTURA,
    DRAGADO,
}

enum class Revestimiento {
    HORMIGON,
    ESCOLLERA,
    GAVIONES,
    MAMPOSTERIA,
    SIN_REVESTIR,
    OTROS,
}


@Entity(
    tableName ="muestras_longitudinales"
)
data class MuestraLongitudinal (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val codigo : String,
    val idTramo:Int,

    @Campo(
        descripcion = "Ubicación de la obra",
        posición = 1,
    )
    var ubicacionObra: UbicacionObra,

    @Campo(
        descripcion = "Margen donde se sitúa la obra",
        posición = 2,
    )
    var margen: Margen,

    @Campo(
        descripcion = "Observaciones",
        posición = 9,
    )
    var observacionesGenerales : String,

    @Campo(
        descripcion = "Observaciones",
        posición = 4,
    )
    var tipoObraObservaciones : String,

    @Campo(
        descripcion = "Estado de conservación",
        posición = 0,
    )
    var estadoConservacion: EstadoConservacion,

    @Campo(
        descripcion = "Tipo de obra",
        posición = 3,
    )
    var tipoObra: TipoObra,

    @Campo(
        descripcion = "Material principal",
        posición = 7,
    )
    var materialPrincipal: MaterialPrincipal,

    @Campo(
        descripcion = "Función de la obra",
        posición =5,
    )
    var funcionObra: FuncionObra,

    @Campo(
        descripcion = "Cauce",
        posición =6,
    )
    var cauce: Cauce,

    @Campo(
        descripcion = "Revestimiento",
        posición = 8,
    )
    var revestimiento: Revestimiento,

)

@Dao
interface MuestrasLongitudinalesDao {
    @Query("SELECT id FROM muestras_longitudinales WHERE idTramo = :idTramo")
    fun obtenerIds(idTramo: Int): List<Int>

    @Query("SELECT codigo FROM muestras_longitudinales WHERE idTramo = :idTramo")
    fun obtenerCodigos(idTramo: Int): List<String>

    @Query("SELECT id , codigo  FROM muestras_longitudinales WHERE idTramo = :idTramo")
    fun cargarTodas(idTramo: Int): List<Identificador>

    @Query("SELECT * FROM muestras_longitudinales WHERE id = :id")
    fun cargar(id: Int): MuestraLongitudinal

    @Insert
    fun añadir(muestraLongitudinal: MuestraLongitudinal)

    @Query("SELECT id FROM muestras_longitudinales WHERE codigo = :codigo")
    fun findId(codigo: String): Int

    @Update
    fun actualizar (muestraLongitudinal: MuestraLongitudinal)
}