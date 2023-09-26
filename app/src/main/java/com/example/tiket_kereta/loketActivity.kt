package com.example.tiket_kereta

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.tiket_kereta.databinding.ActivityLoketBinding
import java.lang.reflect.Array
import java.text.DecimalFormat
import java.text.FieldPosition
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale




class loketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoketBinding
    companion object{
        var nama = "nama"
        var jstiket = "jenis tiket"
        var tanggal = "tanggal"
        var jam = "jam"
    }
    private val jenistiket = arrayListOf<String>(
        "Tiket Ekonomi",
        "Tiket Bisnis",
        "Tiket Eksekutif",
        "Tiket Sleeper",
        "Tiket Premium"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoketBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            val adapterCountry = ArrayAdapter(this@loketActivity, android.R.layout.simple_spinner_dropdown_item,jenistiket)
            adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            jnsspinner.adapter = adapterCountry

            jnsspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, view: View, position: Int, p3: Long) {
                    Toast.makeText(this@loketActivity,
                    jenistiket[position],
                    Toast.LENGTH_LONG
                    )
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            btnhomepage.setOnClickListener {

                if(editText.text.toString() != ""&& jnsspinner.selectedItem.toString() != ""&& dtpicker.text != "MM-dd-yyyy" && timepicker.text!="HH : MM") {
                    val konfirmasi = "Tiket Anda telah dipesan dengan detail berikut:\n" +
                            "Nama: ${editText.text.toString()}\n" +
                            "Jenis Tiket: ${jnsspinner.selectedItem.toString()}\n" +
                            "Tanggal: ${dtpicker.text}\n" +
                            "Jam: ${timepicker.text}"
                    Toast.makeText(this@loketActivity, konfirmasi, Toast.LENGTH_LONG).show()
                    val intentToHomepageActivity =
                        Intent(this@loketActivity, HomepageActivity::class.java)
                    intentToHomepageActivity.putExtra(nama, editText.text.toString())
//                    intentToHomepageActivity.putExtra(jenistiket,  jnsspinner.selectedItem.toString())
                    intentToHomepageActivity.putExtra(tanggal, dtpicker.text)
                    intentToHomepageActivity.putExtra(jam, timepicker.text)
                    startActivity(intentToHomepageActivity)

                }else{
                    Toast.makeText(this@loketActivity, "KOLOM TIDAK BOLEH KOSONG !", Toast.LENGTH_SHORT).show()
                }
            }

        }
        val dateTxt = findViewById<TextView>(R.id.dtpicker)
        val cal = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthofyear, dayofMonth ->
            cal.set(Calendar.YEAR,year)
            cal.set(Calendar.MONTH,monthofyear)
            cal.set(Calendar.DAY_OF_MONTH,dayofMonth)
            dateFormat(cal.time)
        }
        dateTxt.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        val timepicker = findViewById<TextView>(R.id.timepicker)
        val currenTime = Calendar.getInstance()
        val hour = currenTime.get(Calendar.HOUR_OF_DAY)
        val minute =currenTime.get(Calendar.MINUTE)
        val tmpicker = TimePickerDialog(this, {_,hourofDay,min ->
            timerFormat(hourofDay, min)
        },hour,minute,false)
        timepicker.setOnClickListener {
            tmpicker.show()
        }
    }

    private fun dateFormat(yourDate: Any) {
        val format1 = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(yourDate)

        val dateTxt = findViewById<TextView>(R.id.dtpicker)
        dateTxt.setText(
            format1 + "\n"
        )
    }

    private fun  timerFormat(hourofDay: Int, min: Int){
        val modifierdHour = getHourPmAm(hourofDay)
        val pmam = if (hourofDay>11 )"PM" else "AM"
        val numberFormat = DecimalFormat("00")
        val timeTxt = findViewById<TextView>(R.id.timepicker)
        timeTxt.setText(
            "${numberFormat.format(modifierdHour)}:${numberFormat.format(min)}$pmam"
        )
    }
    private fun getHourPmAm(hourofDay: Int):Int{
        var modifiedHour = if (hourofDay > 11) hourofDay-12 else hourofDay
        if (modifiedHour == 0){
            modifiedHour = 12
        }
        return modifiedHour
    }
}
