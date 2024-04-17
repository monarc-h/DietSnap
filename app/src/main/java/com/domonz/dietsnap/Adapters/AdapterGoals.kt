package com.domonz.dietsnap.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.domonz.dietsnap.DataModels.ModelGoals
import com.domonz.dietsnap.databinding.ItemGoalsBinding

class AdapterGoals(var context: Context, var _data:ArrayList<ModelGoals>):RecyclerView.Adapter<AdapterGoals.ViewHolder>() {
    class ViewHolder(var binding:ItemGoalsBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemGoalsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val goal = _data[position]
            title.text = goal.title
            description.text = goal.description
            image.setImageResource(goal.image)
            progressCount.text = "${goal.progress}%"
            progress.progress = goal.progress
        }
    }

    override fun getItemCount(): Int {
        return _data.size
    }
}