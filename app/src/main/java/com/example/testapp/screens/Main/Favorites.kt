package com.example.testapp.screens.Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.adapters.CarAdapter
import com.example.testapp.adapters.FavoriteAdapter
import com.example.testapp.databinding.FragmentFavouritesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Favorites : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private val favoritesViewModel: FavoriteViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = _binding
        if (binding != null) {
            binding.rvCar.layoutManager = LinearLayoutManager(requireContext())
            val mainActivity = activity as MainActivity

            // Собираем Flow и обновляем адаптер, когда данные изменяются
            lifecycleScope.launch {
                favoritesViewModel.carsList.collectLatest { cars ->
                    if (!cars.isNullOrEmpty()) {
                        binding.rvCar.adapter = FavoriteAdapter(cars, mainActivity)
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = Favorites()
    }
}