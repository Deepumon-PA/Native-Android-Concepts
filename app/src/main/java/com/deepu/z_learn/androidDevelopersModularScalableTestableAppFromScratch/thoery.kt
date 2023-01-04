
package com.deepu.z_learn.androidDevelopersModularScalableTestableAppFromScratch
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.utils.io.*
import kotlinx.coroutines.flow.*

/*

//------------------------------------------------------Theory----------------------------------------------------------//

*/
/*  UI <-----------------Data(yes it is)------------------ Data (Single source of truth)
  UI -----------------Events------------------> Data
  Unidirectional data flow to provide consistency

  Example scenario: Data layer store user bookmarks and expose them to the UI layer using kotlin flows
                    User Events are passed back to the Data layer in an event for eg, Add or remove bookmarks

  Data Layer:
             - Repositories : Expose app data and centralize changes to that data
             - Data source : Obtain data from a single source
                            - LocalDataSource: Store bookmarks in DataStore*//*




//News repository: get live news data from network
// News Repository -> NetWorkDataSource
// BookmarkRepository -> NetWorkDataSource & LocalDataSource

*/
/*
 UI Layer

     - State Holder: Handles UI logic and constructs UI state (by listening for changes in the data layer)

        -can use a ViewModel(ForYourViewModel) as state holder
        - ForYourViewModel -> NewsRepository & BookmarkRepository
        - ForYourViewModel crates a UIState

        - List of news resources coming from news repository and List of id's coming from bookmarks repository
        - combines them and creates a SaveableNewsResources, where each item contains a news resource itself and  whether its bookmarked or not

     - Screen: Displays UI state
 *//*




//-----------------------------------------------------Code----------------------------------------------------------------------//




class LocalDataSource(private val dataStore: DataStore<UserPreferences>) {
    val bookMarkStream: Flow<List<String>> = dataStore.data.map {
        it.bookmarksMap.keys.toList()
    }

    suspend fun toggleBookMark(isBookmarked: Boolean, newBookmarkResourceId: String) {
        if (isBookmarked) {
            newBookmarkResourceId.put()
        } else {
            newBookmarkResourceId.remove()
        }
    }
}


class NetworkDataSource(private val networkLayer: NetworkData) {
}

class BookMarkRepository(private val localDataSource: LocalDataSource) { // pulling data from multiple data sources,for eg: local and network data sources , get data from network data source and synchronize it with local data source
    val bookMarkStream: Flow<List<String>> = localDataSource.bookMarkStream

    suspend fun toggleBookMark(nreResourceId: String, isBookmarked: Boolean) =
        localDataSource.toggleBookMark(newBookmarkResourceId = "", isBookmarked = true)

}

class NewsRepository(
    private val localDataSource: LocalDataSource,
    private val networkDAtaSource: NetworkDataSource
) {
    val newsResourceStream: Flow<String> = flow {

    }
}

class ForYouViewModel(
    val newsRepository: NewsRepository,
    private val bookMarkRepository: BookMarkRepository
) : ViewModel() {

    private val combinedData: Flow<List<SaveableNewsResource>> = combine(
        newsRepository.newsResourceStream,
        bookMarkRepository.bookMarkStream
    ) { newsResorces, bookmarkd ->
        newsResorces.map { newsResorces ->
            val isBookMarked = bookmarkd.contains(newsRe0sorces.id)
            SaveableNewsResource(newsResorces, isBookMarked)
        }

    }

    val uiState: StateFlow<ForYouState> = combinedData.map { saveableNewsResources ->
        ForYouState.Success(saveableNewsResources)
    }.stateIn(
        scope = viewModelScope,
        initialValue = ForYouState.Loading,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)// makes the flow active only if there is one collector
    )

}


sealed interface ForYouState {
    object Loading : ForYouState
    data class Success(val resources: List<SaveableNewsResource>) : ForYouState
}
*/
