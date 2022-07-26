package com.logocito.atlas.ui

//Se importan todas las librerías que vamos a necesitar para nuestra actividad
import android.app.Application
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.logocito.atlas.App
import com.logocito.atlas.R
import com.logocito.atlas.data.*
import com.logocito.atlas.data.Muestra
import com.logocito.atlas.data.MuestraDao
import com.logocito.atlas.data.muestras.*
import com.logocito.atlas.databinding.ActivityMainBinding
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.isSubclassOf

//Definimos nuestra clase principal que contará con métodos que actuarán como hooks para la lógica de la app.
class MainActivity : AppCompatActivity() {
//Las variables que declaramos con "lateinit" serán tenidas en cuenta más adelante cuando se ejecute onCreate.
    private lateinit var appBarConfiguration: AppBarConfiguration
    /* El objeto binding de pertenece a la clase ActivityMainBinding que ha sido importada previamente.
       Este objeto permite establecer una conexión entre los views de los XML y la lógica de la función.*/
    private lateinit var binding: ActivityMainBinding
    //magia
    private val viewModel : MainActivityViewModel by viewModels()

//Primero de nuestros hooks (onCreate). Lo que lleve dentro se ejecutará al iniciar la aplicación.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// El siguiente código llama al método de ActivityMainBinding que posee el objeto binding por pertenecer a esa clase y pasa el
// argumento layoutInflater. El método inflate construye y añade las Views a los diseños de fragmentos o actividades.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
/*Llama mediante el método findNavController al Nav controller de una View concreta que se selecciona por su ID*/
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_export -> {
                this.viewModel.exportar()
                Snackbar.make(this.binding.root, "Datos exportados", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                true
            }
            //R.id.action_settings -> true
            /*R.id.action_delete -> {
                this.viewModel.purge()
                true
            }*/
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}

fun encontrarCodigoDisponible (lista : List<String>) : String {
    var index = 0
    var yaEsta : Boolean
    var codigo : String
    do{
        index = index + 1
        codigo = "Nueva ${index}"
        println(codigo)
        yaEsta = lista.contains(codigo)
    }
    while (yaEsta)
    return codigo
}

data class Elemento (
    val id :Long,
    var codigo: String,
    var markedForDeath: Boolean
)

class MainActivityViewModel(application: Application) : AndroidViewModel(application){
    private var app : App

    private  var daoMasasAgua : MasaAguaDao
    private  var daoTramos : TramoDao
    var daoMuestrasTransversales : MuestrasTransversalesDao
    var daoMuestrasLongitudinales : MuestrasLongitudinalesDao
    var daoMuestrasSubtramos: MuestrasSubtramosDao

    //Primera pantalla
    /*private val _codigosDeMasasDeAgua= MutableLiveData<ArrayList<String>>()
    val codigosDeMasasDeAgua : LiveData<ArrayList<String>> get() = _codigosDeMasasDeAgua*/
    private val _masasDeAgua= MutableLiveData<ArrayList<Identificador>>()
    val masasDeAgua : LiveData<ArrayList<Identificador>> get() = _masasDeAgua

    //Segunda pantalla
    private var idMasaAgua : Long = 0
    private val _codigoDeMasaAgua= MutableLiveData<String>()
    val codigoDeMasaAgua : LiveData<String> get() = _codigoDeMasaAgua
    private val _tramos= MutableLiveData<ArrayList<Identificador>>()
    val tramos : LiveData<ArrayList<Identificador>> get() = _tramos

    //Tercera pantalla
    private var idTramo : Long = 0
    private val _codigoDeTramo= MutableLiveData<String>()
    val codigoDeTramo : LiveData<String> get() = _codigoDeTramo
    private val _muestrasLongitudinales= MutableLiveData<ArrayList<Identificador>>()
    val muestrasLongitudinales : LiveData<ArrayList<Identificador>> get() = _muestrasLongitudinales
    private val _muestrasTransversales= MutableLiveData<ArrayList<Identificador>>()
    val muestrasTransversales : LiveData<ArrayList<Identificador>> get() = _muestrasTransversales
    private val _muestrasSubtramos= MutableLiveData<ArrayList<Identificador>>()
    val muestrasSubtramos : LiveData<ArrayList<Identificador>> get() = _muestrasSubtramos

    //Cuarta pantalla: Transversal
    private val _muestraTransversal= MutableLiveData<MuestraTransversal>()
    val muestraTransversal : LiveData<MuestraTransversal> get() = _muestraTransversal
    /*private val _codigoDeMuestraTransversal= MutableLiveData<String>()
    val codigoDeMuestraTransversal : LiveData<String> get() = _codigoDeMuestraTransversal*/

    //Cuarta pantalla: Longitudinal
    private val _muestraLongitudinal= MutableLiveData<MuestraLongitudinal>()
    val muestraLongitudinal : LiveData<MuestraLongitudinal> get() = _muestraLongitudinal
    /*private val _codigoDeMuestraLongitudinal= MutableLiveData<String>()
    val codigoDeMuestraLongitudinal : LiveData<String> get() = _codigoDeMuestraLongitudinal*/

    //Cuarta pantalla: Subtramo
    private val _muestraSubtramo= MutableLiveData<MuestraSubtramo>()
    val muestraSubtramo : LiveData<MuestraSubtramo> get() = _muestraSubtramo
    /*private val _codigoDeMuestraSubtramo= MutableLiveData<String>()
    val codigoDeMuestraSubtramo : LiveData<String> get() = _codigoDeMuestraSubtramo*/

    init {
        this.app = this.getApplication<App>()
        this.daoMasasAgua = app.database.masaAguaDao()
        //this._codigosDeMasasDeAgua.postValue(this.daoMasasAgua.obtenerCodigosMasasAgua() as ArrayList<String>)
        this.cargarMasasAgua()
        this.daoTramos = app.database.tramoDao()
        this.daoMuestrasTransversales = app.database.muestrasTransversalesDao()
        this.daoMuestrasLongitudinales = app.database.muestrasLongitudinalesDao()
        this.daoMuestrasSubtramos = app.database.muestrasSubtramosDao()
    }

    fun añadirMasaAgua (codigo: String) : Long{
        val nuevaMasaAgua = MasaAgua(
            0,
            codigo,
        )
        val id = this.daoMasasAgua.añadirMasaAgua(nuevaMasaAgua)
        this.cargarMasasAgua()
        return id
    }
    fun añadirMasaAgua (): Long{
        //val codigo = encontrarCodigoDisponible(this.codigosDeMasasDeAgua.value!!)
        val codigo = "Nueva"
        val id = this.añadirMasaAgua(codigo)
        return id
    }
    fun añadirTramo() : String{
        //val codigo = encontrarCodigoDisponible(this.tramos.value!!)
        val codigo = "Nuevo"
        this.añadirTramo(this.codigoDeMasaAgua.value!!,codigo)
        return codigo
    }
    fun añadirTramo(codigoMasaAgua : String, codigoTramo : String){
        val masaAgua = daoMasasAgua.cargar(codigoMasaAgua)
        val tramo = Tramo(0,codigoTramo,masaAgua.id)
        this.daoTramos.crearTramo(tramo)
        this._tramos.value = daoTramos.cargarTodos(masaAgua.id) as ArrayList<Identificador>
    }
    fun añadirMuestraTransversal() : String{
        //val codigo = encontrarCodigoDisponible(this.codigosDeMuestrasTransversales.value!!)
        val codigo = "Nueva"
        this.añadirMuestraTransversal(codigo)
        return codigo
    }
    fun añadirMuestraTransversal(codigoMuestraTransversal : String){
        val muestraTransversal = MuestraTransversal(
            id=0,
            idTramo = this.idTramo,
            codigo = codigoMuestraTransversal,
            longitud = 0,
            coordenadaX = 0,
            coordenadaY = 0,
            acceso = Acceso.MD,
            descripcionAcceso = "",
            regadio = false,
            ganadero = false,
            consumoHumano = false,
            industrial = false,
            acuicultura = false,
            transvaseHidro = false,
            desconocido = false,
            canalLateral = CanalLateral.NO,
            rejillas = Rejillas.NO,
            proyecto = false,
            construccion = false,
            explotacion = false,
            fueraServicio = false,
            abandonado = false,
            abandonadoRuinas = false,
            demolido = false,
            otrosConservacionE = false,
            colmatado = Colmatado.NO,
            saltoVertical = false,
            saltoAlt = 0.0F,
            saltoProfPoza = 0.0F,
            saltoAnchuraCorona = 0.0F,
            saltoLongCorona = 0.0F,
            saltoLaminaCorona = 0.0F,
            pasoEntubado = false,
            pasoVelocidad = 0.0F,
            pasoDiametro = 0.0F,
            pasoAltura = 0.0F,
            pasoLongitud = 0.0F,
            pasoLongitudCorona = 0.0F,
            pasoParamento = false,
            paramentoAlturaObs = 0.0F,
            paramentoAlturaSal = 0.0F,
            paramentoProfundidad = 0.0F,
            paramentoDistanciaCoronacion = 0.0F,
            paramentoAnchuraCoronacion = 0.0F,
            paramentoLongitudCoronacion = 0.0F,
            paramentoAlturaLamPar = 0.0F,
            paramentoPendiente = 0.0F,
            paramentoVel = 0.0F,
            mixto = false,
            cruceVial = false,
            remansoLongitud = 0.0F,
            remansoCaladoRem = 0.0F,
            remansoAnchRem = 0.0F,
            remansoAnchAnt = 0.0F,
            remansoCaladoAnt = 0.0F,
            ascensoDifAccesso = AscensoDifAccesso.NO,
            ascensoDifPaso = AscensoDifPaso.NO,
            ascensoAusenciaLlamada = AscensoAusenciaLlamada.NO,
            ascensoPresenciaTurbulencias = AscensoPresenciaTurbulencias.NO,
            ascensoSuperficieRugosa = AscensoSuperficieRugosa.NO,
            ascensoPresenciaDescansaderos = AscensoPresenciaDescansaderos.NO,
            descensoFormacionEmbalse = DescensoFormacionEmbalse.NO,
            descensoDificultadPaso = DescensoDificultadPaso.NO,
            descensoPresenciaCanal = DescensoPresenciaCanal.NO,
            descensoPresenciaCanalDer = DescensoPresenciaCanalDer.NO,
            presenciaDisp = PresenciaDisp.NO,
            accesibleAguasAbajo = AccesibleAguasAbajo.NO,
            accesibleAguasArriba = AccesibleAguasArriba.NO,
            efectoLlamada = EfectoLlamada.NO,
            estanques = Estanques.NO,
            pozaRemonte = PozaRemonte.NO,
            flujo = Flujo.NO,
            discontinuidadesObstáculos = DiscontinuidadesObstáculos.NO,
            dsAccesibilidad = DsAccesibilidad.NO,
            dsEfectoLlamada = DsEfectoLlamada.NO,
            dsFlujo = DsFlujo.NO,
            dsDiscObs = DsDiscObs.NO,
            observacionesGen = "",
            saltoAltObs = 0.0F,
            coordenadasX = "",
            coordenadasY = "",
            husoHorario = HusoHorario.TREINTA,
            canalCanal = false,
            estanquesSucesivos = false,
            prePresas = false,
            rampasPiedras = false,
            ralentizadores = false,
            fechaTransversal = "",
            horaTransversal = "",
        )
        this.daoMuestrasTransversales.añadir(muestraTransversal)
        this.cargarTramo(this.idTramo)
    }
    fun añadirMuestraLongitudinal() : String{
        //val codigo = encontrarCodigoDisponible(this.codigosDeMuestrasLongitudinales.value!!)
        val codigo = "Nueva"
        this.añadirMuestraLongitudinal(codigo)
        return codigo
    }
    fun añadirMuestraLongitudinal(codigoMuestraLongitudinal : String){
        val muestraLongitudinal = MuestraLongitudinal(
            id=0,
            idTramo = this.idTramo,
            codigo = codigoMuestraLongitudinal,
            ubicacionObra = UbicacionObra.MARGEN_DEL_RIO,
            margen = Margen.IZQUIERDA,
            tipoObraObservaciones = "",
            estadoConservacion = EstadoConservacion.BUENO,
            tipoObra = TipoObra.ESCOLLERA,
            materialPrincipal = MaterialPrincipal.MADERA,
            funcionObra = FuncionObra.ESTABILIZACION_DE_MARGENES,
            cauce = Cauce.COBERTURA,
            revestimiento = Revestimiento.SIN_REVESTIR,
            observacionesGenerales = "",
            anchoCoronacion = AnchoCoronacion.ENTREUNOYTRES,
            alturaInterior = "",
            alturaExterior = "",
            taludInterior = "",
            taludExterior = "",
            distanciaMediaCauce = "",
            otros = "",
            coorInX = "",
            coorInY = "",
            coorFnX = "",
            coorFnY = "",
            utm = Utm.TREINTA,
            fechaLongitudinal = "",
            horaLongitudinal = "",
        )
        this.daoMuestrasLongitudinales.añadir(muestraLongitudinal)
        this.cargarTramo(this.idTramo)
    }
    fun añadirMuestraSubtramo() : String{
        //val codigo = encontrarCodigoDisponible(this.codigosDeMuestrasSubtramos.value!!)
        val codigo = "Nuevo"
        this.añadirMuestraSubtramo(codigo)
        return codigo
    }
    fun añadirMuestraSubtramo(codigoMuestraSubtramo : String){
        val muestraSubtramo = MuestraSubtramo(
            id=0,
            idTramo = this.idTramo,
            codigo = codigoMuestraSubtramo,
            inicioX = "",
            finX = "",
            inicioY = "",
            finY = "",
            longiSubtramo = "",
            anchoCauce = "",
            anchoTopo = "",
            anchoFuncional = "",
            accesiblidad = "",
            esRoca = false,
            esColuvial = false,
            esAluvial = false,
            tamañoDominanteRocoso = TamañoDominanteRocoso.ENTRECUARENTAYSETENTA,
            tamañoDominanteGrueso = TamañoDominanteGrueso.ENTRESETENTAYNOVENTA,
            tamañoDominanteFino = TamañoDominanteFino.ENTRECUARENTAYSETENTA,
            tamañoDominanteLodos = TamañoDominanteLodos.ENTRECUARENTAYSETENTA,
            clasificacionSedimentos = ClasificacionSedimentos.EFECTIVA,
            pozaMarmita = PozaMarmita.PRESENTE,
            saltoPoza = SaltoPoza.PRESENTE,
            rapidoPoza = RapidoPoza.PRESENTE,
            rapidoRemanso = RapidoRemanso.PRESENTE,
            rapidoContinuo = RapidoContinuo.PRESENTE,
            grada = Grada.PRESENTE,
            rampa = Rampa.PRESENTE,
            tabla = Tabla.PRESENTE,
            principalModificada = PrincipalModificada.SI,
            otra = "",
            barraMarginal = BarraMarginal.PRINCIPAL,
            barraCauce = BarraCauce.PRESENTE,
            isla = Isla.PRESENTE,
            canalSecundario = CanalSecundario.PRESENTE,
            canalCrecida = CanalCrecida.PRESENTE,
            surco = Surco.PRESENTE,
            cauceAbandonado = CauceAbandonado.PRESENTE,
            sinNatural = SinNatural.PRESENTE,
            otraFormas = "",
            movilidadSedimentos = MovilidadSedimentos.EFECTIVA,
            sintomasIncision = SintomasIncision.SI,
            diferenciaAltura = "",
            gradoAccesibilidad = "",
            remociones = false,
            alter = false,
            descripcionAlter = "",
            descripcionRemociones = "",
            detritosVegetales = DetritosVegetales.MENORCUARENTA,
            orillasVegetadas = OrillasVegetadas.MENORCUARENTA,
            macrofitosSumergidos = MacrofitosSumergidos.MENORCUARENTA,
            obsHabitats = "",
            miConectEco = "",
            mdConectEco = "",
            gConectEco = "",
            miCauceSombreado = "",
            mdCauceSombreado = "",
            gCauceSombreado = "",
            miConectTransv = "",
            mdConectTransv = "",
            gConectTransv = "",
            gradoContacto = GradoContacto.ESCASA,
            gradoContactoObservaciones = "",
            formacionDominante = "",
            formacionAcompañante = "",
            eRegresiva = "",
            eAutoctonas = "",
            eAloctonas = "",
            naturalidad = "",
            especies = "",
            categoriaDiversidad = CategoriaDiversidad.BAJA,
            categoriaDiversidadObs = "",
            funcionalidadRibera = "",
            zonTransversal = "",
            zonPermeable = "",
            calidadObservaciones = "",
            miAnchuraMedia = "",
            mdAnchuraMedia = "",
            miGradoAfeccion = MiGradoAfeccion.BAJA,
            mdGradoAfeccion = MdGradoAfeccion.BAJA,
            miGradoAfeccionAloctona = MiGradoAfeccionAloctona.BAJA,
            mdGradoAfeccionAloctona = MdGradoAfeccionAloctona.BAJA,
            afeccionObs = "",
            fechaSubtramo = "",
            horaSubtramo = "",
        )
        this.daoMuestrasSubtramos.añadir(muestraSubtramo)
        this.cargarTramo(this.idTramo)
    }
    fun cambiarCodigoMasaAgua(codigoNuevo : String){
        val codigoAnterior = this.codigoDeMasaAgua.value
        if(codigoAnterior != null) {
            if (codigoNuevo != codigoAnterior) {
                println(codigoAnterior)
                println(codigoNuevo)
                val masaAguaAnterior = daoMasasAgua.cargar(codigoAnterior)
                val masaAguaNueva = masaAguaAnterior.copy(
                    codigo = codigoNuevo,
                )
                this.daoMasasAgua.modificar(masaAguaNueva)
                this._codigoDeMasaAgua.postValue(codigoNuevo)
                //this.cargarMasaAgua(codigoNuevo)
                //daoMasasAgua.eliminar(masaAguaAnterior)
                this.cargarMasasAgua()

            }
        }
    }

    fun cambiarCodigoTransversal(id : Long, codigoNuevo : String){
        val muestraTransversal = this.muestraTransversal.value!!
        muestraTransversal.codigo = codigoNuevo
        this.daoMuestrasTransversales.modificar(muestraTransversal)
        this.cargarMuestraTransversal(id)
    }

    fun cambiarCodigoLongitudinal(id : Long, codigoNuevo : String){
        val muestraLongitudinal = this.muestraLongitudinal.value!!
        muestraLongitudinal.codigo = codigoNuevo
        this.daoMuestrasLongitudinales.modificar(muestraLongitudinal)
        this.cargarMuestraLongitudinal(id)
    }

    fun cambiarCodigoSubtramo(id : Long, codigoNuevo : String){
        val muestraSubtramo = this.muestraSubtramo.value!!
        muestraSubtramo.codigo = codigoNuevo
        this.daoMuestrasSubtramos.modificar(muestraSubtramo)
        this.cargarMuestraSubtramo(id)
    }

    fun cambiarCodigoTramo(codigoNuevo : String){
        val codigoAnterior = this.codigoDeTramo.value!!
        if (codigoNuevo != codigoAnterior) {
            val tramo = daoTramos.cargar(this.idTramo)
            tramo.codigo = codigoNuevo
            this.daoTramos.modificar(tramo)
            this._codigoDeTramo.postValue(codigoNuevo)
            val idMasaAgua = daoMasasAgua.findId(this.codigoDeMasaAgua.value!!)
            this._tramos.postValue(this.daoTramos.cargarTodos(idMasaAgua) as ArrayList<Identificador>)
        }
    }

    fun cambiarMuestraLongitudinal(muestra : MuestraLongitudinal){
        this.daoMuestrasLongitudinales.modificar(muestra)
    }

    fun cambiarMuestraTransversal(muestra : MuestraTransversal){
        this.daoMuestrasTransversales.modificar(muestra)
    }

    fun cambiarMuestraSubtramo(muestra : MuestraSubtramo){
        this.daoMuestrasSubtramos.modificar(muestra)
    }

    fun cargarMasaAgua(idMasaAgua: Long){
        this.idMasaAgua = idMasaAgua
        val codigo = this.daoMasasAgua.obtenerCodigo(idMasaAgua)
        this._codigoDeMasaAgua.postValue(codigo)
        this._tramos.postValue(daoTramos.cargarTodos(idMasaAgua) as ArrayList<Identificador> /* = java.util.ArrayList<kotlin.String> */)
    }

    fun cargarMasasAgua(){
        this._masasDeAgua.postValue(
            this.daoMasasAgua.cargarTodas()
            as ArrayList<Identificador> /* = java.util.ArrayList<kotlin.Pair<kotlin.String, kotlin.Boolean>> */
        )

    }

    fun cargarTramo(idTramo : Long){
        this.idTramo = idTramo
        val codigo = this.daoTramos.obtenerCodigo(idTramo)
        this._codigoDeTramo.postValue(codigo)
        val muestrasTransversales = daoMuestrasTransversales.cargarTodas(idTramo) as ArrayList<Identificador>
        this._muestrasTransversales.postValue(muestrasTransversales)
        val muestrasLongitudinales = daoMuestrasLongitudinales.cargarTodas(idTramo) as ArrayList<Identificador>
        this._muestrasLongitudinales.postValue(muestrasLongitudinales)
        val muestrasSubtramos = daoMuestrasSubtramos.cargarTodas(idTramo) as ArrayList<Identificador>
        this._muestrasSubtramos.postValue(muestrasSubtramos)
    }

    fun cargarTramo(){
        this.cargarTramo(this.idTramo)
    }

    fun cargarMuestraLongitudinal (id : Long){
        val muestraLongitudinal = this.daoMuestrasLongitudinales.cargar(id)
        this._muestraLongitudinal.postValue(muestraLongitudinal)
    }
    fun cargarMuestraTransversal (id : Long) {
        val muestraTransversal = this.daoMuestrasTransversales.cargar(id)
        this._muestraTransversal.postValue(muestraTransversal)
    }
    fun cargarMuestraSubtramo (id : Long){
        val muestraSubtramo = this.daoMuestrasSubtramos.cargar(id)
        this._muestraSubtramo.postValue(muestraSubtramo)
    }

    fun eliminarMasaAgua (id: Long){
        val tramos = this.daoTramos.obtenerIdsDeTramosQuePertenezcanAUnaMasaDeAgua(id)
        this.eliminarTramos(tramos)
        this.daoMasasAgua.eliminar(id)
        this.cargarMasasAgua()
    }

    inline fun <reified T : Muestra> eliminarMuestra(id : Long){
        val dao = when (T :: class){
            MuestraLongitudinal :: class -> this.daoMuestrasLongitudinales
            MuestraTransversal :: class -> this.daoMuestrasTransversales
            MuestraSubtramo :: class -> this.daoMuestrasSubtramos
            else -> {
                TODO("Amor")}
        }
        dao.eliminar(id)
        this.cargarTramo()
    }

    fun eliminarTramo (id: Long){
        val muestrasLongitudinales = this.daoMuestrasLongitudinales.obtenerIdsDeMuestrasLongitudinalesQuePertenezcanAUnTramo(id)
        this.daoMuestrasLongitudinales.eliminar(muestrasLongitudinales)
        val muestrasTransversales = this.daoMuestrasTransversales.obtenerIdsDeMuestrasTransversalesQuePertenezcanAUnTramo(id)
        this.daoMuestrasTransversales.eliminar(muestrasTransversales)
        val muestrasSubtramos = this.daoMuestrasSubtramos.obtenerIdsDeMuestrasSubtramosQuePertenezcanAUnTramo(id)
        this.daoMuestrasSubtramos.eliminar(muestrasSubtramos)
        this.daoTramos.eliminar(id)
        this.cargarMasaAgua(this.idMasaAgua)
    }

    fun eliminarTramos (ids: List<Long>){
        for (tramo in ids){
            val muestrasLongitudinales = this.daoMuestrasLongitudinales.obtenerIdsDeMuestrasLongitudinalesQuePertenezcanAUnTramo(tramo)
            this.daoMuestrasLongitudinales.eliminar(muestrasLongitudinales)
            val muestrasTransversales = this.daoMuestrasTransversales.obtenerIdsDeMuestrasTransversalesQuePertenezcanAUnTramo(tramo)
            this.daoMuestrasTransversales.eliminar(muestrasTransversales)
            val muestrasSubtramos = this.daoMuestrasSubtramos.obtenerIdsDeMuestrasSubtramosQuePertenezcanAUnTramo(tramo)
            this.daoMuestrasSubtramos.eliminar(muestrasSubtramos)
        }
        this.daoTramos.eliminar(ids)
    }

    fun exportar () {
        val workBook = XSSFWorkbook()
        val sheetLongitudinales = workBook.createSheet("Longitudinales")
        this.exportarSheet(this.daoMuestrasLongitudinales, sheetLongitudinales)
        val sheetTransversales = workBook.createSheet("Transversales")
        this.exportarSheet(this.daoMuestrasTransversales, sheetTransversales)
        val sheetSubtramos = workBook.createSheet("Subtramos")
        this.exportarSheet(this.daoMuestrasSubtramos, sheetSubtramos)
        val path = this.app.getExternalFilesDir(null)
        val outputStream = FileOutputStream("${path}/atlas.xlsx")
        workBook.write(outputStream)
        workBook.close()
    }

    private inline fun <reified T : Muestra > exportarSheet(dao : MuestraDao<T>, sheet : XSSFSheet)
    {
        val campos = sortCampos(T::class)

        val cabeceraSecciones = sheet.createRow(0)
        val cabeceraCampos = sheet.createRow(1)
        cabeceraCampos.createCell(2).setCellValue("Código de muestra")
        cabeceraCampos.createCell(1).setCellValue("Código de tramo")
        cabeceraCampos.createCell(0).setCellValue("Código de masa de agua")
        var columnIndex = 3
        for ((seccion, subcampos) in campos){
            cabeceraSecciones.createCell(columnIndex).setCellValue(seccion)
            val cellRangeAddress = CellRangeAddress(
                0,
                0,
                columnIndex,
                columnIndex + 1,
            )
            for (campo in subcampos){
                val campoData = campo.findAnnotation<Campo>()
                cabeceraCampos.createCell(columnIndex).setCellValue(campoData?.descripcion ?: campo.name)
                columnIndex += 1
            }
            cellRangeAddress.lastColumn = columnIndex -1
            if(cellRangeAddress.lastColumn > cellRangeAddress.firstColumn){
                sheet.addMergedRegion(cellRangeAddress)
            }
        }

        var rowIndex = 2
        val muestras = dao.cargarTodasTodas()
        for (muestra in muestras){
            val fila = sheet.createRow(rowIndex)
            val tramo = this.daoTramos.cargar(muestra.idTramo)
            val codigoMasaAgua =this.daoMasasAgua.obtenerCodigo(tramo.masaAgua)
            fila.createCell(0).setCellValue(codigoMasaAgua)
            //val codigoTramo = this.daoTramos.obtenerCodigo(muestra.idTramo)
            fila.createCell(1).setCellValue(tramo.codigo)
            fila.createCell(2).setCellValue(muestra.codigo)
            var filaColumnIndex = 3
            for ((seccion, subcampos) in campos){
                for (campo in subcampos){
                    var valor : String
                    val campoTipo = campo.returnType.classifier as KClass<*>
                    val campoEsUnEnum = campoTipo.isSubclassOf(Enum::class)
                    if (campoEsUnEnum){
                        valor = (campo.call(muestra) as Enum<*>).toString()

                    } else if (campoTipo == kotlin.Float::class){
                        valor = (campo.call(muestra) as Float).toString()
                    } else if(campoTipo == kotlin.Boolean::class){
                        valor = when(campo.call(muestra) as Boolean){
                            true -> "TRUE"
                            false -> "FALSE"
                        }

                    }else{
                        valor = campo.call(muestra) as String
                    }

                    fila.createCell(filaColumnIndex).setCellValue(valor)
                    println(valor)
                    filaColumnIndex += 1
                }
            }
            rowIndex += 1
        }
    }
}