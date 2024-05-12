package ru.myworld.pokemon.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.myworld.pokemon.dto.Pokemon

@Entity
data class PokemonEntity(
    @PrimaryKey
    val name: String,
    val url: String
) {
    fun toDtoPoke() =  Pokemon(name, url)

    companion object{
        fun fromDtoPoke(dto: Pokemon)= PokemonEntity(dto.name, dto.url)
        }
    }

fun List<PokemonEntity>.toDtoPoke(): List<Pokemon> = map(PokemonEntity::toDtoPoke)
fun List<Pokemon>.toEntityPoke(): List<PokemonEntity> = map(PokemonEntity::fromDtoPoke)