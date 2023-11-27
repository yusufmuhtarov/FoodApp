package com.example.foodApp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentStartBinding


class StartFragment : Fragment() {
    private val binding: FragmentStartBinding by lazy {
        FragmentStartBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goToNextPageBtn.setOnClickListener{
            findNavController().navigate(
                R.id.action_startFragment_to_firstScreenFragment
            )
        }
    }
}