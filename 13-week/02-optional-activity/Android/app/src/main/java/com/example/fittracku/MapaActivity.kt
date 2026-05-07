package com.example.fittracku

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MapaActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var tvEstado: TextView
    private lateinit var tvUbicacion: TextView
    private lateinit var btnObtenerUbicacion: Button

    companion object {
        private const val PERMISO_UBICACION = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        tvEstado = findViewById(R.id.tvEstado)
        tvUbicacion = findViewById(R.id.tvUbicacion)
        btnObtenerUbicacion = findViewById(R.id.btnObtenerUbicacion)

        btnObtenerUbicacion.setOnClickListener {
            tvEstado.text = "Obteniendo ubicación..."
            tvEstado.setTextColor(0xFFAAAAAA.toInt())
            verificarPermiso()
        }
    }

    private fun verificarPermiso() {
        when {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                obtenerUbicacion()
            }
            else -> {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISO_UBICACION
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISO_UBICACION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtenerUbicacion()
            } else {
                tvEstado.text = "Permiso de ubicación denegado. Ve a Configuración para habilitarlo."
                tvEstado.setTextColor(0xFFE94560.toInt())
                tvUbicacion.text = "Sin ubicación disponible"
            }
        }
    }

    private fun obtenerUbicacion() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) return

        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                tvUbicacion.text = "Latitud: ${location.latitude}\nLongitud: ${location.longitude}"
                tvEstado.text = "Ubicación obtenida correctamente"
                tvEstado.setTextColor(0xFF4CAF50.toInt())
            } else {
                tvEstado.text = "No se pudo obtener la ubicación. Activa el GPS."
                tvEstado.setTextColor(0xFFE94560.toInt())
                tvUbicacion.text = "Sin ubicación disponible"
            }
        }
    }
}