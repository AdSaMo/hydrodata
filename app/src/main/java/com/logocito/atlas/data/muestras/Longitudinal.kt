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
    SIN_REVESTIR,
    OTROS,
}

enum class AnchoCoronacion {
    MENORQUEUNO,
    ENTREUNOYTRES,
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
    val id : Int,
    override val codigo : String,
    override val idTramo:Int,

    @Campo(
        descripcion = "Huso UTM",
        posición = 0,
        sección = "General"
    )
    var utm : Utm,

    @Campo(
        descripcion = "Coordenada inicio de obra X",
        posición = 1,
        sección = "General"
    )
    var coorInX : String,

    @Campo(
        descripcion = "Coordenada inicio de obra Y",
        posición = 2,
        sección = "General"
    )
    var coorInY : String,

    @Campo(
        descripcion = "Coordenada fin de obra X",
        posición = 3,
        sección = "General"
    )
    var coorFnX : String,

    @Campo(
        descripcion = "Coordenada fin de obra Y",
        posición = 4,
        sección = "General"
    )
    var coorFnY : String,

    @Campo(
        descripcion = "Estado de conservación",
        posición = 5,
        sección = "General"
    )
    var estadoConservacion: EstadoConservacion,

    @Campo(
        descripcion = "Ubicación de la obra",
        posición = 6,
        sección = "General"
    )
    var ubicacionObra: UbicacionObra,

    @Campo(
        descripcion = "Margen donde se sitúa la obra",
        posición = 7,
        sección = "General"
    )
    var margen: Margen,

    @Campo(
        descripcion = "Tipo de obra",
        posición = 8,
        sección = "TipoObra"
    )
    var tipoObra: TipoObra,

    @Campo(
        descripcion = "Observaciones",
        posición = 9,
        sección = "TipoObra"
    )
    var tipoObraObservaciones : String,

    @Campo(
        descripcion = "Función de la obra",
        posición =10,
        sección = "TipoObra"
    )
    var funcionObra: FuncionObra,

    @Campo(
        descripcion = "Cauce",
        posición =11,
        sección = "TipoObra"
    )
    var cauce: Cauce,

    @Campo(
        descripcion = "Material principal",
        posición = 12,
        sección = "TipoObra"
    )
    var materialPrincipal: MaterialPrincipal,

    @Campo(
        descripcion = "Revestimiento",
        posición = 13,
        sección = "TipoObra"
    )
    var revestimiento: Revestimiento,

    @Campo(
        descripcion = "Anotaciones extra del bloque Dimensiones",
        posición =15,
        sección = "Dimensiones",
        imagen = R.drawable.longitudinal,
    )
    var imgDimensiones: String,

    @Campo(
        descripcion = "Ancho en coronación en metros (AC)",
        posición =16,
        sección = "Dimensiones"
    )
    var anchoCoronacion: AnchoCoronacion,

    @Campo(
        descripcion = "Altura interior (Talud cauce) en metros (Hi)",
        posición = 17,
        sección = "Dimensiones"
    )
    var alturaInterior : String,

    @Campo(
        descripcion = "Altura exterior en metros (He)",
        posición = 18,
        sección = "Dimensiones"
    )
    var alturaExterior : String,

    @Campo(
        descripcion = "Longitud talud interior en metros (talud cauce) (Li)",
        posición = 19,
        sección = "Dimensiones",
    )
    var taludInterior : String,

    @Campo(
        descripcion = "Longitud del talud exterior en metros (Le)",
        posición = 20,
        sección = "Dimensiones"
    )
    var taludExterior : String,

    @Campo(
        descripcion = "Distancia media al cauce activo en metros (Dc)",
        posición = 21,
        sección = "Dimensiones"
    )
    var distanciaMediaCauce : String,

    @Campo(
        descripcion = "Otros",
        posición = 22,
        sección = "Dimensiones"
    )
    var otros : String,

    @Campo(
        descripcion = "",
        posición = 23,
        sección = "ObservacionesF"
    )
    var observacionesGenerales : String,
) : Muestra ()

@Dao
interface MuestrasLongitudinalesDao : MuestraDao <MuestraLongitudinal>  {
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