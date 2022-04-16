package com.karetolabs.cinemapp.discover

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.karetolabs.cinemapp.data.network.NetworkConnection
import com.karetolabs.cinemapp.data.network.service.DiscoverService
import com.karetolabs.cinemapp.databinding.FragmentDiscoverBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DiscoverFragment : Fragment() {

    private lateinit var discoverBinding: FragmentDiscoverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        discoverBinding = FragmentDiscoverBinding.inflate(layoutInflater, container, false)
        return discoverBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callDiscoveryService()
    }


    private fun callDiscoveryService() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = NetworkConnection
                .provideApi(DiscoverService::class.java)?.discoverList(
                    "https://api.themoviedb.org/3/discover/movie?api_key=568ff4df19633325b978ea1b75fe2290&language=es-MX&sort_by=popularity.desc"
                )

            val movies = response?.results?.map { movieResponse ->
                Movie(
                    id = movieResponse.id,
                    title = movieResponse.title,
                    posterPath = movieResponse.posterPath,
                    summary = movieResponse.overview,
                    date = movieResponse.releaseDate
                )
            }
            withContext(Dispatchers.Main) {
                val discoverAdapter = DiscoverAdapter(movies ?: arrayListOf())
                discoverBinding.rvDiscover.apply {
                    layoutManager = LinearLayoutManager(requireActivity())
                    adapter = discoverAdapter
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DiscoverFragment()
    }
}