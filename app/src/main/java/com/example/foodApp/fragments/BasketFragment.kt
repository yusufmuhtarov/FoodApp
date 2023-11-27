package com.example.foodApp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.foodApp.adapter.BasketAdapter
import com.example.foodApp.pref.BasketSharedPreference
import com.example.foodapp.R
import com.example.foodapp.databinding.BasketItemBinding
import com.example.foodapp.databinding.FragmentBasketBinding


class BasketFragment : Fragment() {
    private val binding: FragmentBasketBinding by lazy {
        FragmentBasketBinding.inflate(layoutInflater)
    }

    private val sharedPreferences:BasketSharedPreference by lazy {
        BasketSharedPreference(requireContext())
    }

    private val adapter: BasketAdapter by lazy {
        BasketAdapter(
            onDeleteBtn = {index ->
        sharedPreferences.deleteFoodByIndex(index)
                setUpViewsAndAdapter()
                          },
            onShareToBasket = { findNavController().popBackStack(

            )
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_basket, container, false)
    }

    private fun setUpViewsAndAdapter() {
        val listOfFood = sharedPreferences.getFood()
    if (listOfFood.isNotEmpty()){
        binding.mainRv.visibility = View.VISIBLE
        binding.firstIv.visibility = View.GONE
        binding.mainRv.adapter = adapter
    } else {
        binding.mainRv.visibility = View.GONE
        binding.firstIv.visibility = View.VISIBLE
    }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backCard.setOnClickListener { findNavController().navigate(
            R.id.action_basketIcon_to_homeIcon)}
        setUpViewsAndAdapter()
    }
    companion object {
        const val BASKET_KEY = "BASKET_KEY"
    }
}