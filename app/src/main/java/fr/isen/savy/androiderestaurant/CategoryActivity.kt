package fr.isen.savy.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.savy.androiderestaurant.databinding.ActivityCategoryBinding
import org.json.JSONObject
import com.google.gson.Gson
import fr.isen.savy.androiderestaurant.model.DataResult
import fr.isen.savy.androiderestaurant.model.Items

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        category = intent.getStringExtra("categoryName") ?: ""
        val actionBar = supportActionBar
        actionBar?.title = category

        binding.categoryList.layoutManager = LinearLayoutManager(this)
        binding.categoryList.adapter = DishAdapter(arrayListOf()) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("detail", it)
            startActivity(intent)
        }
        loadDishesFromAPI()

    }
    private fun loadDishesFromAPI(){
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            {
                Log.w("CategoryActivity", "response : $it")
                handleAPIData(it.toString())
            },
            {
                Log.w("CategoryActivity", "error : $it")
            })
        Volley.newRequestQueue(this).add(jsonRequest)
    }


    private fun handleAPIData(data: String){
        val dishesResult = Gson().fromJson(data, DataResult::class.java)
        val dishCategory = dishesResult.data.firstOrNull { it.nameFr == category }

        val adapter = binding.categoryList.adapter as DishAdapter
        adapter.refreshList(dishCategory?.items as ArrayList<Items>)
    }
}