package com.example.cirestechnologiesmobilechallenge.data.remote.service


import com.example.cirestechnologiesmobilechallenge.core.util.Resource
import com.example.cirestechnologiesmobilechallenge.data.remote.HttpRoutes
import com.example.cirestechnologiesmobilechallenge.data.remote.dto.NewsDto
import com.example.cirestechnologiesmobilechallenge.data.remote.repository.NewsServices
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsServiceImpl(
    private val client: HttpClient
    ): NewsServices {

    override fun getNewsByCategory(category: String): Flow<Resource<NewsDto>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(client.get {
                url(HttpRoutes.NEWS)
                parameter("category", category)
            }))
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            emit(Resource.Error(message = "Error: ${e.response.status.description}", data = null))
        } catch(e: ClientRequestException) {
            // 4xx - responses
            emit(Resource.Error(message = "Error: ${e.response.status.description}", data = null))
        } catch(e: ServerResponseException) {
            // 5xx - responses
            emit(Resource.Error(message = "Error: ${e.response.status.description}", data = null))
        } catch(e: Exception) {
            emit(Resource.Error(message = "Couldn't reach server, check your internet connection.", data = null))
        }
    }


}