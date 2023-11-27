package com.example.foodApp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentAddBinding
import com.google.android.material.snackbar.Snackbar

class AddFragment : Fragment() {

    private val binding: FragmentAddBinding by lazy {
        FragmentAddBinding.inflate(layoutInflater)
    }

//    private val sharedPreferences =  com.example.foodApp.pref.FoodSharedPreferences(requireContext())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_addFragment_to_firstScreenFragment
            )
            saveFood()
        }
        binding.backIv.setOnClickListener {
            findNavController().navigate(
                R.id.action_addFragment_to_firstScreenFragment
            )
        }
    }

    fun saveFood() = binding.apply {
        if (titleEt.text?.isNotEmpty() == true && priceEt.text?.isNotEmpty() == true) {
//            sharedPreferences.saveFood(
//                ModelFood(
//                    foodName = titleEt.text.toString(),
//                    foodPrice = priceEt.text.toString()
//                )
//            )
            Log.d("UUU", "$")
        } else {
            showToastMessage("Enter the fields")
        }

    }

    fun showToastMessage(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }
}