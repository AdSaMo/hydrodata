package com.logocito.atlas.ui

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.logocito.atlas.data.Campo
import com.logocito.atlas.data.Opcion
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.isSubclassOf

private const val ARG_PARAM1 = "id"

inline fun <ClaseModelo : Any> sortCampos (dataClass : KClass<ClaseModelo>) : List<Pair<String, List< KProperty1<ClaseModelo, *>>>> {
    val camposSeccionados = HashMap<
            String,
            HashMap<Int, KProperty1<ClaseModelo, *>>
            >()
    for ( propiedad in dataClass.declaredMemberProperties){
        val campoMetadata = propiedad.findAnnotation<Campo>()
        campoMetadata?.let{
            val lista = camposSeccionados.getOrPut(campoMetadata.sección){
                HashMap<Int, KProperty1<ClaseModelo, *>>()
            }
            lista.put(campoMetadata.posición, propiedad)
        }
    }

    val camposSeccionados2 = camposSeccionados.map {
        it.key to it.value.entries.sortedBy { it.key }.map {
            it.value
        }
    }

    return camposSeccionados2
}

abstract class Muestra <ClaseModelo : Any> : Fragment() {
    //private lateinit var data : ClaseModelo
    protected var dbid: Int = -1
    private val formularioViews = HashMap<String, Int>()
    protected val viewModel : MainActivityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            this.dbid = it.getInt(ARG_PARAM1)
        }
    }

    protected fun formularioInflate (lista : ConstraintLayout, dataClass : KClass<ClaseModelo>){
        val formularioCampos = sortCampos(dataClass)
        for ((seccion, campos) in formularioCampos) {
            val constraints = ConstraintSet()
            val seccionConstraintLayoutIdentifier = "seccion${seccion}"
            val seccionConstraintLayoutId = lista.context.resources.getIdentifier(seccionConstraintLayoutIdentifier, "id",lista.context.packageName)
            val seccionConstraintLayout = lista.findViewById<ConstraintLayout>(seccionConstraintLayoutId)
            val seccionHeader = seccionConstraintLayout.getChildAt(0)
            var lastView: View = seccionHeader
            for (campo in campos) {
                val propiedad = campo
                val campoData = propiedad.findAnnotation<Campo>()
                campoData?.let {
                    val descripcion = android.widget.TextView(seccionConstraintLayout.context)
                    descripcion.id = View.generateViewId()
                    descripcion.text = it.descripcion
                    topToBottomOf(seccionConstraintLayout, constraints,lastView, descripcion, 32)
                    lastView = descripcion

                    if(it.imagen>=0){
                        val imagen = ImageView(seccionConstraintLayout.context)
                        imagen.id = View.generateViewId()
                        imagen.setImageResource(it.imagen)
                        topToBottomOf(seccionConstraintLayout, constraints,lastView, imagen)
                        lastView = imagen
                    }

                    val campoTipo = propiedad.returnType.classifier as KClass<*>
                    val campoEsUnEnum = campoTipo.isSubclassOf(Enum::class)

                    if (campoEsUnEnum) {
                        val radioGroup = android.widget.RadioGroup(seccionConstraintLayout.context)
                        radioGroup.id = View.generateViewId()
                        this.formularioViews[propiedad.name] = radioGroup.id
                        radioGroup.orientation = RadioGroup.VERTICAL
                        for (opcion in campoTipo.java.declaredFields) {
                            if (opcion.isEnumConstant) {
                                val radioButton = android.widget.RadioButton(radioGroup.context)
                                radioButton.tag = opcion.get(campoTipo)
                                //println("AQUÍ ${opcion.name}")
                                //println("AQUÍ ${opcion.}")
                                val opcionMetadata = opcion.getAnnotation(Opcion::class.java)
                                radioButton.text =
                                    opcionMetadata?.descripcion ?: maquillarNombre(opcion.name)
                                radioGroup.addView(radioButton)
                            }
                        }
                        topToBottomOf(seccionConstraintLayout, constraints, lastView, radioGroup)
                        lastView = radioGroup
                    } else if (campoTipo == kotlin.Boolean::class) {
                        val checkBox = android.widget.CheckBox(seccionConstraintLayout.context)
                        checkBox.id = View.generateViewId()
                        this.formularioViews[propiedad.name] = checkBox.id
                        topToBottomOf(seccionConstraintLayout, constraints, lastView, checkBox)
                        lastView = checkBox
                    } else {
                        val editText = android.widget.EditText(seccionConstraintLayout.context)
                        editText.id = View.generateViewId()
                        this.formularioViews[propiedad.name] = editText.id
                        editText.inputType = when (campoTipo) {
                            kotlin.Double::class -> InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                            kotlin.Float::class -> InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                            kotlin.Int::class -> InputType.TYPE_CLASS_NUMBER
                            kotlin.Long::class -> InputType.TYPE_CLASS_NUMBER
                            kotlin.String::class -> InputType.TYPE_CLASS_TEXT
                            else -> InputType.TYPE_CLASS_TEXT
                        }
                        topToBottomOf(seccionConstraintLayout, constraints, lastView, editText)
                        lastView = editText
                    }
                }
            }
            constraints.connect(
                lastView!!.id,
                ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID,
                ConstraintSet.BOTTOM
            )
            constraints.applyTo(seccionConstraintLayout)
        }
    }

    protected fun formularioLoad (muestra : ClaseModelo, view : android.view.View){
        for ( propiedad in muestra::class.declaredMemberProperties){
            val viewId = this.formularioViews[propiedad.name]
            viewId?.let{
                val campoTipo = propiedad.returnType.classifier as KClass<*>
                val campoEsUnEnum = campoTipo.isSubclassOf(Enum::class)
                if (campoTipo == kotlin.Boolean::class) {
                    val formularioView = view.findViewById<CheckBox>(viewId)
                    formularioView.isChecked = propiedad.call(muestra) as Boolean
                }else if (campoEsUnEnum){
                    val formularioView = view.findViewById<RadioGroup>(viewId)
                    val valor = propiedad.call(muestra) as Enum<*>
                    //val nombreConstante = valor.toString()
                    //println("AQUÍ ${nombreConstante}")
                    val radioButton = formularioView.findViewWithTag<RadioButton>(
                        //nombreConstante
                        valor
                    )
                    radioButton.isChecked = true
                }else{
                    val formularioView = view.findViewById<EditText>(viewId)
                    formularioView.setText(propiedad.call(muestra) as String)
                }
            }
        }
    }

    protected fun formularioSave(muestra : ClaseModelo, view : View) : ClaseModelo{
        for ( propiedad in muestra::class.declaredMemberProperties){
            val viewId = this.formularioViews[propiedad.name]
            viewId?.let{
                val campoTipo = propiedad.returnType.classifier as KClass<*>
                val campoEsUnEnum = campoTipo.isSubclassOf(Enum::class)
                val propiedadMutable = propiedad as KMutableProperty1
                if (campoTipo == kotlin.Boolean::class) {
                    val formularioView = view.findViewById<CheckBox>(viewId)
                    propiedadMutable.setter.call(muestra, formularioView.isChecked)
                }else if (campoEsUnEnum){
                    val radioGroup  = view.findViewById<RadioGroup>(viewId)
                    val radioButon = radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
                   // val constantName = radioButon.tag
                    val constant = radioButon.tag
                    //(campoTipo as Enum<*>)::class.java.
                    propiedadMutable.setter.call(muestra, constant)
                }else{
                    val formularioView = view.findViewById<EditText>(viewId)
                    propiedadMutable.setter.call(muestra, formularioView.text.toString())
                }
            }
        }
        return muestra
    }
}

private fun topToBottomOf(constraintLayout: ConstraintLayout, constraints : ConstraintSet, elDeArriba : View, elDeAbajo : View, margen : Int = 8){
    elDeAbajo.layoutParams = ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.WRAP_CONTENT,
        ConstraintLayout.LayoutParams.WRAP_CONTENT)
    constraintLayout.addView(
        elDeAbajo,
        //ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,ConstraintLayout.LayoutParams.MATCH_CONSTRAINT)
    )
    //constraints.clone(constraintLayout)
    constraints.connect(elDeAbajo.id, ConstraintSet.TOP, elDeArriba.id, ConstraintSet.BOTTOM,margen)
    constraints.connect(elDeAbajo.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
    constraints.constrainHeight(elDeAbajo.id, ConstraintLayout.LayoutParams.WRAP_CONTENT )
    constraints.constrainWidth(elDeAbajo.id, ConstraintLayout.LayoutParams.WRAP_CONTENT)
}

private fun maquillarNombre(nombreFeo : String) : String{
    val primeraLetra = nombreFeo[0]
    val parteQueDeberiaSerMinuscula = nombreFeo.substring(1)
    val nombreBonito = primeraLetra + parteQueDeberiaSerMinuscula.lowercase()
    return nombreBonito
}