package ru.myworld.pokemon.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.myworld.pokemon.dto.Detail

@Entity
data class DetailEntity (
    @PrimaryKey
    val id: Int,
    val name: String,
    val base_experience : Int,
    val height: Int,
    val weight: Int
){
    fun toDtoDetail() = Detail(id, name, base_experience, height, weight)

    companion object{
        fun fromDtoDetail(dto: Detail) = DetailEntity(dto.id, dto.name, dto.base_experience, dto.height, dto.weight)
    }
}

fun List<DetailEntity>.toDtoDetail(): List<Detail> = map(DetailEntity::toDtoDetail)
fun toEntityDetail(detail: Detail): DetailEntity = DetailEntity.fromDtoDetail(detail)
