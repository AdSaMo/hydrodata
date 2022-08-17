package com.logocito.atlas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.logocito.atlas.data.muestras.MuestraTransversal
import com.logocito.atlas.databinding.FragmentMuestraTransversalBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "id"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MuestrasTransversalesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MuestraTransversalFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var idMuestraTransversal: Int = -1
    //private var param2: String? = null
    private var _binding : FragmentMuestraTransversalBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MainActivityViewModel by activityViewModels()
    private lateinit var muestra : MuestraTransversal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            this.idMuestraTransversal = it.getInt(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_muestra_transversal, container, false)
        this._binding = FragmentMuestraTransversalBinding.inflate(inflater, container, false)
        this.binding.coordenadaX.setOnEditorActionListener {
            _,_,_, ->
            //this.viewModel.actualizarCoordenadas
            true
        }
        this.binding.coordenadaY.setOnEditorActionListener {
            _,_,_, ->
            //this.viewModel.actualizarCoordenadas
            true
        }
        return binding.root

    }

    override fun onPause() {
        super.onPause()
        this.muestra.coordenadaX = this.binding.coordenadaX.text as Long
        this.viewModel.cambiarMuestraTransversal(this.muestra)
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding.
    }
    */

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param idMuestraTransversal ID de la muestra transversal a mostrar y editar.
         * @return A new instance of fragment MuestrasTransversalesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(idMuestraTransversal: Int) =
            MuestraTransversalFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, idMuestraTransversal)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }
}