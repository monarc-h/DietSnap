package com.domonz.dietsnap

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.domonz.dietsnap.Adapters.AdapterFacts
import com.domonz.dietsnap.Adapters.AdapterSimilarItems
import com.domonz.dietsnap.Api.ApiClient
import com.domonz.dietsnap.Api.ApiInterface
import com.domonz.dietsnap.DataModels.FoodInfo
import com.domonz.dietsnap.DataModels.ModelSimilarItems
import com.domonz.dietsnap.databinding.ActivityFoodDetailsBinding
import com.google.gson.Gson
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodDetailsActivity : AppCompatActivity() {

    lateinit var binding:ActivityFoodDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        next()
    }

    fun next(){

        binding.apply {
            getFoodInfo()
            binding.btnBack.setOnClickListener {
                finish()
            }

            binding.loadingLayout.btnBackL.setOnClickListener {
                finish()
            }
        }

    }

    fun getFoodInfo(){
        MainScope().launch {
            binding.loadingLayout.root.visibility = View.VISIBLE
            ApiClient.getClient()?.create(ApiInterface::class.java)!!.getFoodInfo().enqueue(
                object:Callback<Any>{
                    override fun onResponse(call: Call<Any>, response: Response<Any>) {
                        if(response.code() == 200){
                            binding.loadingLayout.root.visibility = View.GONE
                            val obj = Gson().fromJson(Gson().toJson(response.body()), FoodInfo::class.java)
                            handleResponse(obj)
                        }else{
                            Toast.makeText(this@FoodDetailsActivity, "Operation not permitted", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<Any>, t: Throwable) {
                        Toast.makeText(this@FoodDetailsActivity, "Failed to connect with the server", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }

    fun handleResponse(response:FoodInfo){

        binding.apply {

            val food = response.data

            title.text = food?.name
            healthScore.text = food?.health_rating.toString()
            description.text = food?.description

            for (i in 0 until food?.nutrition_info_scaled!!.size) {
                val perServe = food.nutrition_info_scaled[i]
                if (i >= food.serving_sizes!!.size) {
                    break
                } else {
                    val per250Serve = food.serving_sizes[i]
                    tableLayout.addView(
                        getTableRow(
                            perServe?.name.toString(),
                            "${String.format("%.1f",perServe?.value)}${perServe?.units.toString()}",
                            if (per250Serve == null) "0" else "${String.format("%.1f",per250Serve.value)}${per250Serve.units}"
                        )
                    )
                    continue
                }
            }
            val facts = arrayListOf<String>()
            for( i in food.generic_facts!!){
                facts.add(i!!)
            }
            recFacts.adapter = AdapterFacts(this@FoodDetailsActivity, facts)

            val similarItems = arrayListOf<ModelSimilarItems>()

            for(i in food.similar_items!!){
                similarItems.add(ModelSimilarItems(R.drawable.chicken_fried_resized, i?.name!!))
            }

            recSimilarItems.adapter = AdapterSimilarItems(this@FoodDetailsActivity, similarItems)

        }


    }


    fun getTableRow(title:String, perServe:String, per250gm:String): View {
        val tableRow = TableRow(this)
        tableRow.addView(getTableRowText(title, false))
        tableRow.addView(getTableRowText(perServe, true))
        tableRow.addView(getTableRowText(per250gm, true))
        return tableRow
    }

    fun getTableRowText(label:String, center:Boolean):View{
        val textView1 = TextView(this)
        textView1.text = label
        textView1.typeface = resources.getFont(R.font.bevietnam_semi_bold)
        textView1.setTextColor(Color.BLACK)
        if(center) {
            textView1.gravity = Gravity.CENTER
        }
        textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP, resources.getDimension(com.intuit.ssp.R.dimen._4ssp))
        textView1.setPadding(
            resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._10sdp),
            resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._5sdp),
            resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._10sdp),
            resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._5sdp)
        )
        textView1.layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.MATCH_PARENT
        )
        textView1.layoutParams.width = TableRow.LayoutParams.MATCH_PARENT
        textView1.layoutParams.height = TableRow.LayoutParams.MATCH_PARENT

        return textView1

    }
}