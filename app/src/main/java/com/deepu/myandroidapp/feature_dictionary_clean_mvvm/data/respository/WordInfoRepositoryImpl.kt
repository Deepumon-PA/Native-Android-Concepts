package com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.respository

import android.util.Log
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.core.util.Resource
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.local.WordInfoDao
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.data.remote.DictionaryApi
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.model.WordInfo
import com.deepu.myandroidapp.feature_dictionary_clean_mvvm.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading()) // emit Loading state with no data

        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() } // get word info from data base
        emit(Resource.Loading(wordInfos))

        try {

            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfo(remoteWordInfos.map { it.word })
            dao.insertWordInfo(remoteWordInfos.map { it.toWordInfoEntity() })

        } catch (e: HttpException) {
            Log.d("thisistheexc", "exception: ${e}")

            emit(
                Resource.Error(
                    data = wordInfos,
                    message = "Something went wrong"
                )
            )
        } catch (ioe: IOException) {
            emit(
                Resource.Error(
                    data = wordInfos,
                    message = "Couldn't reach server please check your internet connection"
                )
            )
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))

    }
}