package com.example.b14calendarpicker

import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var calendario: CalendarView
    private lateinit var fechaSeleccionada: EditText
    private lateinit var fechaBuscada: EditText
    private lateinit var botonBuscar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //referenciar por id
        calendario = findViewById(R.id.calendarView)
        fechaSeleccionada = findViewById(R.id.editTextDate)
        fechaBuscada = findViewById(R.id.editText)
        botonBuscar = findViewById(R.id.button)

        calendario.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val fechaSeleccionadaStr = "$dayOfMonth/${month+1}/$year"
            fechaSeleccionada.setText(fechaSeleccionadaStr)
        }

        botonBuscar.setOnClickListener {
            val fechaBuscadaStr = fechaBuscada.text.toString()

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).apply {
                isLenient = false
            }

            val fechaParsed = sdf.parse(fechaBuscadaStr)
            if (fechaParsed != null) {
                calendario.date = fechaParsed.time
            }
        }
    }
}