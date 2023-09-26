package com.example.tiket_kereta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiket_kereta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            btnhome.setOnClickListener {
                val intentToloketActivity = Intent(this@MainActivity,loketActivity::class.java)
                startActivity(intentToloketActivity)
            }
        }
    }
}