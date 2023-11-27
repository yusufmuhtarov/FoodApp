package com.example.foodApp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodApp.fragments.FirstScreenFragment.Companion.FOOD_KEY
import com.example.foodApp.models.ModelFood
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentFoodScreenBinding
import com.google.android.material.snackbar.Snackbar


class FoodScreen : Fragment() {

    private val binding: FragmentFoodScreenBinding by lazy {
        FragmentFoodScreenBinding.inflate(layoutInflater)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val foodModel = arguments?.getSerializable(FOOD_KEY) as? ModelFood
        binding.tvFoodName.text = foodModel?.foodName
        binding.tvFoodPrice.text = foodModel?.foodPrice
        binding.backIv.setOnClickListener {
            findNavController().navigate(
                R.id.action_foodScreen_to_firstScreenFragment
            )
        }

        binding.addToBasketBtn.setOnClickListener {
            findNavController().popBackStack()
            Snackbar.make(
                binding.root, "Succesfully added", Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root
}