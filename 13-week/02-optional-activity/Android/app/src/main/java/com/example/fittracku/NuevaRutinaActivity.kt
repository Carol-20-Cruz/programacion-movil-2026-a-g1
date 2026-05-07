package com.example.fittracku

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class NuevaRutinaActivity : AppCompatActivity() {

    private val apiService = ApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_rutina)

        val dias = arrayOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
        val spinner = findViewById<Spinner>(R.id.spinnerDia)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setSelection(0)

        val btnGuardar = findViewById<Button>(R.id.btnGuardarRutina)
        val etNombre = findViewById<EditText>(R.id.etNombreRutina)
        val etEjercicios = findViewById<EditText>(R.id.etNumEjercicios)
        val tvError = findViewById<TextView>(R.id.tvError)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val ejercicios = etEjercicios.text.toString().trim()
            val dia = spinner.selectedItem.toString()

            if (nombre.isEmpty()) {
                etNombre.error = "Escribe el nombre de la rutina"
                return@setOnClickListener
            }
            if (ejercicios.isEmpty()) {
                etEjercicios.error = "Escribe el número de ejercicios"
                return@setOnClickListener
            }

            // Deshabilitar botón mientras guarda
            btnGuardar.isEnabled = false
            btnGuardar.text = "Guardando..."

            thread {
                val exito = apiService.createRutina(nombre, ejercicios, dia)

                runOnUiThread {
                    btnGuardar.isEnabled = true
                    btnGuardar.text = "Guardar Rutina"

                    if (exito) {
                        Toast.makeText(this, "Rutina guardada correctamente", Toast.LENGTH_SHORT).show()
                        val resultIntent = Intent()
                        resultIntent.putExtra("nombre", nombre)
                        resultIntent.putExtra("ejercicios", ejercicios)
                        resultIntent.putExtra("dia", dia)
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    } else {
                        tvError.visibility = android.view.View.VISIBLE
                        tvError.text = "Error al guardar. Verifica tu conexión."
                    }
                }
            }
        }
    }
}