package com.example.fittracku

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class RutinasApiActivity : AppCompatActivity() {

    private lateinit var tvEstado: TextView
    private lateinit var listaRutinas: LinearLayout
    private lateinit var progressBar: ProgressBar
    private val apiService = ApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutinas_api)

        tvEstado = findViewById(R.id.tvEstado)
        listaRutinas = findViewById(R.id.listaRutinas)
        progressBar = findViewById(R.id.progressBar)

        val btnRecargar = findViewById<Button>(R.id.btnRecargar)
        btnRecargar.setOnClickListener { cargarRutinas() }

        cargarRutinas()
    }

    private fun cargarRutinas() {
        // Estado loading
        runOnUiThread {
            progressBar.visibility = View.VISIBLE
            tvEstado.text = "Cargando rutinas..."
            tvEstado.setTextColor(0xFFAAAAAA.toInt())
            listaRutinas.removeAllViews()
        }

        thread {
            val rutinas = apiService.getRutinas()

            runOnUiThread {
                progressBar.visibility = View.GONE

                if (rutinas.isEmpty()) {
                    // Estado vacío o error
                    tvEstado.text = "No se pudieron cargar las rutinas. Verifica tu conexión."
                    tvEstado.setTextColor(0xFFE94560.toInt())
                } else {
                    // Estado éxito
                    tvEstado.text = "Rutinas cargadas correctamente (${rutinas.size})"
                    tvEstado.setTextColor(0xFF4CAF50.toInt())

                    rutinas.forEach { rutina ->
                        val card = LinearLayout(this).apply {
                            orientation = LinearLayout.VERTICAL
                            setBackgroundColor(0xFF16213E.toInt())
                            setPadding(48, 40, 48, 40)
                            val params = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )
                            params.bottomMargin = 24
                            layoutParams = params
                        }

                        val tvNombre = TextView(this).apply {
                            text = rutina["nombre"]
                            textSize = 16f
                            setTextColor(0xFFFFFFFF.toInt())
                            setTypeface(null, android.graphics.Typeface.BOLD)
                            val p = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            )
                            p.bottomMargin = 12
                            layoutParams = p
                        }

                        val tvDetalle = TextView(this).apply {
                            text = "${rutina["ejercicios"]} ejercicios  •  ${rutina["dia"]}"
                            textSize = 13f
                            setTextColor(0xFFAAAAAA.toInt())
                        }

                        card.addView(tvNombre)
                        card.addView(tvDetalle)
                        listaRutinas.addView(card)
                    }
                }
            }
        }
    }
}