package com.karetolabs.cinemapp.upcomming

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.karetolabs.cinemapp.R
import com.karetolabs.cinemapp.databinding.FragmentUpComingBinding
import com.karetolabs.cinemapp.discover.DiscoverAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpComingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpComingFragment : Fragment() {
    lateinit var fragmentUpComingBinding : FragmentUpComingBinding
    var viewModel: UpComingViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentUpComingBinding = FragmentUpComingBinding.inflate(layoutInflater, container, false)

        return fragmentUpComingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpComingViewModel::class.java)
        viewModel?.listMoviesLiveData?.observe(requireActivity()){
            val upcomingAdapter = DiscoverAdapter(it)
            fragmentUpComingBinding.rvListUpComing.apply {
                adapter = upcomingAdapter
                layoutManager = LinearLayoutManager(requireActivity())
            }
            fragmentUpComingBinding.progressBar.visibility = View.GONE
            fragmentUpComingBinding.swipeRefresh.isRefreshing = false
        }

        fragmentUpComingBinding.progressBar.visibility = View.VISIBLE
        viewModel?.getMovies()

        fragmentUpComingBinding.swipeRefresh.setOnRefreshListener {
            viewModel?.getMovies()
        }
    }

}