package com.tripaza.tripaza.ui.navigation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.tripaza.tripaza.databinding.FragmentHomeBinding
import com.tripaza.tripaza.ui.navigation.ui.home.recycler.PlaceListAdapter
import com.tripaza.tripaza.databases.dataobject.Place

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var placeListAdapter: PlaceListAdapter
    
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        placeListAdapter = PlaceListAdapter()
        homeViewModel.foodList.observe(viewLifecycleOwner){
            placeListAdapter.setFoodList(it)
        }
        homeViewModel.placeList.observe(viewLifecycleOwner){
            placeListAdapter.setPlaceList(it)
            showStoryRecyclerList()
        }
        homeViewModel.featuredPlace.observe(viewLifecycleOwner){
            placeListAdapter.setFeaturedFood(it)
        }
        
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    private fun showStoryRecyclerList(){
        val gridLayoutManager = GridLayoutManager(requireContext(),2)
        gridLayoutManager.setSpanSizeLookup(object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (placeListAdapter.getItemViewType(position)) {
                    PlaceListAdapter.HEADER -> 2
                    else -> 1
                }
            }
        })
        binding.frHomeRvHomeList.layoutManager = gridLayoutManager
        placeListAdapter.DEVELOPMENT_ONLY_CONTEXT(requireContext())
        placeListAdapter.setOnItemClickCallback(object : PlaceListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Place) {
                if(data.id != "OFFSET"){
                    Toast.makeText(requireContext(), "Item ${data.name} Clicked", Toast.LENGTH_SHORT).show()
                }
            }
        })
        binding.frHomeRvHomeList.adapter = placeListAdapter
    }
}