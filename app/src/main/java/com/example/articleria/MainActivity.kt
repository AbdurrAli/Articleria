package com.example.articleria

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import com.example.articleria.R

class MainActivity : AppCompatActivity() {
    private lateinit var rvPlant: RecyclerView
    private val list = ArrayList<Plant>()

    private lateinit var imageProfileView: CircleImageView
    private var imageProfileUrl =  "https://w.wallhaven.cc/full/vq/wallhaven-vq268p.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imageProfileView = findViewById(R.id.img_profile)

        Glide.with(this)
            .load(imageProfileUrl)
            .into(imageProfileView)

        rvPlant = findViewById(R.id.rv_plant)
        rvPlant.setHasFixedSize(true)

        list.addAll(getListPlant())
        showRecyclerList()
    }

    private fun getListPlant(): ArrayList<Plant> {
        val plantNames = resources.getStringArray(R.array.data_name)
        val plantDescriptions = resources.getStringArray(R.array.data_description)
        val plantPhotos = resources.obtainTypedArray(R.array.data_photo)

        val plants = ArrayList<Plant>()
        for (i in plantNames.indices) {
            val plant = Plant(
                name = plantNames[i],
                description = plantDescriptions[i],
                photo = plantPhotos.getResourceId(i, -1)
            )
            plants.add(plant)
        }
        plantPhotos.recycle() // Recycle the TypedArray to free up resources
        return plants
    }

    private fun showRecyclerList() {
        rvPlant.layoutManager = LinearLayoutManager(this)
        val listPlantAdapter = ListPlantAdapter(list)
        rvPlant.adapter = listPlantAdapter
    }
}
