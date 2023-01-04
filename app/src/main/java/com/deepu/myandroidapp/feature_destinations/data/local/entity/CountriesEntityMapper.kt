package com.deepu.myandroidapp.feature_destinations.data.local.entity

import com.deepu.myandroidapp.common.DomainMapper
import com.deepu.myandroidapp.feature_destinations.domain.model.Countries


class CountriesEntityMapper: DomainMapper<CountriesEntity, Countries> {
    override fun mapToDomain(entity: CountriesEntity): Countries {
        return Countries(
            entity.countryId,
            entity.countryName,
            entity.countryCapital,
            entity.continent,
            entity.countryImage,
            entity.description,
            entity.topSights
        )
    }

    override fun mapFromDomain(domainModel: Countries): CountriesEntity {

        return CountriesEntity(
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