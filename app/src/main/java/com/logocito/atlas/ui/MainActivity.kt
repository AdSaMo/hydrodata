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
import com.logocito.atlas.data.muestras.*
import com.logocito.atlas.databinding.ActivityMainBinding
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream

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

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            this.viewModel.exportar()
        }

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
            R.id.action_settings -> true
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

class MainActivityViewModel(application: Application) : AndroidViewModel(application){
    private  var daoMasasAgua : MasaAguaDao
    private  var daoTramos : TramoDao
    private  var daoMuestrasTransversales : MuestrasTransversalesDao
    private var daoMuestrasLongitudinales : MuestrasLongitudinalesDao
    private var daoMuestrasSubtramos: MuestrasSubtramosDao

    //Primera pantalla
    private val _codigosDeMasasDeAgua= MutableLiveData<ArrayList<String>>()
    val codigosDeMasasDeAgua : LiveData<ArrayList<String>> get() = _codigosDeMasasDeAgua

    //Segunda pantalla
    private val _codigoDeMasaAgua= MutableLiveData<String>()
    val codigoDeMasaAgua : LiveData<String> get() = _codigoDeMasaAgua
    private val _codigosDeTramos= MutableLiveData<ArrayList<String>>()
    val codigosDeTramos : LiveData<ArrayList<String>> get() = _codigosDeTramos

    //Tercera pantalla
    private val _codigoDeTramo= MutableLiveData<String>()
    val codigoDeTramo : LiveData<String> get() = _codigoDeTramo
    private val _muestrasLongitudinales= MutableLiveData<ArrayList<Identificador>>()
    val muestrasLongitudinales : LiveData<ArrayList<Identificador>> get() = _muestrasLongitudinales
    private val _muestrasTransversales= MutableLiveData<ArrayList<Identificador>>()
    val muestrasTransversales : LiveData<ArrayList<Identificador>> get() = _muestrasTransversales
    private val _muestrasSubtramos= MutableLiveData<ArrayList<Identificador>>()
    val muestrasSubtramos : LiveData<ArrayList<Identificador>> get() = _muestrasSubtramos

    //Cuarta pantalla: Transversal
    private val _codigoDeMuestraTransversal= MutableLiveData<String>()
    val codigoDeMuestraTransversal : LiveData<String> get() = _codigoDeMuestraTransversal

    //Cuarta pantalla: Longitudinal
    private val _muestraLongitudinal= MutableLiveData<MuestraLongitudinal>()
    val muestraLongitudinal : LiveData<MuestraLongitudinal> get() = _muestraLongitudinal

    //Cuarta pantalla: Subtramo
    private val _muestraSubtramo= MutableLiveData<MuestraSubtramo>()
    val muestraSubtramo : LiveData<MuestraSubtramo> get() = _muestraSubtramo

    init {
        val app = this.getApplication<App>()
        this.daoMasasAgua = app.database.masaAguaDao()
        this._codigosDeMasasDeAgua.postValue(this.daoMasasAgua.obtenerCodigosMasasAgua() as ArrayList<String>)
        this.daoTramos = app.database.tramoDao()
        this.daoMuestrasTransversales = app.database.muestrasTransversalesDao()
        this.daoMuestrasLongitudinales = app.database.muestrasLongitudinalesDao()
        this.daoMuestrasSubtramos = app.database.muestrasSubtramosDao()
    }

    fun añadirMasaAgua (codigo: String){
        val nuevaMasaAgua = MasaAgua(
            0,
            codigo,
        )
        this.daoMasasAgua.añadirMasaAgua(nuevaMasaAgua)
        this._codigosDeMasasDeAgua.value = daoMasasAgua.obtenerCodigosMasasAgua() as ArrayList<String> /* = java.util.ArrayList<kotlin.String> */
    }
    fun añadirMasaAgua (): String{
        val codigo = encontrarCodigoDisponible(this.codigosDeMasasDeAgua.value!!)
        this.añadirMasaAgua(codigo)
        return codigo
    }
    fun añadirTramo() : String{
        val codigo = encontrarCodigoDisponible(this.codigosDeTramos.value!!)
        this.añadirTramo(this.codigoDeMasaAgua.value!!,codigo)
        return codigo
    }
    fun añadirTramo(codigoMasaAgua : String, codigoTramo : String){
        val masaAgua = daoMasasAgua.cargar(codigoMasaAgua)
        val tramo = Tramo(0,codigoTramo,masaAgua.id)
        this.daoTramos.crearTramo(tramo)
        this._codigosDeTramos.value = daoTramos.obtenerCodigosTramos(masaAgua.id) as ArrayList<String>
    }
    fun añadirMuestraTransversal() : String{
        //val codigo = encontrarCodigoDisponible(this.codigosDeMuestrasTransversales.value!!)
        val codigo = "Nueva"
        this.añadirMuestraTransversal(codigo)
        return codigo
    }
    fun añadirMuestraTransversal(codigoMuestraTransversal : String){
        val codigoTramo = this.codigoDeTramo.value!!
        val idTramo = daoTramos.findId(codigoTramo)
        val muestraTransversal = MuestraTransversal(
            id=0,
            idTramo = idTramo,
            codigo = codigoMuestraTransversal,
            longitud = 0,
            coordenadaX = 0,
            coordenadaY = 0,
        )
        this.daoMuestrasTransversales.añadir(muestraTransversal)
        this.cargarTramo(codigoTramo)
    }
    fun añadirMuestraLongitudinal() : String{
        //val codigo = encontrarCodigoDisponible(this.codigosDeMuestrasLongitudinales.value!!)
        val codigo = "Nueva"
        this.añadirMuestraLongitudinal(codigo)
        return codigo
    }
    fun añadirMuestraLongitudinal(codigoMuestraLongitudinal : String){
        val codigoTramo = this.codigoDeTramo.value!!
        val idTramo = daoTramos.findId(codigoTramo)
        val muestraLongitudinal = MuestraLongitudinal(
            id=0,
            idTramo = idTramo,
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

        )
        this.daoMuestrasLongitudinales.añadir(muestraLongitudinal)
        this.cargarTramo(codigoTramo)
    }
    fun añadirMuestraSubtramo() : String{
        //val codigo = encontrarCodigoDisponible(this.codigosDeMuestrasSubtramos.value!!)
        val codigo = "Nuevo"
        this.añadirMuestraSubtramo(codigo)
        return codigo
    }
    fun añadirMuestraSubtramo(codigoMuestraSubtramo : String){
        val codigoTramo = this.codigoDeTramo.value!!
        val idTramo = daoTramos.findId(codigoTramo)
        val muestraSubtramo = MuestraSubtramo(
            id=0,
            idTramo = idTramo,
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

        )
        this.daoMuestrasSubtramos.añadir(muestraSubtramo)
        this.cargarTramo(codigoTramo)
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
                this._codigosDeMasasDeAgua.postValue(this.daoMasasAgua.obtenerCodigosMasasAgua() as ArrayList<String>)

            }
        }
    }
    fun cambiarCodigoTramo(codigoNuevo : String){
        val codigoAnterior = this.codigoDeTramo.value
        if(codigoAnterior != null) {
            if (codigoNuevo != codigoAnterior) {
                val tramoAnterior = daoTramos.cargar(codigoAnterior)
                val tramoNuevo =tramoAnterior.copy(
                    codigo = codigoNuevo,
                )
                this.daoTramos.modificar(tramoNuevo)
                this._codigoDeTramo.postValue(codigoNuevo)
                val idMasaAgua = daoMasasAgua.findId(this.codigoDeMasaAgua.value!!)
                this._codigosDeTramos.postValue(this.daoTramos.obtenerCodigosTramos(idMasaAgua) as ArrayList<String>)

            }
        }
    }

    fun cambiarMuestraLongitudinal(muestra : MuestraLongitudinal){
        this.daoMuestrasLongitudinales.actualizar(muestra)
    }

    fun cambiarMuestraTransversal(muestra : MuestraTransversal){
        this.daoMuestrasTransversales.actualizar(muestra)
    }

    fun cambiarMuestraSubtramo(muestra : MuestraSubtramo){
        this.daoMuestrasSubtramos.actualizar(muestra)
    }

    fun cargarMasaAgua(codigo : String,){
        val idMasaAgua = daoMasasAgua.findId(codigo)
        this._codigoDeMasaAgua.postValue(codigo)
        this._codigosDeTramos.postValue(daoTramos.obtenerCodigosTramos(idMasaAgua) as ArrayList<String> /* = java.util.ArrayList<kotlin.String> */)
    }
    fun cargarTramo(codigo : String,){
        val idTramo = daoTramos.findId(codigo)
        this._codigoDeTramo.postValue(codigo)
        val muestrasTransversales = daoMuestrasTransversales.cargarTodas(idTramo) as ArrayList<Identificador>
        this._muestrasTransversales.postValue(muestrasTransversales)
        val muestrasLongitudinales = daoMuestrasLongitudinales.cargarTodas(idTramo) as ArrayList<Identificador>
        this._muestrasLongitudinales.postValue(muestrasLongitudinales)
        val muestrasSubtramos = daoMuestrasSubtramos.cargarTodas(idTramo) as ArrayList<Identificador>
        this._muestrasSubtramos.postValue(muestrasSubtramos)
    }

    fun cargarMuestraLongitudinal (id : Int){
        val muestraLongitudinal = this.daoMuestrasLongitudinales.cargar(id)
        this._muestraLongitudinal.postValue(muestraLongitudinal)
    }
    /*fun cargarMuestraTransversal (id : Int){
        val muestraTransversal = this.daoMuestrasTransversales.cargar(id)
        this._muestraTransversal.postValue(muestraTransversal)
    }*/
    fun cargarMuestraSubtramo (id : Int){
        val muestraSubtramo = this.daoMuestrasSubtramos.cargar(id)
        this._muestraSubtramo.postValue(muestraSubtramo)
    }

    fun exportar () {
        val workBook = XSSFWorkbook()
        val sheetLongitudinales = workBook.createSheet("Longitudinales")
        this.exportarSheet(this.daoMuestrasLongitudinales, sheetLongitudinales)
        val sheetTransversales = workBook.createSheet("Transversales")
        this.exportarSheet(this.daoMuestrasTransversales, sheetTransversales)
        val sheetSubtramos = workBook.createSheet("Subtramos")
        this.exportarSheet(this.daoMuestrasSubtramos, sheetSubtramos)
        val outputStream = FileOutputStream("atlas.xlsx")
        workBook.write(outputStream)
        workBook.close()
    }

    private inline fun <reified T : Any> exportarSheet(dao : Muestra<T>,sheet : XSSFSheet){
        val campos = sortCampos(T::class)

        val cabeceraSecciones = sheet.createRow(0)
        val cabeceraCampos = sheet.createRow(1)
        cabeceraCampos.createCell(0).setCellValue("Código de muestra")
        cabeceraCampos.createCell(1).setCellValue("Código de tramo")
        cabeceraCampos.createCell(2).setCellValue("Código de masa de agua")
        var columnIndex = 3
        for ((seccion, subcampos) in campos){
            cabeceraSecciones.createCell(columnIndex).setCellValue(seccion)
            for (campo in subcampos){
                cabeceraCampos.createCell(columnIndex).setCellValue(campo.name)
                println(campo.name)
                columnIndex += 1
            }
        }

        var rowIndex = 2
        val muestras = dao.cargarTodasTodas()
        for (muestra in muestras){
            val fila = sheet.createRow(rowIndex)
            var filaColumnIndex = 3
            for ((seccion, subcampos) in campos){
                for (campo in subcampos){
                    val valor = campo.call(muestra) as String
                    fila.createCell(filaColumnIndex).setCellValue(valor)
                    println(valor)
                    filaColumnIndex += 1
                }
            }
            rowIndex += 1
        }
    }
}