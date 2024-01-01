package com.example.horoscapp.data

import android.util.Log
import com.example.horoscapp.data.network.HoroscopeAPIService
import com.example.horoscapp.domain.model.PredictionModel
import com.example.horoscapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeAPIService): Repository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching {apiService.getHoroscope(sign) }
            .onSuccess {  return it.toDomain() }
            .onFailure { Log.i("Error","Ha ocurrido un error ${it.message}") }
        return null
    }
}