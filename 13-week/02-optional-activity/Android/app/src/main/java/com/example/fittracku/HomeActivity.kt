package com.example.fittracku

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Botón nueva rutina
        val btnNuevaRutina = findViewById<Button>(R.id.btnNuevaRutina)
        btnNuevaRutina.setOnClickListener {
            startActivity(Intent(this, NuevaRutinaActivity::class.java))
        }

        // Botón cámara
        val btnCamara = findViewById<Button>(R.id.btnCamara)
        btnCamara.setOnClickListener {
            startActivity(Intent(this, CamaraActivity::class.java))
        }

        val btnRutinasApi = findViewById<Button>(R.id.btnRutinasApi)
        btnRutinasApi.setOnClickListener {
            startActivity(Intent(this, RutinasApiActivity::class.java))
        }

        val btnMapa = findViewById<Button>(R.id.btnMapa)
        btnMapa.setOnClickListener {
            startActivity(Intent(this, MapaActivity::class.java))
        }  

        // Chips
        val chipTodos = findViewById<TextView>(R.id.chipTodos)
        val chipLunes = findViewById<TextView>(R.id.chipLunes)
        val chipMiercoles = findViewById<TextView>(R.id.chipMiercoles)
        val chipViernes = findViewById<TextView>(R.id.chipViernes)
        val chips = listOf(chipTodos, chipLunes, chipMiercoles, chipViernes)

        // Cards
        val cardPecho = findViewById<LinearLayout>(R.id.cardPecho)
        val cardPierna = findViewById<LinearLayout>(R.id.cardPierna)
        val cardEspalda = findViewById<LinearLayout>(R.id.cardEspalda)

        fun activarChip(chipActivo: TextView, dia: String) {
            chips.forEach { chip ->
                if (chip == chipActivo) {
                    chip.setBackgroundColor(0xFFE94560.toInt())
                    chip.setTextColor(0xFFFFFFFF.toInt())
                } else {
                    chip.setBackgroundColor(0xFF16213E.toInt())
                    chip.setTextColor(0xFFAAAAAA.toInt())
                }
            }
            when (dia) {
                "Todos" -> {
                    cardPecho.visibility = View.VISIBLE
                    cardPierna.visibility = View.VISIBLE
                    cardEspalda.visibility = View.VISIBLE
                }
                "Lunes" -> {
                    cardPecho.visibility = View.VISIBLE
                    cardPierna.visibility = View.GONE
                    cardEspalda.visibility = View.GONE
                }
                "Miércoles" -> {
                    cardPecho.visibility = View.GONE
                    cardPierna.visibility = View.VISIBLE
                    cardEspalda.visibility = View.GONE
                }
                "Viernes" -> {
                    cardPecho.visibility = View.GONE
                    cardPierna.visibility = View.GONE
                    cardEspalda.visibility = View.VISIBLE
                }
            }
        }

        chipTodos.setOnClickListener { activarChip(chipTodos, "Todos") }
        chipLunes.setOnClickListener { activarChip(chipLunes, "Lunes") }
        chipMiercoles.setOnClickListener { activarChip(chipMiercoles, "Miércoles") }
        chipViernes.setOnClickListener { activarChip(chipViernes, "Viernes") }
    }
}