package ru.myworld.pokemon.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.myworld.pokemon.db.AppDb
import ru.myworld.pokemon.dto.Pokemon
import ru.myworld.pokemon.repository.DetailRepository
import ru.myworld.pokemon.repository.DetailRepositoryImpl
import ru.myworld.pokemon.repository.PokeRepository
import ru.myworld.pokemon.repository.PokeRepositoryImpl

class PokemonViewModel(application: Application) : AndroidViewModel(application) {
    private val pokeRepository : PokeRepository = PokeRepositoryImpl(AppDb.getInstance(context = application).pokeDao())
    private val detailRepository: DetailRepository = DetailRepositoryImpl(AppDb.getInstance(context = application).detailDao())

    val data : LiveData<List<Pokemon>> = pokeRepository.data.asLiveData(Dispatchers.Default)

    fun loadPokemon() = viewModelScope.launch {
        pokeRepository.getAllPoke()
    }

    fun loadDetail(id: Int) = viewModelScope.launch {
        detailRepository.getDetail(id)
    }
}