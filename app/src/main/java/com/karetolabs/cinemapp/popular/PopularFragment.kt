package com.karetolabs.cinemapp.popular

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.karetolabs.cinemapp.R
import com.karetolabs.cinemapp.databinding.FragmentPopularBinding
import com.karetolabs.cinemapp.discover.DiscoverAdapter

class PopularFragment : Fragment() {

    private lateinit var popularBinding: FragmentPopularBinding
    private var popularViewModel: PopularViewModel? = null
    //private var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_popular_fragment, menu)
        val searchMenuItem: MenuItem? = menu.findItem(R.id.action_search)
        val manager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView? = searchMenuItem?.actionView as? SearchView
        searchView?.setSearchableInfo(manager.getSearchableInfo(activity?.componentName))
        searchView?.queryHint = "Escribe el titulo"
        searchView?.isIconified = true
        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                println("$query")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                println("$newText")
                return false
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        popularBinding = FragmentPopularBinding.inflate(layoutInflater, container, false)
        return popularBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularViewModel = ViewModelProvider(
            this,
            PopularViewModelFactory(5)
        )[PopularViewModel::class.java]

        popularViewModel?.contadorLiveData?.observe(requireActivity()) {
            popularBinding.tvContador.text = it.toString()
        }
        popularBinding.swrPopularMovies.setOnRefreshListener {
            //showLoading()
            popularViewModel?.fetchPopularMovies()
        }
        popularViewModel?.fetchPopularMovies()
        //showloading()
        popularViewModel?.popularMoviesLiveData?.observe(requireActivity()) { movies ->
            //hideLoading
            popularBinding.swrPopularMovies.isRefreshing = false
            movies?.let { movieList ->
                val popularAdapter = DiscoverAdapter(movieList)
                popularBinding.rvPopular.apply {
                    layoutManager = LinearLayoutManager(requireActivity())
                    adapter = popularAdapter
                }
            }
        }

        //popularBinding.tvContador.text = ((popularViewModel?.inicializador?: 0) + (popularViewModel?.contador?:0)).toString()//.toString()///contador.toString()
        popularBinding.tvContador.setOnClickListener {
            // contador++
            popularViewModel?.actualizarContador()
            //popularBinding.tvContador.text = ((popularViewModel?.inicializador?: 0) + (popularViewModel?.contador?:0)).toString()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = PopularFragment()
    }
}