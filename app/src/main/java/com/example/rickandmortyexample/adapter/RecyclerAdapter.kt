package com.example.rickandmortyexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.domain.entity.CharacterModel
import com.example.rickandmortyexample.databinding.ItemRecyclerViewBinding

class RecyclerAdapter(
    private val callback: Callback
) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    private var characters: List<CharacterModel> = mutableListOf()

    fun setCharacterData(
        characters: List<CharacterModel>
    ) {
        this.characters += characters
    }

//    fun clearCharacterData() {
//        characters.clear()
//    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            ItemRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), callback
        )
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    interface Callback {
        fun onItemClicked(item: CharacterModel)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    class MyViewHolder(
        binding: ItemRecyclerViewBinding,
        private val callback: Callback
    ) :
        RecyclerView.ViewHolder(binding.root) {

        val characterImage: ImageView = binding.imageRecyclerView
        val characterCard: CardView = binding.cardView
        private val characterName: TextView = binding.tvNameRecyclerview
        private val characterStatus: TextView = binding.tvStatusRecyclerview
        private val characterSpecies: TextView = binding.tvSpeciesRecyclerview
        private val characterLocation: TextView = binding.tvLocation

        fun bind(characterModel: CharacterModel) {

            characterName.text = characterModel.name
            characterImage.load(characterModel.image)
            characterSpecies.text = characterModel.species
            characterStatus.text = characterModel.status
            characterLocation.text = characterModel.location.name

            characterCard.setOnClickListener {
                callback.onItemClicked(characterModel)
            }

        }
    }
}
