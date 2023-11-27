package com.example.foodApp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodApp.models.ModelFood
import com.example.foodapp.R
import com.example.foodapp.databinding.BasketItemBinding

class BasketAdapter(
    private val onShareToBasket:(model:ModelFood) -> Unit,
    private val onDeleteBtn:(index:Int) -> Unit
) : RecyclerView.Adapter<BasketAdapter.FoodAppViewHolder>(){

    private val foodList = mutableListOf<ModelFood>()

    fun updateList(foodsList: List<ModelFood>) {
        foodList.clear()
        foodList.addAll(foodsList)
        notifyDataSetChanged()
    }

    inner class FoodAppViewHolder(
       private val binding: BasketItemBinding
   ): RecyclerView.ViewHolder(binding.root) {


       fun bind(model: ModelFood){
           binding.deleteFoodBtm.setOnClickListener{
               onShareToBasket.invoke(model)
               binding.foodNameTv.text = model.foodName
               binding.foodPriceTv.text = model.foodPrice
           }
           binding.deleteFoodBtm.setOnClickListener{
               onDeleteBtn.invoke(foodList.indexOf(model))
           }
       }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasketAdapter.FoodAppViewHolder {
        val binding = BasketItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.basket_item, parent, false)
        )
        return FoodAppViewHolder(binding)
    }

    override fun getItemCount(): Int = foodList.size

    override fun onBindViewHolder(holder: BasketAdapter
        .FoodAppViewHolder, position: Int) {
        holder.bind(foodList[position])

    }
}