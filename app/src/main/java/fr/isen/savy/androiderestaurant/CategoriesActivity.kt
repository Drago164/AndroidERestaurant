package fr.isen.savy.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.savy.androiderestaurant.databinding.ActivityCategoriesBinding
import org.json.JSONObject


class CategoriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var category : String
    lateinit var dishes: ArrayList<Dish>

    var categoryName = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoriesBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_categories)


        this.categoryName = intent.getStringExtra("category_name").toString()
        this.title = categoryName

        this.initialiserList()

    }

    fun initialiserList() {

        if (this.categoryName.equals("Entrées")) {

            println("Entrees")
            val arrayEntrees: kotlin.Array<kotlin.String> =
                resources.getStringArray(fr.isen.savy.androiderestaurant.R.array.entrees_list)

            // Lookup the recyclerview in activity layout
            // Initialize contacts
            dishes = Dish.addDish(arrayEntrees)

            this.displayList(dishes)
        } else if (this.categoryName.equals("Plats")) {

            println("Plats")
            val arrayPlats: kotlin.Array<kotlin.String> =
                resources.getStringArray(fr.isen.savy.androiderestaurant.R.array.plats_list)

            // Lookup the recyclerview in activity layout
            // Initialize contacts
            dishes = Dish.addDish(arrayPlats)

            this.displayList(dishes)
        } else {

            println("Desserts")
            val arrayDesserts: kotlin.Array<kotlin.String> =
                resources.getStringArray(fr.isen.savy.androiderestaurant.R.array.desserts_list)

            // Lookup the recyclerview in activity layout
            // Initialize contacts
            dishes = Dish.addDish(arrayDesserts)

            this.displayList(dishes)
        }

    }

    fun displayList(dishes: ArrayList<Dish>) {

        // Create adapter passing in the sample user data
        val adapter : DishesAdapter = DishesAdapter(dishes)

        val listePlats = findViewById<View>(R.id.recycler_plats) as RecyclerView

        // Attach the adapter to the recyclerview to populate items
        listePlats.adapter = adapter
        // Set layout manager to position the items
        listePlats.layoutManager = LinearLayoutManager(this)
        // In the activity or fragment
        adapter.setOnItemClickListener(object : DishesAdapter.OnItemClickListener {
            override fun onItemClick(itemView: View?, position: Int) {
                val name = dishes[position].name
                Toast.makeText(this@CategoriesActivity,"$name was clicked!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@CategoriesActivity,DetailActivity::class.java)
                startActivity(intent)
            }
        })
    }

    private fun loadDishesFromAPI() {
        //API REQUEST
        val jsonObject = JSONObject()
        jsonObject.put("id_shop","1")
        var repas : RepasListe
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->
                val gson = Gson()
                repas = gson.fromJson(response.toString(), RepasListe::class.java)
            },
            { println("Error : Post request") }
        )
        queue.add(jsonRequest)
    }

    private fun handleAPIData(data : String) {
        val dishesResult = Gson().fromJson(data,RepasListe::class.java)
        val dishCategoryFiltered = dishesResult.data.firstOrNull{ it.name_fr == category }

        val adapter = binding.Detail.adapter as CategoryAdapter
        adapter.refreshList(dishCategoryFiltered)
    }

}