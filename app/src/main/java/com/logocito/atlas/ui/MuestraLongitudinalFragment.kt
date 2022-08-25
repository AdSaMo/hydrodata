package com.logocito.atlas.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.logocito.atlas.data.muestras.MuestraLongitudinal
import com.logocito.atlas.databinding.FragmentMuestraLongitudinalBinding
import kotlin.reflect.full.*

/**
 * A simple [Fragment] subclass.
 * Use the [MuestrasTransversalesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MuestraLongitudinalFragment : Muestra<MuestraLongitudinal>() {
    private var _binding : FragmentMuestraLongitudinalBinding? = null
    private val binding get() = _binding!!
    private lateinit var muestra : MuestraLongitudinal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_muestra_transversal, container, false)
        this._binding = FragmentMuestraLongitudinalBinding.inflate(inflater, container, false)
        this.formularioInflate(this.binding.lista, MuestraLongitudinal :: class)
        return binding.root

    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.viewModel.muestraLongitudinal.observe(
            this.viewLifecycleOwner,
            Observer{
                this.muestra = it
                this.binding.codigo.setText(it.codigo)
                this.formularioLoad(it, view)
            }
        )
        this.binding.codigo.setOnEditorActionListener { textView, i, keyEvent ->
            val codigoNuevo = textView.text.toString()
            this.viewModel.cambiarCodigoLongitudinal(
                this.dbid,
                codigoNuevo,
            )
            true
        }
        this.viewModel.cargarMuestraLongitudinal(this.dbid)
    }

    override fun onPause() {
        super.onPause()
        val muestraEditada = this.formularioSave(this.muestra, this.binding.root)
        muestraEditada.codigo = this.binding.codigo.text.toString()
        this.viewModel.cambiarMuestraLongitudinal(muestraEditada)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param idMuestraTransversal ID de la muestra transversal a mostrar y editar.
         * @return A new instance of fragment MuestrasTransversalesFragment.
         */
        // TODO: Rename and change types and number of parameters
        /*@JvmStatic
        fun newInstance(idMuestraLongitudinal: Int) =
            MuestraTransversalFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, idMuestraTransversal)
                    //putString(ARG_PARAM2, param2)
                }
            }

         */
    }
}