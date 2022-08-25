package com.logocito.atlas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TableRow
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.logocito.atlas.R
import com.logocito.atlas.databinding.FragmentMasasAguaBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MasasAguaFragment : Fragment() {

    private var _binding: FragmentMasasAguaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    //private val viewModel : MasasAguaViewModel by viewModels()
    private val viewModel : MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMasasAguaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.viewModel.masasDeAgua.observe(this.viewLifecycleOwner, Observer {
            this.binding.tabla.removeAllViews()
            for (identificador in it){
                val codigo_de_masa_de_agua = identificador.codigo
                val fila = TableRow(this.binding.tabla.context)
                var prueba1 = android.widget.TextView(fila.context)
                //FIXME: ID
                prueba1.text = codigo_de_masa_de_agua
                prueba1.setTextAppearance(androidx.appcompat.R.style.TextAppearance_AppCompat_Body2)
                prueba1.textSize = 20F
                prueba1.setOnClickListener {
                    /*Snackbar.make(view, "Cargando datos", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()*/
                    val navController = findNavController()
                    val bundle = bundleOf("masaDeAgua" to identificador.id)
                    navController.navigate(R.id.action_MasasAguaFragment_to_TramosFragment, bundle)
                }
                //val onSwipeListener = OnSwipeListener(codigo_de_masa_de_agua)
                //prueba1.setOnTouchListener(onSwipeListener)
                fila.addView(prueba1)
                val botonBorrar = Button(fila.context)
                botonBorrar.text = "Borrar"
                botonBorrar.setOnClickListener {
                    this.viewModel.eliminarMasaAgua(identificador.id)
                }
                fila.addView(botonBorrar)
                this.binding.tabla.addView(fila)
            }


        })


        binding.btNuevaMasa.setOnClickListener {
            val id = this.viewModel.añadirMasaAgua()
            /*
            val navController = findNavController()
            val bundle = bundleOf("masaDeAgua" to id)
            navController.navigate(
                R.id.action_MasasAguaFragment_to_TramosFragment,
                bundle,
            )

             */
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

