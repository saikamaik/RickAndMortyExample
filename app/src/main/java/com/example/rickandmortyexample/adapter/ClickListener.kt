package com.example.rickandmortyexample.adapter

import com.example.domain.entity.CharacterModel

class ClickListener(
    val clickListener: (characterId: Int) -> Unit,
)
{
    fun onCardClick(character: CharacterModel) = clickListener(character.id)
}