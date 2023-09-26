package com.example.tiket_kereta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiket_kereta.databinding.ActivityHomepageBinding
import com.example.tiket_kereta.databinding.ActivityLoketBinding

class HomepageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomepageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val nalue = intent.getStringExtra(loketActivity.nama)
//        val jenilue = intent.getStringExtra(loketActivity.)
        val jadwalue = intent.getStringExtra(loketActivity.tanggal)
        val waktulue = intent.getStringExtra(loketActivity.jam)

        with(binding){
            hline12.text = nalue
            hline32.text = jadwalue
            hline42.text = waktulue

            appCompatButton.setOnClickListener {
                val intentToMainActivity2 = Intent(this@HomepageActivity,MainActivity::class.java)
                startActivity(intentToMainActivity2)
            }
        }
    }
}