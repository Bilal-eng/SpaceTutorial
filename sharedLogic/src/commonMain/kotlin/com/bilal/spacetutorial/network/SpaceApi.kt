package com.bilal.spacetutorial.network

import com.bilal.spacetutorial.entity.LaunchListResponse
import com.bilal.spacetutorial.entity.RocketLaunch
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class SpaceApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun getAllLaunches(): List<RocketLaunch> {
        return (httpClient.get("https://lldev.thespacedevs.com/2.3.0/launches/previous/?mode=list&format=json")
            .body() as LaunchListResponse).results
    }
}