package ru.myworld.pokemon.repository

import kotlinx.coroutines.flow.Flow
import ru.myworld.pokemon.dto.Detail

interface DetailRepository {
    suspend fun getDetail(id: Int)
}