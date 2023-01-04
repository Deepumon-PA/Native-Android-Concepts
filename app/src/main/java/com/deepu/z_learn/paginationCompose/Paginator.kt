package com.deepu.z_learn.paginationCompose

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}