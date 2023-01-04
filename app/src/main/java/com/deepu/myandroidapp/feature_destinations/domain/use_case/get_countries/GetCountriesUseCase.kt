package com.deepu.myandroidapp.feature_destinations.domain.use_case.get_countries

import android.util.Log
import com.deepu.myandroidapp.common.Resource
import com.deepu.myandroidapp.feature_destinations.data.remote.dto.toCountries
import com.deepu.myandroidapp.feature_destinations.domain.model.Countries
import com.deepu.myandroidapp.feature_destinations.domain.repository.CountriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
//    private val countriesDTOMapper: CountriesDTOMapper, //if you are using a separate mapper class, instead here we are using an extension function
    private val countriesRepository: CountriesRepository
){

    companion object{
        private const val TAG = "GetCountriesUseCaseTAG"
    }

    //operator overriding, predefined methods can be reused using this 'operator' keyword
    //use: this function will automatically get called when a new instance of the class is created
    operator fun invoke(): Flow<Resource<List<Countries>>> = flow{
        try {
            emit(Resource.Loading)
            Log.d(TAG, "loading")
//            val countries = countriesRepository.getDestinations().map { CountriesDTOMapper.mapToDomain(it)} // if you are using a separate mapper class
              val countries = countriesRepository.getDestinations().map { it.toCountries()}/*calling the extension function used for mapping in countriesDTO*/
            Log.d("printingtopsights", countries[0].topSights.toString())
            emit(Resource.Success(countries,"Successful"))
            Log.d(TAG, "success")

        }catch (e: HttpException){ // catching the http exception
              emit(Resource.Error(e.localizedMessage, "An unexpected error occurred"))
            Log.d(TAG, "unexpected error")
        }catch (e: IOException){
              emit(Resource.Error(e.localizedMessage, "Couldn't reach server, check internet connection"))
            Log.d(TAG, "IO Exception ${e.localizedMessage}")
        }

    }

}