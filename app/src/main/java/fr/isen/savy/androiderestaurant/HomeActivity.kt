package fr.isen.savy.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.isen.savy.androiderestaurant.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrees.setOnClickListener {
            Toast.makeText(this, "Entrées", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@HomeActivity, CategoriesActivity::class.java)

            intent.putExtra("category_name", "Entrées")

            startActivity(intent)
        }

        binding.btnPlats.setOnClickListener {
            Toast.makeText(this, "Plats", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@HomeActivity, CategoriesActivity::class.java)

            intent.putExtra("category_name", "Plats")

            startActivity(intent)
        }
        binding.btnDesserts.setOnClickListener {
            Toast.makeText(this,"Desserts", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@HomeActivity, CategoriesActivity::class.java)

            intent.putExtra("category_name", "Desserts")

            startActivity(intent)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("HomeActivity", "onDestroy() called")
    }
}