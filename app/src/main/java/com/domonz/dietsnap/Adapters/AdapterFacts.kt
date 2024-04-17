package com.domonz.dietsnap.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.domonz.dietsnap.DataModels.ModelGoals
import com.domonz.dietsnap.databinding.ItemFactsBinding
import com.domonz.dietsnap.databinding.ItemGoalsBinding
import kotlin.random.Random

class AdapterFacts(var context: Context, var _data:ArrayList<String>):RecyclerView.Adapter<AdapterFacts.ViewHolder>() {
    class ViewHolder(var binding:ItemFactsBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemFactsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val fact = _data[position]
            val colors = arrayListOf<String>("#F8B944", "#9013FE", "#EE00FF")
            factText.text = fact
            background.background.setColorFilter(Color.parseColor(colors[Random.nextInt(colors.size)]), PorterDuff.Mode.SRC_IN)
        }
    }

    override fun getItemCount(): Int {
        return _data.size
    }
}