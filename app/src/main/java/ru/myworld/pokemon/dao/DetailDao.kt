package ru.myworld.pokemon.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.myworld.pokemon.entity.DetailEntity

@Dao
interface DetailDao {
    @Query("SELECT * FROM DetailEntity WHERE ID = :id")
    fun getDetailById(id : Int): Flow<DetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(detail: DetailEntity)
}