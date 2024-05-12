package ru.myworld.pokemon.repository


import ru.myworld.pokemon.api.Api
import ru.myworld.pokemon.dao.DetailDao
import ru.myworld.pokemon.entity.toEntityDetail
import ru.myworld.pokemon.error.ApiError
import ru.myworld.pokemon.error.NetworkError
import ru.myworld.pokemon.error.UnknownError
import java.io.IOException

class DetailRepositoryImpl(private val dao: DetailDao) : DetailRepository{

    override suspend fun getDetail(id: Int) {
        try {
            val response = Api.service.getDetail(id)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }

            val body = response.body() ?: throw ApiError(response.code(), response.message())
            dao.insertDetail(toEntityDetail(body))
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }

}