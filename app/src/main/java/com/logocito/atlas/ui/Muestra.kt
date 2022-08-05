package com.logocito.atlas.ui

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.logocito.atlas.data.Campo
import com.logocito.atlas.data.Opcion
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.isSubclassOf

private const val ARG_PARAM1 = "id"

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
        val campos = LinkedHashMap<Int, KProperty1<ClaseModelo, *>>()
        for ( propiedad in dataClass.declaredMemberProperties){
            val campoMetadata = propiedad.findAnnotation<Campo>()
            campoMetadata?.let{
                campos.put(campoMetadata.posición,propiedad)
            }
        }
        val camposOrdenados = campos.entries.sortedBy {
            it.key
        }
        val constraints = ConstraintSet()
        var lastView : View? = null
        for ( campo in camposOrdenados){
            val propiedad = campo.value
            val campoData = propiedad.findAnnotation<Campo>()
            campoData?.let {
                val descripcion = android.widget.TextView(lista.context)
                descripcion.id = View.generateViewId()
                descripcion.text = it.descripcion
                topToBottomOf(lista,constraints,lastView,descripcion)
                lastView = descripcion

                val campoTipo = propiedad.returnType.classifier as KClass<*>
                val campoEsUnEnum = campoTipo.isSubclassOf(Enum::class)

                if (campoEsUnEnum){
                    val radioGroup = android.widget.RadioGroup(lista.context)
                    radioGroup.id = View.generateViewId()
                    this.formularioViews[propiedad.name] = radioGroup.id
                    radioGroup.orientation = RadioGroup.VERTICAL
                    for (opcion in campoTipo.java.declaredFields){
                        if (opcion.isEnumConstant){
                            val radioButton = android.widget.RadioButton(radioGroup.context)
                            val opcionMetadata = opcion.getAnnotation(Opcion::class.java)
                            radioButton.text = opcionMetadata?.descripcion ?: maquillarNombre(opcion.name)
                            radioGroup.addView(radioButton)
                        }
                    }
                    topToBottomOf(lista,constraints,lastView,radioGroup)
                    lastView = radioGroup
                }
                else if (campoTipo==kotlin.Boolean::class){
                    val checkBox = android.widget.CheckBox(lista.context)
                    checkBox.id = View.generateViewId()
                    this.formularioViews[propiedad.name] = checkBox.id
                    topToBottomOf(lista,constraints,lastView,checkBox)
                    lastView = checkBox
                }
                else{
                    val editText = android.widget.EditText(lista.context)
                    editText.id = View.generateViewId()
                    this.formularioViews[propiedad.name] = editText.id
                    editText.inputType = when ( campoTipo )
                    {
                        kotlin.Int::class -> InputType.TYPE_CLASS_NUMBER
                        kotlin.Long::class -> InputType.TYPE_CLASS_NUMBER
                        kotlin.String::class -> InputType.TYPE_CLASS_TEXT
                        else -> InputType.TYPE_CLASS_TEXT
                    }
                    topToBottomOf(lista,constraints,lastView,editText)
                    lastView = editText
                }
            }
        }
        constraints.connect(lastView!!.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        constraints.applyTo(lista)
    }

    protected fun formularioLoad (muestra : ClaseModelo, view : android.view.View){
        for ( propiedad in muestra::class.declaredMemberProperties){
            val viewId = this.formularioViews[propiedad.name]
            viewId?.let{
                val campoTipo = propiedad.returnType.classifier as KClass<*>
                val campoEsUnEnum = campoTipo.isSubclassOf(Enum::class)
                if (campoTipo == kotlin.Boolean::class) {
                    val formularioView = view.findViewById<CheckBox>(viewId)
                }else if (campoEsUnEnum){
                    val formularioView = view.findViewById<RadioGroup>(viewId)
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
                }else if (campoEsUnEnum){
                    val formularioView = view.findViewById<RadioGroup>(viewId)
                }else{
                    val formularioView = view.findViewById<EditText>(viewId)
                    propiedadMutable.setter.call(muestra, formularioView.text.toString())
                }
            }
        }
        return muestra
    }
}

private fun topToBottomOf(constraintLayout: ConstraintLayout, constraints : ConstraintSet, elDeArriba : View?, elDeAbajo : View){
    elDeAbajo.layoutParams = ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.WRAP_CONTENT,
        ConstraintLayout.LayoutParams.WRAP_CONTENT)
    constraintLayout.addView(
        elDeAbajo,
        //ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,ConstraintLayout.LayoutParams.MATCH_CONSTRAINT)
    )
    //constraints.clone(constraintLayout)
    if (elDeArriba == null){
        constraints.connect(elDeAbajo.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
    } else{
        constraints.connect(elDeAbajo.id, ConstraintSet.TOP, elDeArriba.id, ConstraintSet.BOTTOM,8)
    }
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