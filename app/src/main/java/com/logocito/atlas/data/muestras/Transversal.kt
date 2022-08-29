package com.logocito.atlas.data.muestras

import androidx.room.*
import com.logocito.atlas.data.*
import com.logocito.atlas.R

//import com.logocito.atlas.data.Muestra
enum class HusoHorario{
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
    TREINTAIUNO,
}

enum class Acceso{
    MI,
    MD,
}

enum class CanalLateral{
    SI,
    NO,
}

enum class Rejillas{
    SI,
    NO,
}

enum class Colmatado{
    SI,
    NO,
}

enum class AscensoDifAccesso{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class AscensoDifPaso{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class AscensoAusenciaLlamada{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class AscensoPresenciaTurbulencias{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class AscensoSuperficieRugosa{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class AscensoPresenciaDescansaderos{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class DescensoFormacionEmbalse{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class DescensoDificultadPaso{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class DescensoPresenciaCanal{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class DescensoPresenciaCanalDer{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class PresenciaDisp{
    SI,
    NO,
}

enum class AccesibleAguasAbajo{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class AccesibleAguasArriba{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class EfectoLlamada{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class Estanques{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class PozaRemonte{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class Flujo{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class DiscontinuidadesObstáculos{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class DsAccesibilidad{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class DsEfectoLlamada{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class DsFlujo{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

enum class DsDiscObs{
    SI,
    NO,
    @Opcion(
        descripcion = "Dependiendo de las condiciones del caudal"
    )
    DEPENDIENDO,
}

@Entity(
    tableName ="muestras_transversales"
)
data class MuestraTransversal (
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    override var codigo : String,
    override val idTramo:Long,
    var longitud : Int,
    var coordenadaX : Long,
    var coordenadaY : Long,

    @Campo(
        descripcion = "Fecha",
        posición = 1,
        sección = "DatosGenerales"
    )
    var fechaTransversal : String,

    @Campo(
        descripcion = "Hora",
        posición = 2,
        sección = "DatosGenerales"
    )
    var horaTransversal : String,

    @Campo(
        descripcion = "Huso",
        posición = 3,
        sección = "DatosGenerales"
    )
    var husoHorario: HusoHorario,

    @Campo(
        descripcion = "Coordenadas UTM X",
        posición = 4,
        sección = "DatosGenerales"
    )
    var coordenadasX : String,

    @Campo(
        descripcion = "Coordenadas UTM Y",
        posición = 5,
        sección = "DatosGenerales"
    )
    var coordenadasY : String,

    @Campo(
        descripcion = "Acceso",
        posición = 6,
        sección = "DatosGenerales"
    )
    var acceso: Acceso,

    @Campo(
        descripcion = "Descripción del acceso",
        posición = 7,
        sección = "DatosGenerales"
    )
    var descripcionAcceso : String,

    @Campo(
        descripcion = "Regadío",
        posición = 8,
        sección = "Uso"
    )
    var regadio : Boolean,

    @Campo(
        descripcion = "Ganadero",
        posición = 9,
        sección = "Uso"
    )
    var ganadero : Boolean,

    @Campo(
        descripcion = "Consumo humano",
        posición = 10,
        sección = "Uso"
    )
    var consumoHumano : Boolean,

    @Campo(
        descripcion = "Industrial",
        posición = 11,
        sección = "Uso"
    )
    var industrial : Boolean,

    @Campo(
        descripcion = "Acuicultura",
        posición =12,
        sección = "Uso"
    )
    var acuicultura : Boolean,

    @Campo(
        descripcion = "Transvases hidroeléctricos",
        posición =13,
        sección = "Uso"
    )
    var transvaseHidro : Boolean,

    @Campo(
        descripcion = "desconocido",
        posición =14,
        sección = "Uso"
    )
    var desconocido : Boolean,

    @Campo(
        descripcion = "¿Existe canal lateral?",
        posición =15,
        sección = "Uso"
    )
    var canalLateral: CanalLateral,

    @Campo(
        descripcion = "¿El canal presenta rejillas?",
        posición =16,
        sección = "Uso"
    )
    var rejillas: Rejillas,

    @Campo(
        descripcion = "En proyecto",
        posición =17,
        sección = "Conservacion"
    )
    var proyecto : Boolean,

    @Campo(
        descripcion = "En construcción",
        posición =18,
        sección = "Conservacion"
    )
    var construccion : Boolean,

    @Campo(
        descripcion = "En explotación",
        posición =19,
        sección = "Conservacion"
    )
    var explotacion : Boolean,

    @Campo(
        descripcion = "Puesta fuera de servicio",
        posición =20,
        sección = "Conservacion"
    )
    var fueraServicio : Boolean,

    @Campo(
        descripcion = "Abandonado en buen estado",
        posición =21,
        sección = "Conservacion"
    )
    var abandonado : Boolean,

    @Campo(
        descripcion = "Abandonado en ruinas",
        posición =22,
        sección = "Conservacion"
    )
    var abandonadoRuinas : Boolean,

    @Campo(
        descripcion = "Demolido",
        posición =23,
        sección = "Conservacion"
    )
    var demolido : Boolean,

    @Campo(
        descripcion = "Otros",
        posición =24,
        sección = "Conservacion"
    )
    var otrosConservacionE : Boolean,

    @Campo(
        descripcion = "¿El azud está colmatado?",
        posición =25,
        sección = "Conservacion"
    )
    var colmatado : Colmatado,

    @Campo(
        descripcion = "Salto vertical",
        posición =26,
        sección = "TipoObstaculo",
        imagen = R.drawable.salto_vertical,
    )
    var saltoVertical : Boolean,

    @Campo(
        descripcion = "Altura del obstáculo (H) en metros",
        posición =27,
        sección = "TipoObstaculo"
    )
    var saltoAltObs : Float,

    @Campo(
        descripcion = "Altura del salto (S) en metros",
        posición =28,
        sección = "TipoObstaculo"
    )
    var saltoAlt : Float,

    @Campo(
        descripcion = "Profundidad de la poza a pie del azud (P) en metros",
        posición =29,
        sección = "TipoObstaculo"
    )
    var saltoProfPoza : Float,

    @Campo(
        descripcion = "Anchura en coronación (W) en metros",
        posición =30,
        sección = "TipoObstaculo"
    )
    var saltoAnchuraCorona : Float,

    @Campo(
        descripcion = "Longitud en coronacion (L) en metros",
        posición =31,
        sección = "TipoObstaculo"
    )
    var saltoLongCorona : Float,

    @Campo(
        descripcion = "Altura de la lámina en coronación (A) en metros",
        posición =32,
        sección = "TipoObstaculo"
    )
    var saltoLaminaCorona : Float,

    @Campo(
        descripcion = "Paso entubado",
        posición =33,
        sección = "TipoObstaculo",
        imagen = R.drawable.paso_entubado,
    )
    var pasoEntubado : Boolean,

    @Campo(
        descripcion = "Velocidad de la corriente (V) en metros/segundo",
        posición =34,
        sección = "TipoObstaculo"
    )
    var pasoVelocidad : Float,

    @Campo(
        descripcion = "Diámetro del paso (D) en metros/segundo",
        posición =35,
        sección = "TipoObstaculo"
    )
    var pasoDiametro : Float,

    @Campo(
        descripcion = "Altura de la lámina en el paso (A) en metros",
        posición =36,
        sección = "TipoObstaculo"
    )
    var pasoAltura : Float,

    @Campo(
        descripcion = "Longitudo del paso (I) en metros",
        posición =37,
        sección = "TipoObstaculo"
    )
    var pasoLongitud : Float,

    @Campo(
        descripcion = "Longitudo en coronación (L) en metros",
        posición =38,
        sección = "TipoObstaculo"
    )
    var pasoLongitudCorona : Float,

    @Campo(
        descripcion = "Paso sobre paramento",
        posición =39,
        sección = "TipoObstaculo",
        imagen = R.drawable.paso_sobre_paramento,
    )
    var pasoParamento : Boolean,

    @Campo(
        descripcion = "Altura del obstáculo (H) en metros",
        posición =40,
        sección = "TipoObstaculo"
    )
    var paramentoAlturaObs : Float,

    @Campo(
        descripcion = "Altura del salto (S) en metros",
        posición =41,
        sección = "TipoObstaculo"
    )
    var paramentoAlturaSal : Float,

    @Campo(
        descripcion = "Profundidad de la poza al pie del azud (P) en metros",
        posición =42,
        sección = "TipoObstaculo"
    )
    var paramentoProfundidad : Float,

    @Campo(
        descripcion = "Distancia a coronacion (DC) en metros",
        posición =43,
        sección = "TipoObstaculo"
    )
    var paramentoDistanciaCoronacion : Float,

    @Campo(
        descripcion = "Anchura en coronacion (w) en metros",
        posición =44,
        sección = "TipoObstaculo"
    )
    var paramentoAnchuraCoronacion : Float,

    @Campo(
        descripcion = "Longitud en coronacion (L) en metros",
        posición =45,
        sección = "TipoObstaculo"
    )
    var paramentoLongitudCoronacion : Float,

    @Campo(
        descripcion = "Altura de la lámina sobre el paramento (A) en metros",
        posición =46,
        sección = "TipoObstaculo"
    )
    var paramentoAlturaLamPar : Float,

    @Campo(
        descripcion = "Pendiente del paramento (%)",
        posición =47,
        sección = "TipoObstaculo"
    )
    var paramentoPendiente : Float,

    @Campo(
        descripcion = "Velocidad de la corriente sobre el paramento (V) en metros/segundo",
        posición =48,
        sección = "TipoObstaculo"
    )
    var paramentoVel : Float,

    @Campo(
        descripcion = "Obstáculo Mixto (Marcar aquellos otros obstáculos que lo conforman)",
        posición =49,
        sección = "TipoObstaculo"
    )
    var mixto : Boolean,

    @Campo(
        descripcion = "Cruce con vial (Marcar el tipo de obstáculo que genera)",
        posición =50,
        sección = "TipoObstaculo"
    )
    var cruceVial : Boolean,

    @Campo(
        descripcion = "Longitud del remanso generado por el obstáculo (m)",
        posición =51,
        sección = "Remanso"
    )
    var remansoLongitud : Float,

    @Campo(
        descripcion = "Calado medio del cauce en el remanso (m)",
        posición =52,
        sección = "Remanso"
    )
    var remansoCaladoRem : Float,

    @Campo(
        descripcion = "Calado medio del cauce antes del remanso (m)",
        posición =53,
        sección = "Remanso"
    )
    var remansoCaladoAnt : Float,

    @Campo(
        descripcion = "Anchura media del cauce en el remanso (m)",
        posición =54,
        sección = "Remanso"
    )
    var remansoAnchRem : Float,

    @Campo(
        descripcion = "Anchura media del cauce antes del remanso (m)",
        posición =55,
        sección = "Remanso"
    )
    var remansoAnchAnt : Float,

    @Campo(
        descripcion = "Dificultad de acceso a pie del obstáculo",
        posición =56,
        sección = "EfectoBarreraAs"
    )
    var ascensoDifAccesso: AscensoDifAccesso,

    @Campo(
        descripcion = "Dificultad de paso en obstáculos entubados",
        posición =57,
        sección = "EfectoBarreraAs"
    )
    var ascensoDifPaso: AscensoDifPaso,

    @Campo(
        descripcion = "Ausencia de llamada en la zona de posible franqueo",
        posición =58,
        sección = "EfectoBarreraAs"
    )
    var ascensoAusenciaLlamada: AscensoAusenciaLlamada,

    @Campo(
        descripcion = "Presencia de turbulencias importantes",
        posición =59,
        sección = "EfectoBarreraAs"
    )
    var ascensoPresenciaTurbulencias: AscensoPresenciaTurbulencias,

    @Campo(
        descripcion = "Superficie rugosa o irregular (pendientes inferiores al 45%)",
        posición =60,
        sección = "EfectoBarreraAs"
    )
    var ascensoSuperficieRugosa: AscensoSuperficieRugosa,

    @Campo(
        descripcion = "Presencia de descansaderos, cambios de pendiente u obstáculos formando descansos",
        posición =61,
        sección = "EfectoBarreraAs"
    )
    var ascensoPresenciaDescansaderos: AscensoPresenciaDescansaderos,

    @Campo(
        descripcion = "Formación de embalse o dificultad de identificación del paso (ausencia de un gradiente claro de velocidad en la zona encauzada",
        posición =62,
        sección = "EfectoBarreraDs"
    )
    var descensoFormacionEmbalse: DescensoFormacionEmbalse,

    @Campo(
        descripcion = "Dificultad de paso en obstáculos entubados",
        posición =63,
        sección = "EfectoBarreraDs"
    )
    var descensoDificultadPaso: DescensoDificultadPaso,

    @Campo(
        descripcion = "Presencia de canal de derivacón con rejillas preavias a molino, turbina, toma de riego (considerando los umbrales de luz de paso para cada grupo",
        posición =64,
        sección = "EfectoBarreraDs"
    )
    var descensoPresenciaCanal: DescensoPresenciaCanal,

    @Campo(
        descripcion = "Presencia de canal de derivacón SIN rejillas preavias a molino, turbina, toma de riego (considerando los umbrales de luz de paso para cada grupo",
        posición =65,
        sección = "EfectoBarreraDs"
    )
    var descensoPresenciaCanalDer: DescensoPresenciaCanalDer,

    @Campo(
        descripcion = "Presencia de dispositivos de paso o escalas para el ascenso/descenso de las especies piscícolas",
        posición =66,
        sección = "DispositivosExistentes"
    )
    var presenciaDisp: PresenciaDisp,

    @Campo(
        descripcion = "Estanques sucesivos",
        posición =67,
        sección = "DispositivosExistentes",
        imagen = R.drawable.estanques_sucesivos,
    )
    var estanquesSucesivos: Boolean,

    @Campo(
        descripcion = "Rampas de piedras",
        posición =68,
        sección = "DispositivosExistentes",
        imagen = R.drawable.rampas_piedra_buen,
    )
    var rampasPiedras : Boolean,

    @Campo(
        descripcion = "Ralentizadores",
        posición =69,
        sección = "DispositivosExistentes",
        imagen = R.drawable.ralentizadores,
    )
    var ralentizadores : Boolean,

    @Campo(
        descripcion = "Canal lateral",
        posición =70,
        sección = "DispositivosExistentes",
        imagen = R.drawable.canal_lateral,
    )
    var canalCanal : Boolean,

    @Campo(
        descripcion = "Pre-presas",
        posición =71,
        sección = "DispositivosExistentes",
        imagen = R.drawable.pre_presas,
    )
    var prePresas : Boolean,

    @Campo(
        descripcion = "Accesibilidad del dispositivo de paso al cauce aguas abajo",
        posición =72,
        sección = "EfectoAscenso"
    )
    var accesibleAguasAbajo : AccesibleAguasAbajo,

    @Campo(
        descripcion = "Accesibilidad del dispositivo de paso al cauce aguas arriba",
        posición =73,
        sección = "EfectoAscenso"
    )
    var accesibleAguasArriba: AccesibleAguasArriba,

    @Campo(
        descripcion = "Efecto llamada al pie de la estructura de remonte",
        posición =74,
        sección = "EfectoAscenso"
    )
    var efectoLlamada: EfectoLlamada,

    @Campo(
        descripcion = "Estanques (Longitud mínima pex*3, anchura mínima pes*2)",
        posición =75,
        sección = "EfectoAscenso"
    )
    var estanques: Estanques,

    @Campo(
        descripcion = "Poza de remonte al pie de la estructura",
        posición =76,
        sección = "EfectoAscenso"
    )
    var pozaRemonte: PozaRemonte,

    @Campo(
        descripcion = "Flujo hidráulico para el paso de los peces",
        posición =77,
        sección = "EfectoAscenso"
    )
    var flujo: Flujo,

    @Campo(
        descripcion = "Presencia de discontinuidades u obstáculos en la escala",
        posición =78,
        sección = "EfectoAscenso"
    )
    var discontinuidadesObstáculos: DiscontinuidadesObstáculos,

    @Campo(
        descripcion = "Accesibilidad a la estructura aguas arriba",
        posición =79,
        sección = "EfectoDescenso"
    )
    var dsAccesibilidad: DsAccesibilidad,

    @Campo(
        descripcion = "Efecto llamada en descenso",
        posición =80,
        sección = "EfectoDescenso"
    )
    var dsEfectoLlamada: DsEfectoLlamada,

    @Campo(
        descripcion = "Flujo hidráulico idóneo para el paso de los peces",
        posición =81,
        sección = "EfectoDescenso"
    )
    var dsFlujo: DsFlujo,

    @Campo(
        descripcion = "Presencia de discontimuidades u obstáculos en la escala",
        posición =82,
        sección = "EfectoDescenso"
    )
    var dsDiscObs: DsDiscObs,

    @Campo(
        descripcion = "Observaciones generales",
        posición =125,
        sección = "Observaciones"
    )
    var observacionesGen : String,

) : Muestra()

@Dao
interface MuestrasTransversalesDao : MuestraDao<MuestraTransversal> {

    @Query("SELECT id FROM muestras_transversales WHERE idTramo = :idTramo")
    abstract override fun obtenerIds(idTramo: Long): List<Long>

    @Query("SELECT codigo FROM muestras_transversales WHERE idTramo = :idTramo")
    abstract override fun obtenerCodigos(idTramo: Long) : List<String>

    @Insert
    abstract override fun añadir(muestra: MuestraTransversal)

    @Query ("SELECT id FROM muestras_transversales WHERE codigo = :codigo")
    abstract override fun findId (codigo:String) : Long

    @Update
    abstract override fun modificar (muestra: MuestraTransversal)

    @Query("SELECT id , codigo  FROM muestras_transversales WHERE idTramo = :idTramo")
    abstract override fun cargarTodas(idTramo: Long): List<Identificador>

    @Query("SELECT * FROM muestras_transversales")
    abstract override fun cargarTodasTodas(): List<MuestraTransversal>

    @Query("SELECT * FROM muestras_transversales WHERE id = :id")
    abstract override fun cargar(id: Long): MuestraTransversal

    @Query("SELECT id  FROM muestras_transversales WHERE idTramo = :idTramo")
    fun obtenerIdsDeMuestrasTransversalesQuePertenezcanAUnTramo(idTramo: Long): List<Long>

    @Query("DELETE FROM muestras_transversales WHERE id IN (:ids)")
    fun eliminar (ids : List<Long>)

    @Query("DELETE FROM muestras_transversales WHERE id = :id")
    override fun eliminar (id : Long)
}