package com.logocito.atlas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.logocito.atlas.R
import com.logocito.atlas.data.MasaAgua
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

        this.viewModel.codigosDeMasasDeAgua.observe(this.viewLifecycleOwner, Observer {

            var lista = binding.lista.getChildAt(0) as LinearLayout
            lista.removeAllViews()
            for (codigo_de_masa_de_agua in it){
                var prueba1 = android.widget.TextView(this.binding.lista.context)
                prueba1.text = codigo_de_masa_de_agua
                prueba1.setOnClickListener {
                    Snackbar.make(view, "Cargando datos", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                    val navController = findNavController()
                    val bundle = bundleOf("masaDeAgua" to codigo_de_masa_de_agua)
                    navController.navigate(R.id.action_MasasAguaFragment_to_TramosFragment, bundle)
                }
                lista.addView(prueba1)
            }


        })


        binding.btNuevaMasa.setOnClickListener {
            val codigo = this.viewModel.añadirMasaAgua()
            val navController = findNavController()
            val bundle = bundleOf("masaDeAgua" to codigo)
            navController.navigate(
                R.id.action_MasasAguaFragment_to_TramosFragment,
                bundle,
            )
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

