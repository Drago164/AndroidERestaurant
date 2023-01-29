package fr.isen.savy.androiderestaurant

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import fr.isen.savy.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.savy.androiderestaurant.model.Items
import java.lang.StringBuilder

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var dataItems: Items
    private lateinit var nameDish : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)


        val title = intent.getStringExtra("title")
        supportActionBar?.title = title

        dataItems = intent.getSerializableExtra("detail") as Items
        setContentView(binding.root)

        nameDish = dataItems.nameFr.toString()

        val actionBar = supportActionBar
        actionBar?.title = nameDish

        val textView = findViewById<TextView>(R.id.titleDetail)
        val text = nameDish
        textView.text = text

        if (dataItems.images.isNotEmpty()) {
            Picasso.get().load(dataItems.images[0]).into(binding.imageDetail);
        }

        val ingredients = dataItems.ingredients

        if (dataItems.ingredients.isNotEmpty()){
            val ingredientsString = StringBuilder()
            ingredients.forEach { ingredients ->
                ingredientsString.append(ingredients.nameFr)
                ingredientsString.append("\n")
            }
            binding.descriptionDetail.text = ingredientsString
        }

        val prix = dataItems.prices
        val priceString = StringBuilder()
        val priceunique = dataItems.prices[0].price?.toDouble()

        var addition = 0

        binding.addDetail.setOnClickListener {
            addition++
            binding.quantityDetail.text = Editable.Factory.getInstance().newEditable(addition.toString())

            if (dataItems.prices.isNotEmpty()) {
                prix.forEach { prix ->
                    priceString.append(prix.price)
                    priceString.append("$")
                }
                val number = addition * priceunique!!
                binding.priceDetail.text = "Total : $number"
            }
        }
        binding.subDetail.setOnClickListener {
            addition--
            binding.quantityDetail.setText(
                Editable.Factory.getInstance().newEditable(addition.toString())
            )
            val number = addition * priceunique!!
            binding.priceDetail.text = "Total : $number"
        }

        binding.priceDetail.setOnClickListener {
            val total = addition * priceunique!!
            val data = ("Article : $text ; Number : $addition ; Price Unite: $priceunique ; Total : $total")
            val fileOutputStream = openFileOutput("pannier.json", Context.MODE_PRIVATE)

            fileOutputStream.write(data.toByteArray())
            fileOutputStream.close()

            Toast.makeText(this,"Article ajouté au panier", Toast.LENGTH_LONG).show()
        }
    }
}