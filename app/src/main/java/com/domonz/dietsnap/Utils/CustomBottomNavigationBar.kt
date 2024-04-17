package com.domonz.dietsnap.Utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.domonz.dietsnap.databinding.ItemBtNavBarBinding


class CustomBottomNavigationBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var GRID_ITEM_COUNT = 2
    private var list:ArrayList<BottomNavData> = arrayListOf()
    private var itemClickListener:ItemClickListener? = null
    private var tabColor:Int = Color.GRAY
    private var tabSelectedColor = Color.BLACK

    init {
        layoutManager = GridLayoutManager(context, GRID_ITEM_COUNT)
    }

    fun build(itemCount:Int){
        GRID_ITEM_COUNT = itemCount
        layoutManager = GridLayoutManager(context, GRID_ITEM_COUNT)
        adapter = AdapterBottomNav(context, list, tabSelectedColor, tabColor ,itemClickListener)
    }

    @SuppressLint("NotifyDataSetChanged")
    public fun add(icon:Int? = null, label:String? = null):CustomBottomNavigationBar{
        list.add(BottomNavData(label, icon))
        adapter?.notifyDataSetChanged()
        return this
    }

    fun setTabSelectedColor(color:Int):CustomBottomNavigationBar{
        tabSelectedColor = color
        return this
    }

    fun setTabColor(color: Int):CustomBottomNavigationBar{
        tabColor = color
        return this
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSelected(position: Int):CustomBottomNavigationBar{
        val myAdapter = adapter as AdapterBottomNav
        myAdapter.selected = position
        myAdapter.notifyDataSetChanged()
        return this
    }

    fun getSelected():Int{
        val myAdapter = adapter as AdapterBottomNav
        return myAdapter.selected
    }

    fun setOnItemClickListener(clickListener: ItemClickListener):CustomBottomNavigationBar{
        itemClickListener = clickListener
        return this
    }

    data class BottomNavData(
        var label:String?,
        var icon:Int?,
    )

    interface  ItemClickListener{
        fun onClick(position: Int, data:Any?)
    }

    class AdapterBottomNav(var context: Context, var _data:ArrayList<BottomNavData>, var selectedColor:Int, var color:Int, var callback:ItemClickListener? = null): RecyclerView.Adapter<AdapterBottomNav.ViewHolder>(){

        var selected:Int = 0

        class ViewHolder(var binding: ItemBtNavBarBinding):RecyclerView.ViewHolder(binding.root) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(ItemBtNavBarBinding.inflate(LayoutInflater.from(context), parent, false))
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.apply {

                val md = _data[position]

                if(md.label == null){
                    label.visibility = View.GONE
                }else{
                    label.text = md.label
                    label.visibility = View.VISIBLE
                }

                if(md.icon == null){
                    icon.visibility = View.GONE
                }else{
                    icon.setImageResource(md.icon!!)
                    icon.visibility = View.VISIBLE
                }

                if(selected == position){
                    YoYo.with(Techniques.RubberBand).duration(100).playOn(holder.itemView)
                    label.setTextColor(selectedColor)
                    icon.setColorFilter(selectedColor)
                }else{
                    label.setTextColor(color)
                    icon.setColorFilter(color, PorterDuff.Mode.SRC_IN)
                }

                holder.itemView.setOnClickListener {
                    selected = position
                    notifyDataSetChanged()
                    callback?.onClick(position, _data[position])
                }
            }
        }

        override fun getItemCount(): Int {
            return _data.size
        }
    }


}

