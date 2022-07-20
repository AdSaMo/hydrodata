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
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.logocito.atlas.App
import com.logocito.atlas.R
import com.logocito.atlas.data.MasaAgua
import com.logocito.atlas.data.MasaAguaDao
import com.logocito.atlas.data.Tramo
import com.logocito.atlas.data.TramoDao
import com.logocito.atlas.data.muestras.MuestraTransversal
import com.logocito.atlas.data.muestras.MuestrasTransversalesDao
import com.logocito.atlas.databinding.ActivityMainBinding
//Definimos nuestra clase principal que contará con métodos que actuarán como hooks para la lógica de la app.
class MainActivity : AppCompatActivity() {
//Las variables que declaramos con "lateinit" serán tenidas en cuenta más adelante cuando se ejecute onCreate.
    private lateinit var appBarConfiguration: AppBarConfiguration
    /* El objeto binding de pertenece a la clase ActivityMainBinding que ha sido importada previamente.
       Este objeto permite establecer una conexión entre los views de los XML y la lógica de la función.*/
    private lateinit var binding: ActivityMainBinding
    //magia
    //private val viewmodel : com.logocito.atlas.ui.ViewModel by viewModels()

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
class MainActivityViewModel(application: Application) : AndroidViewModel(application){
    private  var daoMasasAgua : MasaAguaDao
    private  var daoTramos : TramoDao
    private  var daoMuestrasTransversales : MuestrasTransversalesDao
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
    private val _codigosDeMuestras= MutableLiveData<ArrayList<Pair<String,String>>>()
    val codigosDeMuestras : LiveData<ArrayList<Pair<String,String>>> get() = _codigosDeMuestras

    init {
        val app = this.getApplication<App>()
        this.daoMasasAgua = app.database.masaAguaDao()
        this._codigosDeMasasDeAgua.postValue(this.daoMasasAgua.obtenerCodigosMasasAgua() as ArrayList<String>)
        this.daoTramos = app.database.tramoDao()
        this.daoMuestrasTransversales = app.database.muestrasTransversalesDao()
    }

    fun añadirMasaAgua (codigo: String){
        val nuevaMasaAgua = MasaAgua(
            0,
            codigo,
        )
        this.daoMasasAgua.añadirMasaAgua(nuevaMasaAgua)
        this._codigosDeMasasDeAgua.value = daoMasasAgua.obtenerCodigosMasasAgua() as ArrayList<String> /* = java.util.ArrayList<kotlin.String> */
    }
    fun añadirTramo(codigoMasaAgua : String, codigoTramo : String){
        val masaAgua = daoMasasAgua.cargar(codigoMasaAgua)
        val tramo = Tramo(0,codigoTramo,masaAgua.id)
        this.daoTramos.crearTramo(tramo)
        this._codigosDeTramos.value = daoTramos.obtenerCodigosTramos(masaAgua.id) as ArrayList<String>
    }
    fun añadirMuestraTransversal(){
        val muestras = this.codigosDeMuestras.value!!
        var index = 0
        var yaEsta : Boolean
        var codigo : String
        do{
            index = index + 1
            codigo = "Nuevo_${index}"
            val par = Pair<String,String>(codigo,"Transversal")
            yaEsta = muestras.contains(par)
        }
        while (yaEsta)
        this.añadirMuestraTransversal(codigo)
    }
    fun añadirMuestraTransversal(codigoMuestraTransversal : String){
        val codigoTramo = this.codigoDeTramo.value!!
        val idTramo = daoTramos.findId(codigoTramo)
        val muestraTransversal = MuestraTransversal(
            id=0,
            idTramo = idTramo,
            codigo = codigoMuestraTransversal,
            longitud = 0,
        )
        this.daoMuestrasTransversales.crearMuestraTransversal(muestraTransversal)
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
    fun cargarMasaAgua(codigo : String,){
        val idMasaAgua = daoMasasAgua.findId(codigo)
        this._codigoDeMasaAgua.postValue(codigo)
        this._codigosDeTramos.postValue(daoTramos.obtenerCodigosTramos(idMasaAgua) as ArrayList<String> /* = java.util.ArrayList<kotlin.String> */)
    }
    fun cargarTramo(codigo : String,){
        val idTramo = daoTramos.findId(codigo)
        this._codigoDeTramo.postValue(codigo)
        val todosCodigosDeMuestras = ArrayList<Pair<String,String>>()
        val codigosMuestrasTransversales = daoMuestrasTransversales.obtenerCodigosMuestrasTransversales(idTramo)
        todosCodigosDeMuestras.addAll(codigosMuestrasTransversales.map{
            Pair<String,String>(it,"Transversal")
        })
        this._codigosDeMuestras.postValue(todosCodigosDeMuestras)
    }
}

