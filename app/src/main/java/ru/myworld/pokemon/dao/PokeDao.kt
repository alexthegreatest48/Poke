package ru.myworld.pokemon.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.myworld.pokemon.entity.PokemonEntity

@Dao
interface PokeDao {
    @Query("SELECT * FROM POKEMONENTITY ORDER BY name DESC")
    fun getAllPoke(): Flow<List<PokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoke(poke: List<PokemonEntity>)
}