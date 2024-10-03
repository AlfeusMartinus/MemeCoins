package com.example.memestoken

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        rvHeroes = findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getListHeroes(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_coin_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataMarketName = resources.getStringArray(R.array.data_name_coin)
        val dataLongDesc = resources.getStringArray(R.array.data_long_desc)
        val listHero = ArrayList<Hero>()
        for (i in dataName.indices) {
            val hero = Hero(dataName[i], dataDescription[i], dataPhoto[i], dataMarketName[i], dataLongDesc[i])
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        rvHeroes.adapter = listHeroAdapter


        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_HERO, data)
                startActivity(intent)
            }
        })

    }
}