package ru.myworld.pokemon.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.myworld.pokemon.adapter.PokeAdapter
import ru.myworld.pokemon.databinding.FragmentPokemonBinding
import ru.myworld.pokemon.viewmodel.PokemonViewModel

class PokeFragment : Fragment() {
    private val viewModel: PokemonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPokemonBinding.inflate(inflater, container, false)

        val adapter = PokeAdapter()
        binding.list.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

}