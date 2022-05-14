package com.karetolabs.cinemapp.upcomming

import android.graphics.Color
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
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
        val content = SpannableString("5545362718 Telefono de emergencia")
        //content.setSpan(UnderlineSpan(), 0, 10, 0)
        content.setSpan(ForegroundColorSpan(Color.BLUE), 0,10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        //fragmentUpComingBinding.textView10.setText(content)

        val contentHTML = "<b>Hola</b> <i>Mundo</i>  <font color='red'>simple</font>."
        val formatText = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(contentHTML, HtmlCompat.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(contentHTML)
        }
        fragmentUpComingBinding.textView10.text = formatText
    }

}