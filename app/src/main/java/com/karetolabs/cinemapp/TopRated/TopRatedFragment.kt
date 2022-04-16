package com.karetolabs.cinemapp.TopRated

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.karetolabs.cinemapp.databinding.FragmentTopRatedBinding
import com.karetolabs.cinemapp.discover.DiscoverAdapter
import com.karetolabs.cinemapp.discover.Movie


class TopRatedFragment : Fragment(), TopRatedContract.TopRatedView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var topRatedBinding: FragmentTopRatedBinding
    private var discoverAdapter: DiscoverAdapter? = null
    private var topRatedPresenter: TopRatedPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        topRatedBinding = FragmentTopRatedBinding.inflate(layoutInflater, container, false)
        return topRatedBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topRatedPresenter = TopRatedPresenter(this)
        initView()
    }

    private fun initView() {
        topRatedBinding.srlTopRated.setOnRefreshListener(this)
    }

    override fun showLoading() {
        topRatedBinding.progressTop.visibility = View.VISIBLE
        topRatedBinding.rvTopRated.visibility = View.GONE
    }

    override fun hideLoading() {
        topRatedBinding.srlTopRated.isRefreshing = false
        topRatedBinding.rvTopRated.visibility = View.VISIBLE
        topRatedBinding.progressTop.visibility = View.GONE
    }

    override fun showTopRated(topRatedMovies: List<Movie>) {
        discoverAdapter = DiscoverAdapter(topRatedMovies)
        topRatedBinding.rvTopRated.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = discoverAdapter
        }
    }

    override fun showErrorRequest(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onRefresh() {
        topRatedPresenter?.fetchRequestTopRatedMovie()
    }

    override fun onDestroy() {
        super.onDestroy()
        topRatedBinding.srlTopRated.setOnRefreshListener(null)
    }
}