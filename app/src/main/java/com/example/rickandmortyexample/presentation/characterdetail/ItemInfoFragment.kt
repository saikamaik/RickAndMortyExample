package com.example.rickandmortyexample.presentation.characterdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.domain.entity.CharacterModel
import com.example.rickandmortyexample.databinding.FragmentItemInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemInfoFragment : Fragment() {

    private lateinit var binding: FragmentItemInfoBinding
    private val viewModel: ItemInfoViewModel by viewModels()
    private lateinit var characterModel: CharacterModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterId: Int = args.characterId
        initViewModel(characterId)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

    val args: ItemInfoFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    fun setUpCard(characterModel: CharacterModel) {

        val imgURL: String = characterModel.image
        binding.imageInfo.load(imgURL) {
            size(800, 800)
        }
        binding.tvName.text = "Name: " + characterModel.name
        binding.tvStatus.text = "Status: " + characterModel.status
        binding.tvSpecies.text = "Species: " + characterModel.species

        if (characterModel.type.isEmpty()) {
            binding.tvType.text = "Type: ???"
        } else {
            binding.tvType.text = "Type: " + characterModel.type
        }

        binding.tvGender.text = "Gender: " + characterModel.gender
        binding.tvOrigin.text = "Origin: " + characterModel.origin.name
        binding.tvLocation.text = "Location: " + characterModel.location.name
        binding.tvEpisode.text = "Episode: " + characterModel.episode

    }

    private fun initViewModel(id: Int) {

        viewModel.getOneCharacterFromDB(id).observe(viewLifecycleOwner) {
            if (it != null)
                characterModel = it
            setUpCard(characterModel)
        }
    }
}