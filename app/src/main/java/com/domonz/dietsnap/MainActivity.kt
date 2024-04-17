package com.domonz.dietsnap

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.domonz.dietsnap.Adapters.MainPagerAdapter
import com.domonz.dietsnap.Utils.CustomBottomNavigationBar
import com.domonz.dietsnap.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        next()
    }


    fun next(){
        binding.apply {

            val bottomNavBar = bottomNavBar
                .setTabSelectedColor(Color.BLACK)
                .setTabColor(Color.parseColor("#949494"))
                .add(R.drawable.activity, "Activity")
                .add(R.drawable.goals, "Goals")
                .add(R.drawable.camera, "Camera")
                .add(R.drawable.feed, "Feed")
                .add(R.drawable.profile, "Profile")
                .setOnItemClickListener(object:CustomBottomNavigationBar.ItemClickListener{
                    override fun onClick(position: Int, data: Any?) {
                        viewpager.currentItem = position
                    }
                })
            bottomNavBar.build(5)
            viewpager.adapter = MainPagerAdapter(supportFragmentManager, 5)
            viewpager.addOnPageChangeListener(object:OnPageChangeListener{
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                }

                override fun onPageSelected(position: Int) {
                    bottomNavBar.setSelected(position)
                }

                override fun onPageScrollStateChanged(state: Int) {

                }
            })

        }
    }

}