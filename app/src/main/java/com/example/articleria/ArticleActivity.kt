package com.example.articleria

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Set the content view before accessing views
        setContentView(R.layout.activity_article)

        // Retrieve views
        val tvName = findViewById<TextView>(R.id.tv_item_name)
        val tvDescription = findViewById<TextView>(R.id.tv_item_description)
        val imgPhoto = findViewById<ImageView>(R.id.img_item_photo)

        // Retrieve intent extras
        val plantName = intent.getStringExtra("PLANT_NAME")
        val plantDescription = intent.getStringExtra("PLANT_DESCRIPTION")
        val plantPhoto = intent.getIntExtra("PLANT_PHOTO", 0)

        // Set data to views if the individual extras are passed
        if (plantName != null && plantDescription != null) {
            tvName.text = plantName
            tvDescription.text = plantDescription
            imgPhoto.setImageResource(plantPhoto)
        }

        // Optionally, retrieve the Plant object and set its data if it exists
        val plant = intent.getParcelableExtra<Plant>("key_plant")
        if (plant != null) {
            tvName.text = plant.name
            tvDescription.text = plant.description
            imgPhoto.setImageResource(plant.photo)
        }

        // Handle system insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
