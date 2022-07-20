package com.project.memelist.api

import com.project.memelist.model.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MemeRepository {
    suspend fun getMemesByPageNum(num: Int): Flow<UIState>
}

class MemeRepositoryImpl(private val service: MemeService): MemeRepository {
    override suspend fun getMemesByPageNum(num: Int): Flow<UIState> =
        flow {
            try {
                val response = service.getMemesByPageNum(num)
                if (response.isSuccessful) {
                    emit(response.body()?.let {
                        UIState.Success(it)
                    }?: throw Exception("Empty Response"))
                } else  throw Exception("Failed network call")
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

}