package com.domonz.dietsnap.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.domonz.dietsnap.Adapters.AdapterCurrentProgress
import com.domonz.dietsnap.Adapters.AdapterGoals
import com.domonz.dietsnap.DataModels.ModelGoals
import com.domonz.dietsnap.DataModels.ModelProgress
import com.domonz.dietsnap.FoodDetailsActivity
import com.domonz.dietsnap.R
import com.domonz.dietsnap.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class HomeFragment : Fragment() {

    lateinit var _binding:FragmentHomeBinding
    val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            recGoals.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recGoals.adapter = AdapterGoals(requireContext(), ArrayList<ModelGoals>().apply {
                add(ModelGoals(R.drawable.body_builder, "Keto Weight loss plan", "Current Major Goal", 73))
            })

            with(progressViewPager){
                adapter = AdapterCurrentProgress(requireContext(), ArrayList<ModelProgress>().apply {
                    add(ModelProgress(0, 0, 0, 0, 1000, 45, "4/7", formatDateFromTimestamp(System.currentTimeMillis()), "Today"))
                    add(ModelProgress(45, 294, 100, 400, 1300, 89, "6/7", formatDateFromTimestamp(getPreviousDayTimestamp()), "Yesterday"))
                })
                dotsIndicator.attachTo(this)
            }

            findDiets.setOnClickListener {
                startActivity(Intent(requireContext(), FoodDetailsActivity::class.java))
            }


        }

    }



    fun getPreviousDayTimestamp(): Long {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        return calendar.timeInMillis
    }

    fun formatDateFromTimestamp(timestamp: Long): String {
        val date = Date(timestamp)
        val dateFormat = SimpleDateFormat("EEEE, d", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())
        var formattedDate = dateFormat.format(date)
        var formattedMonth = monthFormat.format(date)
        val dayOfMonth = Calendar.getInstance()
        dayOfMonth.time = date
        val day = dayOfMonth.get(Calendar.DAY_OF_MONTH)
        formattedDate += when {
            day in 11..13 -> "th"
            day % 10 == 1 -> "st"
            day % 10 == 2 -> "nd"
            day % 10 == 3 -> "rd"
            else -> "th"
        }

        return "$formattedDate $formattedMonth"
    }



}