package com.logocito.atlas.data.muestras

import androidx.room.*
import com.logocito.atlas.R
import com.logocito.atlas.data.Campo
import com.logocito.atlas.data.Identificador
import com.logocito.atlas.data.Muestra
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

enum class AnchoCoronacion {
    MENORQUEUNO,
    ENTREUNOYTRES,
    MAYORQUETRES
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
        sección = "TipoObra"
    )
    var ubicacionObra: UbicacionObra,

    @Campo(
        descripcion = "Margen donde se sitúa la obra",
        posición = 2,
        sección = "TipoObra"
    )
    var margen: Margen,

    @Campo(
        descripcion = "Observaciones",
        posición = 9,
        sección = "TipoObra"
    )
    var observacionesGenerales : String,

    @Campo(
        descripcion = "Observaciones",
        posición = 4,
        sección = "TipoObra"
    )
    var tipoObraObservaciones : String,

    @Campo(
        descripcion = "Estado de conservación",
        posición = 0,
        sección = "TipoObra"
    )
    var estadoConservacion: EstadoConservacion,

    @Campo(
        descripcion = "Tipo de obra",
        posición = 3,
        sección = "TipoObra"
    )
    var tipoObra: TipoObra,

    @Campo(
        descripcion = "Material principal",
        posición = 7,
        sección = "TipoObra"
    )
    var materialPrincipal: MaterialPrincipal,

    @Campo(
        descripcion = "Función de la obra",
        posición =5,
        sección = "TipoObra"
    )
    var funcionObra: FuncionObra,

    @Campo(
        descripcion = "Cauce",
        posición =6,
        sección = "TipoObra"
    )
    var cauce: Cauce,

    @Campo(
        descripcion = "Revestimiento",
        posición = 8,
        sección = "TipoObra"
    )
    var revestimiento: Revestimiento,

    @Campo(
        descripcion = "Ancho en coronación en metros (AC)",
        posición =10,
        sección = "Dimensiones"
    )
    var anchoCoronacion: AnchoCoronacion,

    @Campo(
        descripcion = "Altura interior (Talud cauce) en metros (Hi)",
        posición = 11,
        sección = "Dimensiones"
    )
    var alturaInterior : String,

    @Campo(
        descripcion = "Altura exterior en metros (He)",
        posición = 12,
        sección = "Dimensiones"
    )
    var alturaExterior : String,

    @Campo(
        descripcion = "Longitud talud interior en metros (talud cauce) (Li)",
        posición = 13,
        sección = "Dimensiones",
        imagen = R.drawable.paso_entubado,
    )
    var taludInterior : String,

    @Campo(
        descripcion = "Longitud del talud exterior en metros (Le)",
        posición = 14,
        sección = "Dimensiones"
    )
    var taludExterior : String,

    @Campo(
        descripcion = "Distancia media al cauce activo en metros (Dc)",
        posición = 15,
        sección = "Dimensiones"
    )
    var distanciaMediaCauce : String,

    @Campo(
        descripcion = "Otros",
        posición = 16,
        sección = "Dimensiones"
    )
    var otros : String,
)

@Dao
interface MuestrasLongitudinalesDao : Muestra <MuestraLongitudinal>  {
    @Query("SELECT id FROM muestras_longitudinales WHERE idTramo = :idTramo")
    abstract override fun obtenerIds(idTramo: Int): List<Int>

    @Query("SELECT codigo FROM muestras_longitudinales WHERE idTramo = :idTramo")
    abstract override  fun obtenerCodigos(idTramo: Int): List<String>

    @Query("SELECT id , codigo  FROM muestras_longitudinales WHERE idTramo = :idTramo")
    abstract override fun cargarTodas(idTramo: Int): List<Identificador>

    @Query("SELECT * FROM muestras_longitudinales")
    abstract override fun cargarTodasTodas(): List<MuestraLongitudinal>

    @Query("SELECT * FROM muestras_longitudinales WHERE id = :id")
    abstract override fun cargar(id: Int): MuestraLongitudinal

    @Insert
    abstract override fun añadir(muestra: MuestraLongitudinal)

    @Query("SELECT id FROM muestras_longitudinales WHERE codigo = :codigo")
    abstract override fun findId(codigo: String): Int

    @Update
    abstract override fun actualizar (muestra: MuestraLongitudinal)
}