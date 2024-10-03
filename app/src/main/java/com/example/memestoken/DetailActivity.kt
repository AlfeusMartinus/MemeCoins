package com.example.memestoken

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back_icon)

        val imgPhoto: ImageView = findViewById(R.id.img_item_photo)
        val tvName: TextView =findViewById(R.id.tv_item_name)
        val tvDescription: TextView = findViewById(R.id.tv_item_description)
        val tvMarketName: TextView = findViewById(R.id.tv_additional_info)
        val tvLongDesc: TextView = findViewById(R.id.tv_long_description)
        val btnShare: Button = findViewById(R.id.action_share)

        @Suppress("DEPRECATION")
        val hero = intent.getParcelableExtra<Hero>(EXTRA_HERO)

        hero?.let {
            tvName.text = it.name
            tvDescription.text = it.description
            tvMarketName.text = it.marketName
            tvLongDesc.text = it.longDesc
            Glide.with(this).load(it.photo).into(imgPhoto)
        }

        btnShare.setOnClickListener {
            shareHeroDetails(hero)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareHeroDetails(hero: Hero?) {
        hero?.let {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Check out this meme coin: ${it.name}\n\nDescription: ${it.description}")
                type = "text/plain"
            }

            val chooser = Intent.createChooser(shareIntent, "Share this meme coin using")
            startActivity(chooser)
        }
    }

    companion object {
        const val EXTRA_HERO = "extra_hero"
    }
}