package com.example.notlaruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import com.example.notlaruygulamasi.databinding.ActivityDetayBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detay.*

class DetayActivity : AppCompatActivity() {

    private lateinit var not:Notlar
    private lateinit var vt:VeritabaniYardimcisi

    private lateinit var tasarim:ActivityDetayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(tasarim.root)


        tasarim.toolbarDetay.title = "Not Detay"
        setSupportActionBar(tasarim.toolbarDetay)

        vt = VeritabaniYardimcisi(this)

        not = intent.getSerializableExtra("nesne") as Notlar

        tasarim.editTextDers.setText(not.ders_adi)
        tasarim.editTextNot1.setText((not.not1).toString())
        tasarim.editTextNot2.setText((not.not2).toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_sil -> {
                Snackbar.make(toolbarDetay,"Silinsin mi?",Snackbar.LENGTH_INDEFINITE)
                    .setAction("Evet"){

                        Notlardao().notSil(vt,not.not_id)

                        startActivity(Intent(this@DetayActivity,MainActivity::class.java))
                        finish()
                    }.show()
                return true
            }

            R.id.action_duzenle -> {

                val ders_adi = tasarim.editTextDers.text.toString().trim()
                val not1 = tasarim.editTextNot1.text.toString().trim()
                val not2 = tasarim.editTextNot2.text.toString().trim()

                if (TextUtils.isEmpty(ders_adi)){
                    Snackbar.make(tasarim.root,"Ders adı giriniz",Snackbar.LENGTH_LONG).show()
                    return false
                }
                if (TextUtils.isEmpty(not1)){
                    Snackbar.make(tasarim.root,"1. Notu giriniz",Snackbar.LENGTH_LONG).show()
                    return false
                }
                if (TextUtils.isEmpty(not2)){
                    Snackbar.make(tasarim.root,"2. Notu giriniz",Snackbar.LENGTH_LONG).show()
                    return false
                }

                Notlardao().notGuncelle(vt,not.not_id,ders_adi,not1.toInt(),not2.toInt())

                startActivity(Intent(this@DetayActivity,MainActivity::class.java))
                finish()
                return true
            }
            else -> return false

        }

        return super.onOptionsItemSelected(item)
    }
}