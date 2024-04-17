package com.domonz.dietsnap.DataModels

data class Data(
    val _id: String?,
    val api_name: List<String?>?,
    val badge_indicator: String?,
    val credits_url: String?,
    val cuisine: String?,
    val default_unit_serving: String?,
    val description: String?,
    val generic_facts: List<String?>?,
    val health_rating: Double?,
    val image: String?,
    val image_url: String?,
    val ingredients: List<Ingredient?>?,
    val itemtype: String?,
    val name: String?,
    val no_of_servings: Double?,
    val nutrition_facts: String?,
    val nutrition_info: String?,
    val nutrition_info_scaled: List<NutritionInfoScaled?>?,
    val serving_sizes: List<ServingSize?>?,
    val similar_items: List<SimilarItem?>?,
    val type: String?
)