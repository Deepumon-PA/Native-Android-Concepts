package com.deepu.myandroidapp.feature_destinations.data.remote.dto

import com.deepu.myandroidapp.common.DomainMapper
import com.deepu.myandroidapp.feature_destinations.domain.model.Countries

class CountriesDTOMapper: DomainMapper<CountriesDTO, Countries> {
    override fun mapToDomain(entityOrDto: CountriesDTO): Countries {
        return Countries(
            entityOrDto.countryId,
            entityOrDto.countryName,
            entityOrDto.countryCapital,
            entityOrDto.continent,
            entityOrDto.countryImage,
            entityOrDto.description,
            entityOrDto.topSights
        )
    }

    override fun mapFromDomain(domainModel: Countries): CountriesDTO {
        return  CountriesDTO(
            domainModel.countryId,
            domainModel.countryName,
            domainModel.countryCapital,
            domainModel.continent,
            domainModel.countryImage,
            domainModel.description,
            domainModel.topSights
        )
    }


}