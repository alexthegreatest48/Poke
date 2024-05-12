package ru.myworld.pokemon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.myworld.pokemon.databinding.CardPokemonBinding
import ru.myworld.pokemon.dto.Pokemon

class PokeAdapter() : ListAdapter<Pokemon, PokeViewHolder>(PokeDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        val binding = CardPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        val poke = getItem(position)
        holder.bind(poke)
    }
}

class PokeViewHolder(
    private val binding: CardPokemonBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(pokemon: Pokemon) {
        binding.apply {
            name.text = pokemon.name
        }
    }
}

class PokeDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }
}