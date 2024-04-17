package com.domonz.dietsnap.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.domonz.dietsnap.DataModels.ModelGoals
import com.domonz.dietsnap.DataModels.ModelSimilarItems
import com.domonz.dietsnap.databinding.ItemFactsBinding
import com.domonz.dietsnap.databinding.ItemGoalsBinding
import com.domonz.dietsnap.databinding.ItemSimilarItemsBinding

class AdapterSimilarItems(var context: Context, var _data:ArrayList<ModelSimilarItems>):RecyclerView.Adapter<AdapterSimilarItems.ViewHolder>() {
    class ViewHolder(var binding:ItemSimilarItemsBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemSimilarItemsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val item = _data[position]
            image.setImageResource(item.image)
            label.text = item.label

        }
    }

    override fun getItemCount(): Int {
        return _data.size
    }
}