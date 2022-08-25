package com.logocito.atlas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableRow
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.logocito.atlas.R
import com.logocito.atlas.data.Identificador
import com.logocito.atlas.data.Muestra
import com.logocito.atlas.data.MuestraDao
import com.logocito.atlas.data.muestras.MuestraLongitudinal
import com.logocito.atlas.data.muestras.MuestraSubtramo
import com.logocito.atlas.data.muestras.MuestraTransversal
import com.logocito.atlas.databinding.FragmentMuestrasBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MuestrasFragment : Fragment() {

    private var _binding: FragmentMuestrasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel : MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMuestrasBinding.inflate(inflater, container, false)
        return binding.root

    }

    private inline fun < reified T : Muestra> recargarParteLista (muestras : List<Identificador>, tipo : String, navigationAction : Int){
        for (muestra in muestras){
            val fila = TableRow(this.binding.tabla.context)
            fila.setOnClickListener {
                val navController = findNavController()
                val bundle = bundleOf("id" to muestra.id)
                navController.navigate(navigationAction, bundle)
            }
            val prueba1 = TextView(fila.context)
            prueba1.text = muestra.codigo
            prueba1.setTextAppearance(androidx.appcompat.R.style.TextAppearance_AppCompat_Body2)
            prueba1.textSize = 20F
            fila.addView(prueba1)
            val tipoView = TextView(fila.context)
            tipoView.text = tipo
            fila.addView(tipoView)
            val botonBorrar = Button(fila.context)
            botonBorrar.text = "Borrar"
            botonBorrar.setOnClickListener {
                this.viewModel.eliminarMuestra<T>(muestra.id)
            }
            fila.addView(botonBorrar)
            this.binding.tabla.addView(fila)

        }
    }
    fun recargarLista () {
        this.binding.tabla.removeAllViews()

        this.viewModel.muestrasLongitudinales.value?.let{
            this.recargarParteLista<MuestraLongitudinal>(
                it,
                "Longitudinal",
                R.id.action_MuestrasFragment_to_MuestraLongitudinalFragment,
            )
        }

        this.viewModel.muestrasSubtramos.value?.let{
            this.recargarParteLista<MuestraSubtramo>(
                it,
                "Subtramo",
                R.id.action_MuestrasFragment_to_MuestraSubtramoFragment,
            )
        }

        this.viewModel.muestrasTransversales.value?.let{
            this.recargarParteLista<MuestraTransversal>(
                it,
                "Transversal",
                R.id.action_MuestrasFragment_to_MuestraTransversalFragment,
            )
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.viewModel.codigoDeTramo.observe(this.viewLifecycleOwner, Observer {
            binding.editTramoCode.setText(this.viewModel.codigoDeTramo.value)
        })
        this.viewModel.muestrasTransversales.observe(
            this.viewLifecycleOwner,
            Observer {
                this.recargarLista()
            },
        )
        this.viewModel.muestrasLongitudinales.observe(
            this.viewLifecycleOwner,
            Observer {
                this.recargarLista()
            },
        )
        this.viewModel.muestrasSubtramos.observe(
            this.viewLifecycleOwner,
            Observer {
                this.recargarLista()
            },
        )


        binding.btNuevoTransversal.setOnClickListener {
            this.viewModel.añadirMuestraTransversal()
            //findNavController().navigate(R.id.action_TramosFragment_to_MuestrasFragment)
        }
        binding.btNuevoLongitudinal.setOnClickListener {
            this.viewModel.añadirMuestraLongitudinal()
            //findNavController().navigate(R.id.action_TramosFragment_to_MuestrasFragment)
        }
        binding.btNuevoSubtramo.setOnClickListener {
            this.viewModel.añadirMuestraSubtramo()
            //findNavController().navigate(R.id.action_TramosFragment_to_MuestrasFragment)
        }
        binding.editTramoCode.setOnEditorActionListener {
                _,_,_ ->
            val codigoNuevo = this.binding.editTramoCode.text.toString()
            this.viewModel.cambiarCodigoTramo(
                codigoNuevo,
            )

            true
        }
        //Load Data
        this.viewModel.cargarTramo(arguments?.getLong("tramo")!!)
    }

    override fun onPause() {
        super.onPause()
        this.viewModel.cambiarCodigoTramo(this.binding.editTramoCode.text.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}