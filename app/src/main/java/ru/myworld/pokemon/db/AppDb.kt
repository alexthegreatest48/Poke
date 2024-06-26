package ru.myworld.pokemon.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.myworld.pokemon.dao.DetailDao
import ru.myworld.pokemon.dao.PokeDao
import ru.myworld.pokemon.entity.DetailEntity
import ru.myworld.pokemon.entity.PokemonEntity

@Database(entities = [PokemonEntity::class,DetailEntity::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun pokeDao(): PokeDao
    abstract fun detailDao(): DetailDao

    companion object {
        @Volatile
        private var instance: AppDb? = null

        fun getInstance(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDb::class.java, "app.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}