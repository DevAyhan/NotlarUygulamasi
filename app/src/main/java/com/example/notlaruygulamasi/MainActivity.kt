package com.example.notlaruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notlaruygulamasi.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var notlarListe:ArrayList<Notlar>
    private lateinit var adapter:NotlarAdapter

    private lateinit var vt:VeritabaniYardimcisi

    private lateinit var tasarim:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityMainBinding.inflate(layoutInflater)
        setContentView(tasarim.root)

        tasarim.toolbar.title = "Notlar Uygulaması"

        setSupportActionBar(tasarim.toolbar)

        tasarim.rv.setHasFixedSize(true)
        tasarim.rv.layoutManager = LinearLayoutManager(this)

        vt = VeritabaniYardimcisi(this)
        notlarListe = Notlardao().tumNotlar(vt)

        adapter = NotlarAdapter(this,notlarListe)

        tasarim.rv.adapter = adapter

        var toplam = 0

        for (n in notlarListe){
            toplam = toplam + (n.not1+n.not2)/2
        }

        if (toplam != 0){
            tasarim.toolbar.subtitle = "Ortalama: ${toplam/notlarListe.size}"
        }

        tasarim.fab.setOnClickListener {
            startActivity(Intent(this@MainActivity,NotKayitActivity::class.java))
        }



    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}