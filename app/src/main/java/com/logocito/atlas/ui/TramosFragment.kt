package com.logocito.atlas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TableRow
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.logocito.atlas.R
import com.logocito.atlas.databinding.FragmentTramosBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TramosFragment : Fragment() {

    private var _binding: FragmentTramosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    //private val viewModel : TramosViewModel by viewModels()
    private val viewModel : MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTramosBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Instalación de reactores (callback installation)
        this.viewModel.codigoDeMasaAgua.observe(this.viewLifecycleOwner, Observer {
            binding.editMasaAguaCode.setText(this.viewModel.codigoDeMasaAgua.value)
        })

        this.viewModel.tramos.observe(
            this.viewLifecycleOwner,
            Observer {
                this.binding.tabla.removeAllViews()
                for (identificador in it){
                    val fila = TableRow(this.binding.tabla.context)
                    val codigoDeTramo = identificador.codigo
                    val prueba1 = android.widget.TextView(fila.context)
                    prueba1.text = codigoDeTramo
                    prueba1.setTextAppearance(androidx.appcompat.R.style.TextAppearance_AppCompat_Body2)
                    prueba1.textSize = 20F
                    prueba1.setOnClickListener {
                        val navController = findNavController()
                        val bundle = bundleOf("tramo" to identificador.id)
                        navController.navigate(R.id.action_TramosFragment_to_MuestrasFragment, bundle)
                    }
                    fila.addView(prueba1)
                    val botonBorrar = Button(fila.context)
                    botonBorrar.text = "Borrar"
                    botonBorrar.setOnClickListener {
                        this.viewModel.eliminarTramo(identificador.id)
                    }
                    fila.addView(botonBorrar)
                    this.binding.tabla.addView(fila)
                }
            },
        )

        binding.btNuevoTramo.setOnClickListener {
            this.viewModel.añadirTramo()
            //findNavController().navigate(R.id.action_TramosFragment_to_MuestrasFragment)
        }
        binding.editMasaAguaCode.setOnEditorActionListener {
            _,_,_ ->
            val codigoNuevo = this.binding.editMasaAguaCode.text.toString()
            this.viewModel.cambiarCodigoMasaAgua(
                codigoNuevo,
            )

            true
        }

        //Load Data
        this.viewModel.cargarMasaAgua(arguments?.getLong("masaDeAgua")!!)
    }

    override fun onPause() {
        super.onPause()
        this.viewModel.cambiarCodigoMasaAgua(this.binding.editMasaAguaCode.text.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

