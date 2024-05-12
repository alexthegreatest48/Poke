package ru.myworld.pokemon.dto

data class ResultPoke(
    val count: Int,
    val next: String?,
    val previous: String?,
    val result: List<Pokemon>
)