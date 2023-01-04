package com.deepu.myandroidapp.feature_destinations.data.remote

import com.deepu.myandroidapp.common.Constants
import com.deepu.myandroidapp.feature_destinations.data.remote.dto.CountriesDTO
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class CountriesKtorAPIImpl(private val ktorHttpClient: HttpClient) : CountriesKtorAPI {

    companion object{
        private const val TAG = "CountriesKtorAPIImpl"
    }

    override suspend fun getCountries(): List<CountriesDTO> {
        return try {
            ktorHttpClient.get { url(Constants.COUNTRIES_URL + "/tourismDestinations") }
        } catch (e: RedirectResponseException) {
//            3xx - Response
            emptyList()
        } catch (e: ClientRequestException) {
//            4xx - Response
            emptyList()
        } catch (e: ServerResponseException) {
//            5xx - Response
            emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }


    // posting values'
/*    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse> {
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }*/
}