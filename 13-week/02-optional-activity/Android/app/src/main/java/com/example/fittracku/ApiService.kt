package com.example.fittracku

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

class ApiService {

    private val client = OkHttpClient()
    private val baseUrl = "http://10.0.2.2:3000/api"

    fun getRutinas(): List<Map<String, String>> {
        val request = Request.Builder()
            .url("$baseUrl/rutinas")
            .get()
            .build()

        return try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val body = response.body?.string() ?: "[]"
                val jsonArray = JSONArray(body)
                val lista = mutableListOf<Map<String, String>>()
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    lista.add(mapOf(
                        "id" to obj.getString("id"),
                        "nombre" to obj.getString("nombre"),
                        "ejercicios" to obj.getString("ejercicios"),
                        "dia" to obj.getString("dia")
                    ))
                }
                lista
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun createRutina(nombre: String, ejercicios: String, dia: String): Boolean {
        val json = JSONObject()
        json.put("nombre", nombre)
        json.put("ejercicios", ejercicios)
        json.put("dia", dia)

        val body = json.toString()
            .toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url("$baseUrl/rutinas")
            .post(body)
            .build()

        return try {
            val response = client.newCall(request).execute()
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}