package com.example.fittracku

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class CamaraActivity : AppCompatActivity() {

    private lateinit var ivFoto: ImageView
    private lateinit var tvEstado: TextView

    private val pedirPermisoCamara = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { concedido ->
        if (concedido) {
            abrirCamara()
        } else {
            tvEstado.text = "Permiso de cámara denegado. Ve a Configuración para habilitarlo."
            tvEstado.setTextColor(0xFFE94560.toInt())
        }
    }

    private val tomarFoto = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            ivFoto.setImageBitmap(bitmap)
            tvEstado.text = "Foto tomada correctamente"
            tvEstado.setTextColor(0xFF4CAF50.toInt())
        } else {
            tvEstado.text = "No se tomó ninguna foto"
            tvEstado.setTextColor(0xFFAAAAAA.toInt())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camara)

        ivFoto = findViewById(R.id.ivFoto)
        tvEstado = findViewById(R.id.tvEstado)
        val btnTomarFoto = findViewById<Button>(R.id.btnTomarFoto)

        btnTomarFoto.setOnClickListener {
            tvEstado.text = "Abriendo cámara..."
            tvEstado.setTextColor(0xFFAAAAAA.toInt())
            verificarPermiso()
        }
    }

    private fun verificarPermiso() {
        when {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                abrirCamara()
            }
            else -> {
                pedirPermisoCamara.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun abrirCamara() {
        tomarFoto.launch(null)
    }
}