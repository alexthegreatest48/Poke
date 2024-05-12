package ru.myworld.pokemon.repository

import kotlinx.coroutines.flow.Flow
import ru.myworld.pokemon.dto.Pokemon

interface PokeRepository {
    val data: Flow<List<Pokemon>>
    suspend fun getAllPoke()

}
