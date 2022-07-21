package com.logocito.atlas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.logocito.atlas.R
import com.logocito.atlas.data.MasaAgua
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

        this.viewModel.codigosDeTramos.observe(
            this.viewLifecycleOwner,
            Observer {
                var lista = binding.lista.getChildAt(0) as LinearLayout
                lista.removeAllViews()
                for (codigoDeTramo in it){
                    var prueba1 = android.widget.TextView(this.binding.lista.context)
                    prueba1.text = codigoDeTramo
                    prueba1.setOnClickListener {
                        val navController = findNavController()
                        val bundle = bundleOf("codigoDeTramo" to codigoDeTramo)
                        navController.navigate(R.id.action_TramosFragment_to_MuestrasFragment, bundle)
                    }
                    lista.addView(prueba1)
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
        this.viewModel.cargarMasaAgua(arguments?.getString("masaDeAgua")!!)
    }

    override fun onPause() {
        super.onPause()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

