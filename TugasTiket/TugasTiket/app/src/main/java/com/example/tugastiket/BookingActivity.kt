package com.example.tugastiket

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class BookingActivity : AppCompatActivity() {
    private lateinit var spinnerTicketType: Spinner
    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker
    private lateinit var btnBookTicket: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking) 

        spinnerTicketType = findViewById(R.id.spinnerTicketType)
        datePicker = findViewById(R.id.datePicker)
        timePicker = findViewById(R.id.timePicker)
        btnBookTicket = findViewById(R.id.btnBookTicket)

        val ticketTypes = arrayOf("Pesawat", "Kereta", "Bus", "Kapal Laut")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ticketTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTicketType.adapter = adapter

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH), null)
        timePicker.setIs24HourView(true)
        timePicker.hour = calendar.get(Calendar.HOUR_OF_DAY)
        timePicker.minute = calendar.get(Calendar.MINUTE)

        spinnerTicketType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                ticketTypes[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        btnBookTicket.setOnClickListener {
            val selectedTicketType = spinnerTicketType.selectedItem.toString()
            val selectedDate = dateFormat.format(
                calendar.timeInMillis + (datePicker.dayOfMonth - 1) * 24 * 60 * 60 * 1000
            )
            val selectedTime = timeFormat.format(calendar.timeInMillis)

            val message = "Anda telah memesan tiket $selectedTicketType pada tanggal $selectedDate, pukul $selectedTime."
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}
