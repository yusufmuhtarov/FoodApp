package com.example.foodApp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodApp.models.ModelFood
import com.example.foodapp.R
import com.example.foodapp.databinding.FoodItemBinding

class FoodsAdapter(
    private val onDeleteFoodClick: (index: Int) -> Unit,
) : RecyclerView.Adapter<FoodsAdapter.FoodAppViewHolder>() {

    private val foodList = mutableListOf<ModelFood>()

    fun updateList(foodsList: List<ModelFood>) {
        foodList.clear()
        foodList.addAll(foodsList)
        notifyDataSetChanged()
    }

    inner class FoodAppViewHolder(
        private val binding: FoodItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ModelFood) {
            binding.foodNameTv.text = model.foodName
            binding.foodPriceTv.text = model.foodPrice
            binding.deleteFoodBtm.setOnClickListener {
                onDeleteFoodClick.invoke(foodList.indexOf(model))
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): FoodsAdapter.FoodAppViewHolder {
        val binding = FoodItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        )
        return FoodAppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodsAdapter.FoodAppViewHolder, position: Int) {
        holder.bind(foodList[position])
    }

    override fun getItemCount(): Int = 10

}