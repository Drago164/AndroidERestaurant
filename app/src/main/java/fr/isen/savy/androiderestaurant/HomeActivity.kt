package fr.isen.savy.androiderestaurant


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.savy.androiderestaurant.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this@HomeActivity, CategoryActivity::class.java)

        binding.buttonStarter.setOnClickListener {
            intent.putExtra("categoryName", "Entrées")
            val mealList = resources.getStringArray(R.array.entries_list).toList() as ArrayList<String>
            intent.putExtra("List_Meal", mealList)
            startActivity(intent)
        }

        binding.buttonMainCourse.setOnClickListener {
            intent.putExtra("categoryName", "Plats")
            val mealList = resources.getStringArray(R.array.main_courses_list).toList() as ArrayList<String>
            intent.putExtra("List_Meal", mealList)
            startActivity(intent)
        }

        binding.buttonDessert.setOnClickListener {
            intent.putExtra("categoryName", "Desserts")
            val mealList = resources.getStringArray(R.array.desserts_list).toList() as ArrayList<String>
            intent.putExtra("List_Meal", mealList)
            startActivity(intent)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("HomeActivity", "L'activité Home a été détruite")
    }
}