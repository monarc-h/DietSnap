package com.domonz.dietsnap.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.domonz.dietsnap.DataModels.ModelProgress
import com.domonz.dietsnap.R
import com.domonz.dietsnap.databinding.ItemProgressBinding

class AdapterCurrentProgress(var context: Context, var data: ArrayList<ModelProgress>) :
    PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var viewItem = ItemProgressBinding.inflate(LayoutInflater.from(context), container, false)
        setData(viewItem, position)
        container.addView(viewItem.root)
        return viewItem.root
    }

    override fun getCount(): Int {
        return data.size
    }

    @SuppressLint("SetTextI18n")
    fun setData(binding: ItemProgressBinding, position: Int) {
        binding.apply {
            val md = data[position]
            dietProgress.max = md.dietGoal
            eProgress.max = md.exerciseGoal

            dietTarget.text = "out of ${md.dietGoal}"
            exTarget.text = "out of ${md.exerciseGoal}"

            dietPts.text = md.dietProgress.toString()
            exPts.text = md.exerciseProgress.toString()

            if(md.dietGoal == 0 && md.exerciseGoal == 0){
                dietProgress.progress = 1
                eProgress.progress = 1
                layoutData.visibility = View.GONE
                textSetGoal.visibility = View.VISIBLE
            }else{
                dietProgress.progress = md.dietProgress
                eProgress.progress = md.exerciseProgress
                textSetGoal.visibility = View.GONE
                layoutData.visibility = View.VISIBLE
            }

            calText.text = md.cal.toString()
            healthScoreText.text = md.healthScore.toString()
            daysText.text = md.time
            day.text = md.day
            date.text = md.date
        }

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}
