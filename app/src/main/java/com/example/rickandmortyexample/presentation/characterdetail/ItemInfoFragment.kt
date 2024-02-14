package com.example.rickandmortyexample.presentation.characterdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.rickandmortyexample.Const.CHARACTER_EPISODE
import com.example.rickandmortyexample.Const.CHARACTER_GENDER
import com.example.rickandmortyexample.Const.CHARACTER_IMAGEURL
import com.example.rickandmortyexample.Const.CHARACTER_LOCATION
import com.example.rickandmortyexample.Const.CHARACTER_NAME
import com.example.rickandmortyexample.Const.CHARACTER_ORIGIN
import com.example.rickandmortyexample.Const.CHARACTER_SPECIES
import com.example.rickandmortyexample.Const.CHARACTER_STATUS
import com.example.rickandmortyexample.Const.CHARACTER_TYPE
import com.example.rickandmortyexample.databinding.FragmentItemInfoBinding

class ItemInfoFragment : Fragment() {

    private var _binding: FragmentItemInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpCard()

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

    @SuppressLint("SetTextI18n")
    fun setUpCard() {
        val imgURL: String? = arguments?.getString(CHARACTER_IMAGEURL)
        binding.imageInfo.load(imgURL) {
            size(800, 800)
        }
        binding.tvName.text = "Name: " + arguments?.getString(CHARACTER_NAME)
        binding.tvStatus.text = "Status:" + arguments?.getString(CHARACTER_STATUS)
        binding.tvSpecies.text = "Species" + arguments?.getString(CHARACTER_SPECIES)
        if (!arguments?.getString(CHARACTER_TYPE).isNullOrEmpty()) {
            binding.tvType.text = "Type: " + arguments?.getString(CHARACTER_TYPE)
        } else binding.tvType.text = "Type: ???"
        binding.tvGender.text = "Gender: " + arguments?.getString(CHARACTER_GENDER)
        binding.tvOrigin.text = "Origin: " + arguments?.getString(CHARACTER_ORIGIN)
        binding.tvLocation.text = "Location: " + arguments?.getString(CHARACTER_LOCATION)
        binding.tvEpisode.text = "Episodes: " + arguments?.getString(CHARACTER_EPISODE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}