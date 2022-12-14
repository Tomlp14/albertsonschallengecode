package com.tommyappdevs.albertsonsapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tommyappdevs.albertsonsapp.databinding.DisplayFragmentBinding
import com.tommyappdevs.albertsonsapp.model.AcronymsResponseItem
import com.tommyappdevs.albertsonsapp.model.DisplayData
import com.tommyappdevs.albertsonsapp.model.VarsDefinition
import com.tommyappdevs.albertsonsapp.model.repository.UIState
import com.tommyappdevs.albertsonsapp.view.adapter.AcronymAdapter
import com.tommyappdevs.albertsonsapp.viewmodel.AcronymViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "DisplayFragment"

@AndroidEntryPoint
class DisplayFragment: Fragment() {

    private lateinit var binding: DisplayFragmentBinding
    private  val viewModel: AcronymViewModel by viewModels()
    private val adapter: AcronymAdapter by lazy { AcronymAdapter(emptyList()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DisplayFragmentBinding.inflate(inflater, container, false)
        initViews()
        initObservables()
        viewModel.fetchAcronymList("HMM")

        return binding.root
    }

    private fun initObservables() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect{
                when (it){
                    is UIState.Success ->{
                        Log.d(TAG, "observeViewModel: Success ${it.data}")
                        updateAdapter(it.data)
                    }
                    is UIState.Failure -> {
                        Log.d(TAG, "observeViewModel: Failure ${it.errorMessage}")
                        showError(it.errorMessage)
                    }
                    is UIState.Loading->{
                        Log.d(TAG, "observeViewModel: Loading ${it.loading}")
                        showLoading(it.loading)
                    }
                    else -> Log.i(TAG, "List of Acronyms: ${it}")
                }
            }
        }
    }

    private fun showLoading(loading: Boolean) {
        requireActivity().showLoading(loading)
    }

    private fun showError(errorMessage: String) {
        requireActivity().showError(errorMessage)
    }

    private fun updateAdapter(dataSet: List<AcronymsResponseItem>) {
        val list =dataSet.map {
            DisplayData(
                it.sf,
                it.lfs.map {
                    VarsDefinition(
                        it.lf,
                        it.since
                    )
                }
            )
        }
        Log.d(TAG, "updateAdapter: $list")
        adapter.updateDataSet(
            list
        )
    }

    private fun initViews() {
        binding.acronymList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@DisplayFragment.adapter
        }
    }
}