package com.logocito.atlas.data.muestras

import androidx.room.*
import com.logocito.atlas.data.*
import com.logocito.atlas.R

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

enum class GradoContacto{
    @Opcion(
        descripcion = "Imbricada (Conjunto continuo y enlazado)"
    )
    IMBRICADA,
    @Opcion(
        descripcion = "Conectada (Conexión mayor parte)"
    )
    CONECTADA,
    @Opcion(
        descripcion = "Moderada (Conexión en varios puntos)"
    )
    MODERADA,
    @Opcion(
        descripcion = "Escasa (Conexión de forma esporádica)"
    )
    ESCASA,
    @Opcion(
        descripcion = "Inconexa (No hay conexión)"
    )
    INCONEXA
}

enum class CategoriaDiversidad{
    @Opcion(
        descripcion = "Muy alta (todas las clases de edad)"
    )
    MUYALTA,
    @Opcion(
        descripcion = "Alta (Mayor parte de clases de edad con regenerado y árboles extramaduros)"
    )
    ALTA,
    @Opcion(
        descripcion = "Media (Mayor parte de clases de edad con regenerado y árboles extramaduros)"
    )
    MEDIA,
    @Opcion(
        descripcion = "Baja (Pocas clases de edad, árboles adultos y extramaduros)"
    )
    BAJA,
    @Opcion(
        descripcion = "Muy baja (una única clase de edad, en general árboles maduros)"
    )
    MUYBAJA,
}

enum class MiGradoAfeccion{
    @Opcion(
        descripcion = "Muy alta (>90%)"
    )
    MUYALTA,
    @Opcion(
        descripcion = "Alta (70-90%)"
    )
    ALTA,
    @Opcion(
        descripcion = "Media (50-70%)"
    )
    MEDIA,
    @Opcion(
        descripcion = "Baja (30-50%)"
    )
    BAJA,
    @Opcion(
        descripcion = "Muy baja (<30%)"
    )
    MUYBAJA,
}

enum class MdGradoAfeccion{
    @Opcion(
        descripcion = "Muy alta (>90%)"
    )
    MUYALTA,
    @Opcion(
        descripcion = "Alta (70-90%)"
    )
    ALTA,
    @Opcion(
        descripcion = "Media (50-70%)"
    )
    MEDIA,
    @Opcion(
        descripcion = "Baja (30-50%)"
    )
    BAJA,
    @Opcion(
        descripcion = "Muy baja (<30%)"
    )
    MUYBAJA,
}

enum class MiGradoAfeccionAloctona{
    @Opcion(
        descripcion = "Muy alta (>90%)"
    )
    MUYALTA,
    @Opcion(
        descripcion = "Alta (70-90%)"
    )
    ALTA,
    @Opcion(
        descripcion = "Media (50-70%)"
    )
    MEDIA,
    @Opcion(
        descripcion = "Baja (30-50%)"
    )
    BAJA,
    @Opcion(
        descripcion = "Muy baja (<30%)"
    )
    MUYBAJA,
}
enum class MdGradoAfeccionAloctona{
    @Opcion(
        descripcion = "Muy alta (>90%)"
    )
    MUYALTA,
    @Opcion(
        descripcion = "Alta (70-90%)"
    )
    ALTA,
    @Opcion(
        descripcion = "Media (50-70%)"
    )
    MEDIA,
    @Opcion(
        descripcion = "Baja (30-50%)"
    )
    BAJA,
    @Opcion(
        descripcion = "Muy baja (<30%)"
    )
    MUYBAJA,
}

@Entity(
    tableName ="muestras_subtramos"
)
data class MuestraSubtramo (
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    override var codigo : String,
    override val idTramo:Long,

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
        sección = "TamañoDominante"
    )
    var tamañoDominanteRocoso: TamañoDominanteRocoso,

    @Campo(
        descripcion = "Grueso",
        posición = 15,
        sección = "TamañoDominante"
    )
    var tamañoDominanteGrueso: TamañoDominanteGrueso,

    @Campo(
        descripcion = "Fino",
        posición = 16,
        sección = "TamañoDominante"
    )
    var tamañoDominanteFino: TamañoDominanteFino,

    @Campo(
        descripcion = "Lodos",
        posición = 17,
        sección = "TamañoDominante"
    )
    var tamañoDominanteLodos: TamañoDominanteLodos,

    @Campo(
        descripcion = "Clasificación Sedimentos",
        posición = 18,
        sección = "ClasificacionSedimentos"
    )
    var clasificacionSedimentos: ClasificacionSedimentos,

    @Campo(
        descripcion = "Poza/Marmita de gigante",
        posición = 19,
        sección = "TipoLongitudinal"
    )
    var pozaMarmita: PozaMarmita,

    @Campo(
        descripcion = "Salto/Poza",
        posición = 20,
        sección = "TipoLongitudinal"
    )
    var saltoPoza: SaltoPoza,

    @Campo(
        descripcion = "Rapido/Poza",
        posición = 21,
        sección = "TipoLongitudinal"
    )
    var rapidoPoza: RapidoPoza,

    @Campo(
        descripcion = "Rapido/Remanso",
        posición = 22,
        sección = "TipoLongitudinal"
    )
    var rapidoRemanso: RapidoRemanso,

    @Campo(
        descripcion = "Rapido continuo",
        posición = 23,
        sección = "TipoLongitudinal"
    )
    var rapidoContinuo: RapidoContinuo,

    @Campo(
        descripcion = "Grada",
        posición = 24,
        sección = "TipoLongitudinal"
    )
    var grada: Grada,

    @Campo(
        descripcion = "Rampa",
        posición = 25,
        sección = "TipoLongitudinal"
    )
    var rampa: Rampa,

    @Campo(
        descripcion = "Tabla",
        posición = 26,
        sección = "TipoLongitudinal"
    )
    var tabla: Tabla,

    @Campo(
        descripcion = "¿Principal modificada?",
        posición = 27,
        sección = "TipoLongitudinal"
    )
    var principalModificada: PrincipalModificada,

    @Campo(
        descripcion = "Otra (Especificar)",
        posición = 28,
        sección = "TipoLongitudinal"
    )
    var otra : String,

    @Campo(
        descripcion = "Barra Marginal",
        posición = 30,
        sección = "DepositosEmergentes"
    )
    var barraMarginal: BarraMarginal,

    @Campo(
        descripcion = "Barra en el cauce",
        posición = 31,
        sección = "DepositosEmergentes"
    )
    var barraCauce: BarraCauce,

    @Campo(
        descripcion = "Isla",
        posición = 32,
        sección = "DepositosEmergentes"
    )
    var isla: Isla,

    @Campo(
        descripcion = "Canal secundario",
        posición = 33,
        sección = "DepositosEmergentes"
    )
    var canalSecundario: CanalSecundario,

    @Campo(
        descripcion = "Canal de crecida",
        posición = 34,
        sección = "DepositosEmergentes"
    )
    var canalCrecida: CanalCrecida,

    @Campo(
        descripcion = "Surco",
        posición = 35,
        sección = "DepositosEmergentes"
    )
    var surco: Surco,

    @Campo(
        descripcion = "Cauce Abandonado",
        posición = 36,
        sección = "DepositosEmergentes"
    )
    var cauceAbandonado: CauceAbandonado,

    @Campo(
        descripcion = "Sin formas naturales",
        posición = 37,
        sección = "DepositosEmergentes"
    )
    var sinNatural: SinNatural,

    @Campo(
        descripcion = "Otra (a especificar)",
        posición = 38,
        sección = "DepositosEmergentes"
    )
    var otraFormas : String,

    @Campo(
        descripcion = "Movilidad de los sedimentos",
        posición = 39,
        sección = "MovilidadSedimentos"
    )
    var movilidadSedimentos: MovilidadSedimentos,

    @Campo(
        descripcion = "¿Hay síntomas?",
        posición = 41,
        sección = "SintomasDinamicaAcelerada"
    )
    var sintomasIncision: SintomasIncision,

    @Campo(
        descripcion = "Diferencia de altura entre el nivel de las márgenes donde la pendiente cambia significativamente y el nivel del cauce en aguas bajas (m)",
        posición = 42,
        sección = "SintomasDinamicaAcelerada"
    )
    var diferenciaAltura : String,

    @Campo(
        descripcion = "Grado de accesibilidad de las orillas y conexión transversal (Alta/Moderado/Bajo/Muy bajo)",
        posición = 43,
        sección = "SintomasDinamicaAcelerada"
    )
    var gradoAccesibilidad : String,

    @Campo(
        descripcion = "Remociones, extracciones de áridos y dragados",
        posición = 44,
        sección = "AlteracionEstructuraFuncion"
    )
    var remociones : Boolean,

    @Campo(
        descripcion = "Descripción",
        posición = 45,
        sección = "AlteracionEstructuraFuncion"
    )
    var descripcionRemociones : String,

    @Campo(
        descripcion = "Azudes y otras estructuras de fondo",
        posición = 46,
        sección = "AlteracionEstructuraFuncion"
    )
    var alter : Boolean,

    @Campo(
        descripcion = "Descripción",
        posición = 47,
        sección = "AlteracionEstructuraFuncion"
    )
    var descripcionAlter : String,

    @Campo(
        descripcion = "Detritos vegetales",
        posición = 48,
        sección = "OtrosHabitats"
    )
    var detritosVegetales: DetritosVegetales,

    @Campo(
        descripcion = "Orillas Vegetadas",
        posición = 49,
        sección = "OtrosHabitats"
    )
    var orillasVegetadas: OrillasVegetadas,

    @Campo(
        descripcion = "Macrófitos sumergidos",
        posición = 50,
        sección = "OtrosHabitats"
    )
    var macrofitosSumergidos: MacrofitosSumergidos,

    @Campo(
        descripcion = "Observaciones",
        posición = 51,
        sección = "OtrosHabitats"
    )
    var obsHabitats : String,

    @Campo(
        descripcion = "Conectividad ecológica longitudinal (%) Margen izquierdo",
        posición = 52,
        sección = "DimensionesRie"
    )
    var miConectEco : String,

    @Campo(
        descripcion = "Conectividad ecológica longitudinal (%) Margen derecho",
        posición = 53,
        sección = "DimensionesRie"
    )
    var mdConectEco : String,

    @Campo(
        descripcion = "Conectividad ecológica longitudinal (%) global",
        posición = 54,
        sección = "DimensionesRie"
    )
    var gConectEco : String,

    @Campo(
        descripcion = "Porcentaje del cauce sombreado por la vegetación de ribera (%) Margen izquierdo",
        posición = 55,
        sección = "DimensionesRie"
    )
    var miCauceSombreado : String,

    @Campo(
        descripcion = "Porcentaje del cauce sombreado por la vegetación de ribera (%) Margen derecho",
        posición = 56,
        sección = "DimensionesRie"
    )
    var mdCauceSombreado : String,

    @Campo(
        descripcion = "Porcentaje del cauce sombreado por la vegetación de ribera (%) global",
        posición = 57,
        sección = "DimensionesRie"
    )
    var gCauceSombreado : String,

    @Campo(
        descripcion = "Conectividad ecológica trasnversal (%) Margen izquierdo",
        posición = 58,
        sección = "DimensionesRie"
    )
    var miConectTransv : String,

    @Campo(
        descripcion = "Conectividad ecológica trasnversal (%) Margen derecho",
        posición = 59,
        sección = "DimensionesRie"
    )
    var mdConectTransv : String,

    @Campo(
        descripcion = "Conectividad ecológica trasnversal (%) global",
        posición = 60,
        sección = "DimensionesRie"
    )
    var gConectTransv : String,

    @Campo(
        descripcion = "Categoría de conexión/grado de contacto entre diferentes estratos (arbustivo, no leñoso, etc.)",
        posición = 61,
        sección = "DimensionesRie"
    )
    var gradoContacto: GradoContacto,

    @Campo(
        descripcion = "Observaciones",
        posición = 62,
        sección = "DimensionesRie"
    )
    var gradoContactoObservaciones: String,

    @Campo(
        descripcion = "Formación dominante en la vegetación ribereña",
        posición = 63,
        sección = "ComposicionVegetacion"
    )
    var formacionDominante: String,

    @Campo(
        descripcion = "Formaciones acompañantes",
        posición = 64,
        sección = "ComposicionVegetacion"
    )
    var formacionAcompañante: String,

    @Campo(
        descripcion = "Etapas regresivas de la vegetación de ribera",
        posición = 65,
        sección = "ComposicionVegetacion"
    )
    var eRegresiva: String,

    @Campo(
        descripcion = "Especies autóctonas presentes",
        posición = 66,
        sección = "ComposicionVegetacion"
    )
    var eAutoctonas: String,

    @Campo(
        descripcion = "Especies alóctonas presentes",
        posición = 67,
        sección = "ComposicionVegetacion"
    )
    var eAloctonas: String,

    @Campo(
        descripcion = "Naturalidad: porcentaje de la ribera funcional con especies autóctonas de ribera (%)",
        posición = 68,
        sección = "ComposicionVegetacion"
    )
    var naturalidad: String,

    @Campo(
        descripcion = "Superficie de la ribera funcional (%) con especies indicadoras de etapas regresivas (nitrófilas, ruderales, arvenses, etc.), tanto autóctonas como alóctonas (también vegetación no estrictamente ribereña)",
        posición = 69,
        sección = "ComposicionVegetacion"
    )
    var especies: String,

    @Campo(
        descripcion = "Categoría de diversidad de clases de edad (vegetación autóctona)",
        posición = 70,
        sección = "ComposicionVegetacion"
    )
    var categoriaDiversidad: CategoriaDiversidad,

    @Campo(
        descripcion = "Observaciones",
        posición = 71,
        sección = "ComposicionVegetacion"
    )
    var categoriaDiversidadObs: String,

    @Campo(
        descripcion = "Funcionalidad de la ribera: Desconexión de la ribera funcional respecto a la ribera",
        posición = 72,
        sección = "CalidadHabitat"
    )
    var funcionalidadRibera: String,

    @Campo(
        descripcion = "Zonificación de la ribera funcional (%) con limitaciones en su conexión transversal con estructuras artificiales",
        posición = 73,
        sección = "CalidadHabitat"
    )
    var zonTransversal: String,

    @Campo(
        descripcion = "Zonificación de la ribera funcional (%) con limitaciones en su permeabilidad y alteración de los materiales de la ribera funcional por actividades humanas",
        posición = 74,
        sección = "CalidadHabitat"
    )
    var zonPermeable: String,

    @Campo(
        descripcion = "Observaciones",
        posición = 75,
        sección = "CalidadHabitat"
    )
    var calidadObservaciones: String,

    @Campo(
        descripcion = "Anchura media de la ribera topográfica margen izquierdo (m)",
        posición = 76,
        sección = "SinRibera"
    )
    var miAnchuraMedia: String,

    @Campo(
        descripcion = "Anchura media de la ribera topográfica margen derecho (m)",
        posición = 77,
        sección = "SinRibera"
    )
    var mdAnchuraMedia: String,

    @Campo(
        descripcion = "Grado de afección en la ribera topofráfica por la existencia de vías de comunicación longitudinales y transversales, estructuras artificiales o usos humanos del suelo. Margen izquierdo",
        posición = 78,
        sección = "SinRibera"
    )
    var miGradoAfeccion: MiGradoAfeccion,

    @Campo(
        descripcion = "Grado de afección en la ribera topofráfica por la existencia de vías de comunicación longitudinales y transversales, estructuras artificiales o usos humanos del suelo. Margen derecho",
        posición = 79,
        sección = "SinRibera"
    )
    var mdGradoAfeccion: MdGradoAfeccion,

    @Campo(
        descripcion = "Grado de afección por presencia relativa en la ribera topográfica de especies alóctonas. Margen izquierdo",
        posición = 80,
        sección = "SinRibera"
    )
    var miGradoAfeccionAloctona: MiGradoAfeccionAloctona,

    @Campo(
        descripcion = "Grado de afección por presencia relativa en la ribera topográfica de especies alóctonas. Margen derecho",
        posición = 81,
        sección = "SinRibera"
    )
    var mdGradoAfeccionAloctona: MdGradoAfeccionAloctona,

    @Campo(
        descripcion = "Observaciones",
        posición = 82,
        sección = "SinRibera"
    )
    var afeccionObs : String,
) : Muestra()


@Dao
interface MuestrasSubtramosDao : MuestraDao<MuestraSubtramo> {
    @Query("SELECT id FROM muestras_subtramos WHERE idTramo = :idTramo")
    abstract override fun obtenerIds(idTramo: Long): List<Long>

    @Query("SELECT codigo FROM muestras_subtramos WHERE idTramo = :idTramo")
    abstract override fun obtenerCodigos(idTramo: Long): List<String>

    @Insert
    abstract override fun añadir(muestra: MuestraSubtramo)

    @Query("SELECT id FROM muestras_subtramos WHERE codigo = :codigo")
    abstract override fun findId(codigo: String): Long

    @Update
    abstract override fun modificar (muestra: MuestraSubtramo)

    @Query("SELECT * FROM muestras_subtramos WHERE id = :id")
    abstract override fun cargar(id: Long): MuestraSubtramo

    @Query("SELECT id , codigo  FROM muestras_subtramos WHERE idTramo = :idTramo")
    abstract override fun cargarTodas(idTramo: Long): List<Identificador>

    @Query("SELECT * FROM muestras_subtramos")
    abstract override fun cargarTodasTodas(): List<MuestraSubtramo>

    @Query("SELECT id  FROM muestras_subtramos WHERE idTramo = :idTramo")
    fun obtenerIdsDeMuestrasSubtramosQuePertenezcanAUnTramo(idTramo: Long): List<Long>

    @Query("DELETE FROM muestras_subtramos WHERE id IN (:ids)")
    fun eliminar (ids : List<Long>)

    @Query("DELETE FROM muestras_subtramos WHERE id = :id")
    override fun eliminar (id : Long)
}