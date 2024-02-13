package com.example.rickandmortyexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.CharacterModel
import com.example.rickandmortyexample.R

class RecyclerAdapter (
    private val callback: Callback
) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    private var characters: List<CharacterModel>? = null

    fun setCharactersData(
        characters: List<CharacterModel>?
    ) {
        this.characters = characters
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecyclerAdapter.MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recycler_view, parent, false)
        return MyViewHolder(itemView, callback)
    }

    override fun getItemCount(): Int{
        if (characters == null) return 0
        return characters?.size!!
    }

    interface Callback {
        fun onItemClicked(item: CharacterModel)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(characters?.get(position)!!)
    }

    class MyViewHolder(listItemView: View, private val callback: Callback) :
        RecyclerView.ViewHolder(listItemView) {

        val characterImage: ImageView = listItemView.findViewById(R.id.image_info)
        val characterCard: CardView = listItemView.findViewById(R.id.card_view)
        val characterName: TextView = listItemView.findViewById(R.id.tv_name)

        fun bind(characterModel: CharacterModel) {

            characterName.text = characterModel.name

            characterCard.setOnClickListener {
                callback.onItemClicked(characterModel)
            }

        }
    }
}
