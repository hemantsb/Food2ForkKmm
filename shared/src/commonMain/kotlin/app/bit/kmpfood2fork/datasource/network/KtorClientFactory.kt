package app.bit.kmpfood2fork.datasource.network

import io.ktor.client.*

expect class KtorClientFactory {
    fun build(): HttpClient
}