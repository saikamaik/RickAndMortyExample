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
import com.example.rickandmortyexample.R
import com.example.rickandmortyexample.databinding.FragmentItemInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemInfoFragment : Fragment() {

    private lateinit var binding: FragmentItemInfoBinding
    private val viewModel: ItemInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (page in 1..3) {
            viewModel.getAllEpisodes(page)
        }
        val characterId: Int = args.characterId
        initViewModel(characterId)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

    private val args: ItemInfoFragmentArgs by navArgs()

    private fun sliceEpisodeString(episodeListOfStrings: List<String>): List<String> {

        val slicedIdEpisodes: MutableList<String> = mutableListOf()
        for (episode in episodeListOfStrings) {
            val episodeNew = episode.substringAfterLast("/")
            slicedIdEpisodes += episodeNew
        }
        return slicedIdEpisodes
    }

    @SuppressLint("SetTextI18n")
    fun setUpCard(characterModel: CharacterModel) {

        val imgURL: String = characterModel.image
        binding.imageInfo.load(imgURL) {
            size(800, 800)
        }
        binding.tvName.text = getString(R.string.item_text_name) + characterModel.name
        binding.tvStatus.text = getString(R.string.item_text_status) + characterModel.status
        binding.tvSpecies.text = getString(R.string.item_text_species) + characterModel.species

        if (characterModel.type.isEmpty()) {
            binding.tvType.text = getString(R.string.item_text_type_unknown)
        } else {
            binding.tvType.text = getString(R.string.item_text_type) + characterModel.type
        }

        binding.tvGender.text = getString(R.string.item_text_gender) + characterModel.gender
        binding.tvOrigin.text = getString(R.string.item_text_origin) + characterModel.origin.name
        binding.tvLocation.text =
            getString(R.string.item_text_location) + characterModel.location.name

        initViewModelEpisode(characterModel.episode)
    }

    @SuppressLint("SetTextI18n")
    fun setUpEpisodeList(episodeList: List<String>) {
        episodeList.toString().substringAfter("[").substringBefore("]")
        binding.tvEpisode.text = getString(R.string.item_text_episode) + episodeList
    }

    private fun initViewModel(id: Int) {
        viewModel.getOneCharacterFromDB(id).observe(viewLifecycleOwner) {
            if (it != null) {
                val characterModel = it
                setUpCard(characterModel)
            }
        }
    }

    private fun initViewModelEpisode(episodeList: List<String>) {

        var episodeList2: List<String> = mutableListOf()
        val episodeIdList: List<String> = sliceEpisodeString(episodeList)
        for (episodeId in episodeIdList) {
            viewModel.getOneEpisodeFromDB(episodeId.toInt()).observe(viewLifecycleOwner) {
                if (it != null) {
                    episodeList2 += it.name
                    setUpEpisodeList(episodeList2)
                }
            }
        }
    }
}