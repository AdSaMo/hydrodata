package com.logocito.atlas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.logocito.atlas.R
import com.logocito.atlas.databinding.FragmentPortadaBinding


class PortadaFragment : Fragment() {
    private var _binding: FragmentPortadaBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MainActivityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portada, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val empezar : Button = view.findViewById(R.id.buttonEmpezar)
        var navController = findNavController()
        empezar.setOnClickListener {
            navController.navigate(R.id.action_blankFragment_to_MasasAguaFragment)
        }

    }


}