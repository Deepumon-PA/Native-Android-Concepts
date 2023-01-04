package com.deepu.z_learn.paginationCompose

import kotlinx.coroutines.delay

class PaginationDataRepository {

    private val remoteDataSource = (1..100).map {
        PagingListItem(
            title = "Item $it",
            description = "Description $it"
        )
    }

    suspend fun getItems(page: Int, pageSize: Int): Result<List<PagingListItem>> {
        delay(2000L)
        val startingIndex = page * pageSize
        return if(startingIndex + pageSize <= remoteDataSource.size) {
            Result.success(
                remoteDataSource.slice(startingIndex until startingIndex + pageSize)
            )
        } else Result.success(emptyList())
    }
}