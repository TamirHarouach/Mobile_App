package com.example.cirestechnologiesmobilechallenge.presentation.news.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cirestechnologiesmobilechallenge.core.util.Constants
import com.example.cirestechnologiesmobilechallenge.core.util.collectLifecycleFlow
import com.example.cirestechnologiesmobilechallenge.databinding.FragmentDiscoverBinding
import com.example.cirestechnologiesmobilechallenge.domain.model.Data
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverFragment : Fragment() {

    private lateinit var binding: FragmentDiscoverBinding
    private val viewModel: DiscoverViewModel by activityViewModels()
    private val dataList = ArrayList<Data>()

    companion object {
        fun newInstance(): DiscoverFragment {
            val f = DiscoverFragment()
            return f
        }
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDiscoverBinding.inflate(inflater, container, false)

        val discoverAdapter = DiscoverAdapter()
        binding.newsRecyclerView.adapter = discoverAdapter
        initTabLayout()
        tabLayoutClickListener()
        requireActivity().collectLifecycleFlow(viewModel.newsFlow){ news ->
            dataList.clear()
            dataList.addAll(news)
            discoverAdapter.submitList(dataList.toList())
        }

        requireActivity().collectLifecycleFlow(viewModel.eventFlow){ event ->
            when(event) {
                is DiscoverViewModel.UIEvent.ShowToast -> { Toast.makeText(requireContext(), event.message, Toast.LENGTH_LONG).show()}
                is DiscoverViewModel.UIEvent.ShowLoading -> { if (event.show) showLoadingView() else hideLoadingView()}
           }
        }

        viewModel.getNewsByCategory(Constants.Categories.values()[0].value)

        return binding.root
    }

    private fun initTabLayout() {
        val tabLayout = binding.discoverTabLayout
        for (category in Constants.Categories.values()) {
            tabLayout.addTab(tabLayout.newTab().setText(category.value))
        }
    }

    private fun tabLayoutClickListener() {
        binding.discoverTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.getNewsByCategory(Constants.Categories.values()[tab!!.position].value)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    fun showLoadingView() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideLoadingView() {
        binding.progressBar.visibility = View.GONE
    }

}