package com.tommyappdevs.albertsonsapp.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tommyappdevs.albertsonsapp.databinding.AcronymItemLayoutBinding
import com.tommyappdevs.albertsonsapp.model.AcronymsResponseItem
import com.tommyappdevs.albertsonsapp.model.DisplayData

private const val TAG = "AcronymAdapter"

class AcronymAdapter(private var dataSet: List<DisplayData>): RecyclerView.Adapter<AcronymAdapter.AcronymViewHolder>() {

    class AcronymViewHolder(private val binding: AcronymItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root){

            fun onBind(acronyms: DisplayData){
                binding.apply {
                    lfDefinition.text = acronyms.lf
                    acronyms.vars.map{ it.toString() }.reduce { acc, s ->
                        Log.d(TAG, "onBind: $acc")
                        acc.plus(s)
                    }.also {
                        varDefinition.text = it
                    }
                }
            }
        }

    fun updateDataSet(newDataSet: List<DisplayData>){
        dataSet = newDataSet
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymViewHolder {
        return AcronymViewHolder(
            AcronymItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AcronymViewHolder, position: Int) {
        holder.onBind(dataSet[0])
    }

    override fun getItemCount(): Int {
        return if(dataSet.isEmpty()) 0 else dataSet[0].vars.size
    }
}