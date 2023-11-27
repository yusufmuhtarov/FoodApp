package com.example.foodApp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodApp.adapter.FoodsAdapter
import com.example.foodApp.pref.FoodSharedPreferences
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentFirstScreenBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class FirstScreenFragment : Fragment() {

    private val binding: FragmentFirstScreenBinding by lazy {
        FragmentFirstScreenBinding.inflate(layoutInflater)
    }

    private val sharedPreferences: FoodSharedPreferences by lazy {
        FoodSharedPreferences(requireActivity())
    }

    private val adapter: FoodsAdapter by lazy {
        FoodsAdapter(onDeleteFoodClick = { index ->
            sharedPreferences.deleteFoodByIndex(index)
            setUpViewsAndAdapter()
        },
            onClickToFood = {
                findNavController().navigate(
                    R.id.action_firstScreenFragment_to_foodScreen,
                    bundleOf(FOOD_KEY to it)
                )
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClickListener()
        setUpViewsAndAdapter()

    }

    private fun setUpViewsAndAdapter() {
        val listOfFood = sharedPreferences.getFood()
        if (listOfFood.isNotEmpty()) {
            adapter.updateList(listOfFood)
            binding.mainRv.visibility = View.VISIBLE
            binding.firstIv.visibility = View.GONE
            binding.mainRv.adapter = adapter
        } else {
            binding.mainRv.visibility = View.GONE
            binding.firstIv.visibility = View.VISIBLE
        }
    }

    private fun showConfirmDeleteDialog() {
        val alertDialog = MaterialAlertDialogBuilder(requireContext())
        alertDialog.setMessage("Do you really want to delete all cares")
        alertDialog.setPositiveButton("yes") { dialog, _ ->
            deleteAllSavedFood()
            dialog.dismiss()
        }
        alertDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        alertDialog.create().show()
    }


    private fun deleteAllSavedFood() {
        sharedPreferences.deleteAllFood()
        adapter.updateList(emptyList())
        binding.mainRv.visibility = View.GONE
        binding.firstIv.visibility = View.VISIBLE
    }

    private fun setUpClickListener() = binding.apply {
        addNoteBtn.setOnClickListener {
            findNavController().navigate(R.id.action_firstScreenFragment_to_addFragment)
        }
        binding.backCard.setOnClickListener {
            findNavController().navigate(R.id.action_firstScreenFragment_to_startFragment)
        }
        deleteCard.setOnClickListener {
            showConfirmDeleteDialog()
        }
    }

    companion object {
        const val FOOD_KEY = "FOOD_KEY"
    }
}