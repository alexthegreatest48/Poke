package ru.myworld.pokemon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.myworld.pokemon.databinding.CardDetailBinding
import ru.myworld.pokemon.dto.Detail

class DetailAdapter() : ListAdapter<Detail, DetailViewHolder>(DetailDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding = CardDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val detail = getItem(position)
        holder.bind(detail)
    }
}

class DetailViewHolder(
    private val binding: CardDetailBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(detail: Detail) {
        binding.apply {
            idDDesc.text = detail.id.toString()
            nameDesc.text = detail.name
            expDesc.text = detail.base_experience.toString()
            heightDesc.text = detail.height.toString()
            weightDesc.text = detail.weight.toString()
        }
    }
}

class DetailDiffCallback : DiffUtil.ItemCallback<Detail>() {
    override fun areItemsTheSame(oldItem: Detail, newItem: Detail): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Detail, newItem: Detail): Boolean {
        return oldItem == newItem
    }
}