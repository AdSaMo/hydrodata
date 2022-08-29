package com.logocito.atlas.data.muestras

import androidx.room.*
import com.logocito.atlas.R
import com.logocito.atlas.data.*

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
    @Opcion(
        descripcion = "Sin revestir"
    )
    SIN_REVESTIR,
    OTROS,
}

enum class AnchoCoronacion {
    @Opcion(
        descripcion = "<1"
    )
    MENORQUEUNO,
    @Opcion(
        descripcion = "1-3"
    )
    ENTREUNOYTRES,
    @Opcion(
        descripcion = ">3"
    )
    MAYORQUETRES
}

enum class Utm {
    @Opcion(
        descripcion = "29"
    )
    VENTINUEVE,
    @Opcion(
        descripcion = "30"
    )
    TREINTA,
    @Opcion(
        descripcion = "31"
    )
    TRANTAIUNO,
}

@Entity(
    tableName ="muestras_longitudinales"
)
data class MuestraLongitudinal (
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    override var codigo : String,
    override val idTramo:Long,

    @Campo(
        descripcion = "Fecha",
        posición = 0,
        sección = "General"
    )
    var fechaLongitudinal : String,

    @Campo(
        descripcion = "Hora",
        posición = 2,
        sección = "General"
    )
    var horaLongitudinal : String,

    @Campo(
        descripcion = "Huso UTM",
        posición = 4,
        sección = "General"
    )
    var utm : Utm,

    @Campo(
        descripcion = "Coordenada inicio de obra X",
        posición = 6,
        sección = "General"
    )
    var coorInX : String,

    @Campo(
        descripcion = "Coordenada inicio de obra Y",
        posición = 8,
        sección = "General"
    )
    var coorInY : String,

    @Campo(
        descripcion = "Coordenada fin de obra X",
        posición = 10,
        sección = "General"
    )
    var coorFnX : String,

    @Campo(
        descripcion = "Coordenada fin de obra Y",
        posición = 12,
        sección = "General"
    )
    var coorFnY : String,

    @Campo(
        descripcion = "Estado de conservación",
        posición = 14,
        sección = "General"
    )
    var estadoConservacion: EstadoConservacion,

    @Campo(
        descripcion = "Ubicación de la obra",
        posición = 16,
        sección = "General"
    )
    var ubicacionObra: UbicacionObra,

    @Campo(
        descripcion = "Margen donde se sitúa la obra",
        posición = 18,
        sección = "General"
    )
    var margen: Margen,

    @Campo(
        descripcion = "Tipo de obra",
        posición = 20,
        sección = "TipoObra"
    )
    var tipoObra: TipoObra,

    @Campo(
        descripcion = "Observaciones",
        posición = 22,
        sección = "TipoObra"
    )
    var tipoObraObservaciones : String,

    @Campo(
        descripcion = "Función de la obra",
        posición =24,
        sección = "TipoObra"
    )
    var funcionObra: FuncionObra,

    @Campo(
        descripcion = "Cauce",
        posición =26,
        sección = "TipoObra"
    )
    var cauce: Cauce,

    @Campo(
        descripcion = "Material principal",
        posición = 28,
        sección = "TipoObra"
    )
    var materialPrincipal: MaterialPrincipal,

    @Campo(
        descripcion = "Revestimiento",
        posición = 30,
        sección = "TipoObra"
    )
    var revestimiento: Revestimiento,

    @Campo(
        descripcion = "Ancho en coronación en metros (AC)",
        posición =32,
        sección = "Dimensiones"
    )
    var anchoCoronacion: AnchoCoronacion,

    @Campo(
        descripcion = "Altura interior (Talud cauce) en metros (Hi)",
        posición = 34,
        sección = "Dimensiones"
    )
    var alturaInterior : String,

    @Campo(
        descripcion = "Altura exterior en metros (He)",
        posición = 36,
        sección = "Dimensiones"
    )
    var alturaExterior : String,

    @Campo(
        descripcion = "Longitud talud interior en metros (talud cauce) (Li)",
        posición = 38,
        sección = "Dimensiones",
    )
    var taludInterior : String,

    @Campo(
        descripcion = "Longitud del talud exterior en metros (Le)",
        posición = 40,
        sección = "Dimensiones"
    )
    var taludExterior : String,

    @Campo(
        descripcion = "Distancia media al cauce activo en metros (Dc)",
        posición = 42,
        sección = "Dimensiones"
    )
    var distanciaMediaCauce : String,

    @Campo(
        descripcion = "Otros",
        posición = 44,
        sección = "Dimensiones"
    )
    var otros : String,

    @Campo(
        descripcion = "Observaciones Generales",
        posición = 46,
        sección = "ObservacionesF"
    )
    var observacionesGenerales : String,
) : Muestra ()

@Dao
interface MuestrasLongitudinalesDao : MuestraDao <MuestraLongitudinal>  {
    @Query("SELECT id FROM muestras_longitudinales WHERE idTramo = :idTramo")
    abstract override fun obtenerIds(idTramo: Long): List<Long>

    @Query("SELECT codigo FROM muestras_longitudinales WHERE idTramo = :idTramo")
    abstract override  fun obtenerCodigos(idTramo: Long): List<String>

    @Query("SELECT id , codigo  FROM muestras_longitudinales WHERE idTramo = :idTramo")
    abstract override fun cargarTodas(idTramo: Long): List<Identificador>

    @Query("SELECT id  FROM muestras_longitudinales WHERE idTramo = :idTramo")
    fun obtenerIdsDeMuestrasLongitudinalesQuePertenezcanAUnTramo(idTramo: Long): List<Long>

    @Query("DELETE FROM muestras_longitudinales WHERE id IN (:ids)")
    fun eliminar (ids : List<Long>)

    @Query("SELECT * FROM muestras_longitudinales")
    abstract override fun cargarTodasTodas(): List<MuestraLongitudinal>

    @Query("SELECT * FROM muestras_longitudinales WHERE id = :id")
    abstract override fun cargar(id: Long): MuestraLongitudinal

    @Insert
    abstract override fun añadir(muestra: MuestraLongitudinal)

    @Query("SELECT id FROM muestras_longitudinales WHERE codigo = :codigo")
    abstract override fun findId(codigo: String): Long

    @Update
    abstract override fun modificar (muestra: MuestraLongitudinal)

    @Query("DELETE FROM muestras_longitudinales WHERE id = :id")
    override fun eliminar (id : Long)
}