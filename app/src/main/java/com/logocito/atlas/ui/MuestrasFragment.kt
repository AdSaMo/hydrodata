package com.logocito.atlas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
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

    fun recargarParteLista (codigos : List<String>, tipo : String){
        for (codigo in codigos){
            val fila = TableRow(this.binding.tabla.context)
            val prueba1 = TextView(fila.context)
            prueba1.text = codigo
            fila.addView(prueba1)
            val tipoView = TextView(fila.context)
            tipoView.text = tipo
            fila.addView(tipoView)
            this.binding.tabla.addView(fila)

        }
    }
    fun recargarLista () {
        this.binding.tabla.removeAllViews()
        if(this.viewModel.codigosDeMuestrasTransversales.value != null){
            this.recargarParteLista(this.viewModel.codigosDeMuestrasTransversales.value!!, "Transversal")
        }
        if(this.viewModel.codigosDeMuestrasLongitudinales.value != null) {
            this.recargarParteLista(
                this.viewModel.codigosDeMuestrasLongitudinales.value!!,
                "Longitudinal",
            )
        }
        if(this.viewModel.codigosDeMuestrasSubtramos.value != null) {
            this.recargarParteLista(this.viewModel.codigosDeMuestrasSubtramos.value!!, "Subtramo")
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.viewModel.codigoDeTramo.observe(this.viewLifecycleOwner, Observer {
            binding.editTramoCode.setText(this.viewModel.codigoDeTramo.value)
        })
        this.viewModel.codigosDeMuestrasTransversales.observe(
            this.viewLifecycleOwner,
            Observer {
                this.recargarLista()
            },
        )
        this.viewModel.codigosDeMuestrasLongitudinales.observe(
            this.viewLifecycleOwner,
            Observer {
                this.recargarLista()
            },
        )
        this.viewModel.codigosDeMuestrasSubtramos.observe(
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
        this.viewModel.cargarTramo(arguments?.getString("codigoDeTramo")!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}