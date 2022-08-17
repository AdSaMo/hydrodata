package com.logocito.atlas.data.muestras

import androidx.room.*
import com.logocito.atlas.data.Campo
import com.logocito.atlas.data.Identificador
import com.logocito.atlas.data.Muestra
import com.logocito.atlas.data.Opcion

/*enum class TipoSustrato {
    ROCA,
    COLUVIAL,
    ALUVIAL,
    MIXTO
}*/

enum class TamañoDominanteRocoso{
    MENOSCUARENTA,
    ENTRECUARENTAYSETENTA,
    ENTRESETENTAYNOVENTA,
    MAYORNOVENTA
}

enum class TamañoDominanteGrueso{
    MENOSCUARENTA,
    ENTRECUARENTAYSETENTA,
    ENTRESETENTAYNOVENTA,
    MAYORNOVENTA
}

enum class TamañoDominanteFino{
    MENOSCUARENTA,
    ENTRECUARENTAYSETENTA,
    ENTRESETENTAYNOVENTA,
    MAYORNOVENTA
}

enum class TamañoDominanteLodos{
    MENOSCUARENTA,
    ENTRECUARENTAYSETENTA,
    ENTRESETENTAYNOVENTA,
    MAYORNOVENTA
}

enum class ClasificacionSedimentos{
    EFECTIVA,
    LIMITADA,
    NULA,
}

enum class PozaMarmita{
    PRINCIPAL,
    PRESENTE,
}

enum class SaltoPoza{
    PRINCIPAL,
    PRESENTE,
}

enum class RapidoPoza{
    PRINCIPAL,
    PRESENTE,
}

enum class RapidoRemanso{
    PRINCIPAL,
    PRESENTE,
}

enum class RapidoContinuo{
    PRINCIPAL,
    PRESENTE,
}

enum class Grada{
    PRINCIPAL,
    PRESENTE,
}

enum class Rampa{
    PRINCIPAL,
    PRESENTE,
}

enum class Tabla{
    PRINCIPAL,
    PRESENTE,
}

enum class PrincipalModificada{
    SI,
    NO,
}

enum class BarraMarginal{
    PRINCIPAL,
    PRESENTE,
}

enum class BarraCauce{
    PRINCIPAL,
    PRESENTE,
}

enum class Isla{
    PRINCIPAL,
    PRESENTE,
}

enum class CanalSecundario{
    PRINCIPAL,
    PRESENTE,
}

enum class CanalCrecida{
    PRINCIPAL,
    PRESENTE,
}

enum class Surco{
    PRINCIPAL,
    PRESENTE,
}

enum class CauceAbandonado{
    PRINCIPAL,
    PRESENTE,
}

enum class SinNatural{
    PRINCIPAL,
    PRESENTE,
}

enum class MovilidadSedimentos{
    EFECTIVA,
    LIMITADA,
    NULA,
}
enum class SintomasIncision{
    SI,
    NO,
}

enum class DetritosVegetales{
    @Opcion(
        descripcion = "<40%"
    )
    MENORCUARENTA,
    @Opcion(
        descripcion = "40% - 70%"
    )
    ENTRECUARENTAYSETENTA,
    @Opcion(
        descripcion = "70% - 90%"
    )
    ENTRESETENTAYNOVENTA,
    @Opcion(
        descripcion = ">90%"
    )
    MASDENOVENTA,
}

enum class OrillasVegetadas{
    @Opcion(
        descripcion = "<40%"
    )
    MENORCUARENTA,
    @Opcion(
        descripcion = "40% - 70%"
    )
    ENTRECUARENTAYSETENTA,
    @Opcion(
        descripcion = "70% - 90%"
    )
    ENTRESETENTAYNOVENTA,
    @Opcion(
        descripcion = ">90%"
    )
    MASDENOVENTA,
}

enum class MacrofitosSumergidos{
    @Opcion(
        descripcion = "<40%"
    )
    MENORCUARENTA,
    @Opcion(
        descripcion = "40% - 70%"
    )
    ENTRECUARENTAYSETENTA,
    @Opcion(
        descripcion = "70% - 90%"
    )
    ENTRESETENTAYNOVENTA,
    @Opcion(
        descripcion = ">90%"
    )
    MASDENOVENTA,
}

@Entity(
    tableName ="muestras_subtramos"
)
data class MuestraSubtramo (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val codigo : String,
    val idTramo:Int,

    @Campo(
        descripcion = "Coordenada inicio x",
        posición = 1,
        sección = "General"
    )
    var inicioX : String,
    @Campo(
        descripcion = "Coordenada inicio y",
        posición = 2,
        sección = "General"
    )
    var inicioY : String,
    @Campo(
        descripcion = "Coordenada fin x",
        posición = 3,
        sección = "General"
    )
    var finX : String,
    @Campo(
        descripcion = "Coordenada fin y",
        posición = 4,
        sección = "General"
    )
    var finY : String,
    @Campo(
        descripcion = "Longitud Subtramo (m)",
        posición = 5,
        sección = "General"
    )
    var longiSubtramo : String,

    @Campo(
        descripcion = "Ancho del cauce activo (m)",
        posición = 6,
        sección = "General"
    )
    var anchoCauce : String,

    @Campo(
        descripcion = "Ancho ribera topográfica (m)",
        posición = 7,
        sección = "General"
    )
    var anchoTopo : String,

    @Campo(
        descripcion = "Ancho de ribera funcional (m)",
        posición = 8,
        sección = "General"
    )
    var anchoFuncional : String,

    @Campo(
        descripcion = "Accesibilidad",
        posición = 9,
        sección = "General"
    )
    var accesiblidad : String,

    @Campo(
        descripcion = "Roca",
        posición = 10,
        sección = "General"
    )
    var esRoca: Boolean,

    @Campo(
        descripcion = "Coluvial",
        posición = 11,
        sección = "General"
    )
    var esColuvial: Boolean,

    @Campo(
        descripcion = "Aluvial",
        posición = 12,
        sección = "General"
    )
    var esAluvial: Boolean,

    @Campo(
        descripcion = "Rocoso",
        posición = 13,
        sección = "Tamaño dominante del sedimento"
    )
    var tamañoDominanteRocoso: TamañoDominanteRocoso,

    @Campo(
        descripcion = "Grueso",
        posición = 15,
        sección = "Tamaño dominante del sedimento"
    )
    var tamañoDominanteGrueso: TamañoDominanteGrueso,

    @Campo(
        descripcion = "Fino",
        posición = 16,
        sección = "Tamaño dominante del sedimento"
    )
    var tamañoDominanteFino: TamañoDominanteFino,

    @Campo(
        descripcion = "Lodos",
        posición = 17,
        sección = "Tamaño dominante del sedimento"
    )
    var tamañoDominanteLodos: TamañoDominanteLodos,

    @Campo(
        descripcion = "Clasificación Sedimentos",
        posición = 18,
        sección = "Clasificacion Sedimentos"
    )
    var clasificacionSedimentos: ClasificacionSedimentos,

    @Campo(
        descripcion = "Poza/Marmita de gigante",
        posición = 19,
        sección = "Tipo de estructura longitudinal principal"
    )
    var pozaMarmita: PozaMarmita,

    @Campo(
        descripcion = "Salto/Poza",
        posición = 20,
        sección = "Tipo de estructura longitudinal principal"
    )
    var saltoPoza: SaltoPoza,

    @Campo(
        descripcion = "Rapido/Poza",
        posición = 21,
        sección = "Tipo de estructura longitudinal principal"
    )
    var rapidoPoza: RapidoPoza,

    @Campo(
        descripcion = "Rapido/Remanso",
        posición = 22,
        sección = "Tipo de estructura longitudinal principal"
    )
    var rapidoRemanso: RapidoRemanso,

    @Campo(
        descripcion = "Rapido continuo",
        posición = 23,
        sección = "Tipo de estructura longitudinal principal"
    )
    var rapidoContinuo: RapidoContinuo,

    @Campo(
        descripcion = "Grada",
        posición = 24,
        sección = "Tipo de estructura longitudinal principal"
    )
    var grada: Grada,

    @Campo(
        descripcion = "Rampa",
        posición = 25,
        sección = "Tipo de estructura longitudinal principal"
    )
    var rampa: Rampa,

    @Campo(
        descripcion = "Tabla",
        posición = 26,
        sección = "Tipo de estructura longitudinal principal"
    )
    var tabla: Tabla,

    @Campo(
        descripcion = "¿Principal modificada?",
        posición = 27,
        sección = "Tipo de estructura longitudinal principal"
    )
    var principalModificada: PrincipalModificada,

    @Campo(
        descripcion = "Otra (Especificar)",
        posición = 28,
        sección = "Tipo de estructura longitudinal principal"
    )
    var otra : String,

    @Campo(
        descripcion = "Barra Marginal",
        posición = 29,
        sección = "Formas y depósitos emergentes del lecho"
    )
    var barraMarginal: BarraMarginal,

    @Campo(
        descripcion = "Barra en el cauce",
        posición = 30,
        sección = "Formas y depósitos emergentes del lecho"
    )
    var barraCauce: BarraCauce,

    @Campo(
        descripcion = "Isla",
        posición = 31,
        sección = "Formas y depósitos emergentes del lecho"
    )
    var isla: Isla,

    @Campo(
        descripcion = "Canal secundario",
        posición = 32,
        sección = "Formas y depósitos emergentes del lecho"
    )
    var canalSecundario: CanalSecundario,

    @Campo(
        descripcion = "Canal de crecida",
        posición = 33,
        sección = "Formas y depósitos emergentes del lecho"
    )
    var canalCrecida: CanalCrecida,

    @Campo(
        descripcion = "Surco",
        posición = 34,
        sección = "Formas y depósitos emergentes del lecho"
    )
    var surco: Surco,

    @Campo(
        descripcion = "Cauce Abandonado",
        posición = 35,
        sección = "Formas y depósitos emergentes del lecho"
    )
    var cauceAbandonado: CauceAbandonado,

    @Campo(
        descripcion = "Sin formas naturales",
        posición = 36,
        sección = "Formas y depósitos emergentes del lecho"
    )
    var sinNatural: SinNatural,

    @Campo(
        descripcion = "Otra (a especificar)",
        posición = 37,
        sección = "Formas y depósitos emergentes del lecho"
    )
    var otraFormas : String,

    @Campo(
        descripcion = "Movilidad de los sedimentos",
        posición = 38,
        sección = "Movilidad de los sedimentos"
    )
    var movilidadSedimentos: MovilidadSedimentos,

    @Campo(
        descripcion = "¿Hay síntomas?",
        posición = 39,
        sección = "Síntomas de dinámica vertical acelerada"
    )
    var sintomasIncision: SintomasIncision,

    @Campo(
        descripcion = "Diferencia de altura entre el nivel de las márgenes donde la pendiente cambia significativamente y el nivel del cauce en aguas bajas (m)",
        posición = 40,
        sección = "Síntomas de dinámica vertical acelerada"
    )
    var diferenciaAltura : String,

    @Campo(
        descripcion = "Grado de accesibilidad de las orillas y conexión transversal (Alta/Moderado/Bajo/Muy bajo)",
        posición = 41,
        sección = "Síntomas de dinámica vertical acelerada"
    )
    var gradoAccesibilidad : String,

    @Campo(
        descripcion = "Remociones, extracciones de áridos y dragados",
        posición = 42,
        sección = "Alteración de la estructura y sustrato del lecho"
    )
    var remociones : Boolean,

    @Campo(
        descripcion = "Descripción",
        posición = 43,
        sección = "Alteración de la estructura y sustrato del lecho"
    )
    var descripcionRemociones : String,

    @Campo(
        descripcion = "Azudes y otras estructuras de fondo",
        posición = 44,
        sección = "Alteración de la estructura y sustrato del lecho"
    )
    var alter : Boolean,

    @Campo(
        descripcion = "Descripción",
        posición = 45,
        sección = "Alteración de la estructura y sustrato del lecho"
    )
    var descripcionAlter : String,

    @Campo(
        descripcion = "Detritos vegetales",
        posición = 46,
        sección = "Otros hábitats diferenciables"
    )
    var detritosVegetales: DetritosVegetales,

    @Campo(
        descripcion = "Orillas Vegetadas",
        posición = 47,
        sección = "Otros hábitats diferenciables"
    )
    var orillasVegetadas: OrillasVegetadas,

    @Campo(
        descripcion = "Macrófitos sumergidos",
        posición = 48,
        sección = "Otros hábitats diferenciables"
    )
    var macrofitosSumergidos: MacrofitosSumergidos,

    @Campo(
        descripcion = "Observaciones",
        posición = 49,
        sección = "Otros hábitats diferenciables"
    )
    var obsHabitats : String,

)
@Dao
interface MuestrasSubtramosDao : Muestra<MuestraSubtramo> {
    @Query("SELECT id FROM muestras_subtramos WHERE idTramo = :idTramo")
    abstract override fun obtenerIds(idTramo: Int): List<Int>

    @Query("SELECT codigo FROM muestras_subtramos WHERE idTramo = :idTramo")
    abstract override fun obtenerCodigos(idTramo: Int): List<String>

    @Insert
    abstract override fun añadir(muestra: MuestraSubtramo)

    @Query("SELECT id FROM muestras_subtramos WHERE codigo = :codigo")
    abstract override fun findId(codigo: String): Int

    @Update
    abstract override fun actualizar (muestra: MuestraSubtramo)

    @Query("SELECT * FROM muestras_subtramos WHERE id = :id")
    abstract override fun cargar(id: Int): MuestraSubtramo

    @Query("SELECT id , codigo  FROM muestras_subtramos WHERE idTramo = :idTramo")
    abstract override fun cargarTodas(idTramo: Int): List<Identificador>

    @Query("SELECT * FROM muestras_subtramos")
    abstract override fun cargarTodasTodas(): List<MuestraSubtramo>
}