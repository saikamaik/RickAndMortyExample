package com.example.rickandmortyexample.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.domain.entity.CharacterModel
import com.example.rickandmortyexample.databinding.ItemRecyclerViewBinding

class RecyclerAdapter(
    private val clickListener: ClickListener
) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    private var characters: List<CharacterModel> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setCharacterData(
        characters: List<CharacterModel>
    ) {
        this.characters = characters
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(characters[position], clickListener)
    }

    class MyViewHolder private constructor(
        private val binding: ItemRecyclerViewBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRecyclerViewBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

        fun bind(characterModel: CharacterModel, clickListener: ClickListener) {

            binding.character = characterModel
            binding.tvNameRecyclerview.text = characterModel.name
            binding.imageRecyclerView.load(characterModel.image)
            binding.tvSpeciesRecyclerview.text = characterModel.species
            binding.tvStatusRecyclerview.text = characterModel.status
            binding.tvLocation.text = characterModel.location.name
            binding.clickListener = clickListener

        }
    }
}
