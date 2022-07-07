package com.example.notlaruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.notlaruygulamasi.databinding.ActivityMainBinding
import com.example.notlaruygulamasi.databinding.ActivityNotKayitBinding
import com.google.android.material.snackbar.Snackbar

class NotKayitActivity : AppCompatActivity() {

    private lateinit var vt:VeritabaniYardimcisi

    private  lateinit var tasarim:ActivityNotKayitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityNotKayitBinding.inflate(layoutInflater)
        setContentView(tasarim.root)

        vt = VeritabaniYardimcisi(this)

        tasarim.toolbarNotKayit.title = "Not Kayıt"
        setSupportActionBar(tasarim.toolbarNotKayit)

        tasarim.buttonKaydet.setOnClickListener {

            val ders_adi = tasarim.editTextDers.text.toString().trim()
            val not1 = tasarim.editTextNot1.text.toString().trim()
            val not2 = tasarim.editTextNot2.text.toString().trim()

            if (TextUtils.isEmpty(ders_adi)){
                Snackbar.make(tasarim.toolbarNotKayit,"Ders adı giriniz",Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(not1)){
                Snackbar.make(tasarim.toolbarNotKayit,"1. Notu giriniz",Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(not2)){
                Snackbar.make(tasarim.toolbarNotKayit,"2. Notu giriniz",Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            Notlardao().notEkle(vt,ders_adi,not1.toInt(),not2.toInt())

            startActivity(Intent(this@NotKayitActivity,MainActivity::class.java))
            finish()
        }
    }
}