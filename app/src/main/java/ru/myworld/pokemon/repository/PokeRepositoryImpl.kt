package ru.myworld.pokemon.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.myworld.pokemon.api.Api
import ru.myworld.pokemon.dao.PokeDao
import ru.myworld.pokemon.entity.PokemonEntity
import ru.myworld.pokemon.entity.toDtoPoke
import ru.myworld.pokemon.entity.toEntityPoke
import ru.myworld.pokemon.error.ApiError
import ru.myworld.pokemon.error.NetworkError
import ru.myworld.pokemon.error.UnknownError
import java.io.IOException

class PokeRepositoryImpl(private val dao: PokeDao) : PokeRepository {
    override val data = dao.getAllPoke()
        .map(List<PokemonEntity>::toDtoPoke)
        .flowOn(Dispatchers.Default)

    override suspend fun getAllPoke() {
        try {
            val response = Api.service.getAllPoke()
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }

            val body = response.body() ?: throw ApiError(response.code(), response.message())
            val pokeBody = body.result
            dao.insertPoke(pokeBody.toEntityPoke())
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }
}