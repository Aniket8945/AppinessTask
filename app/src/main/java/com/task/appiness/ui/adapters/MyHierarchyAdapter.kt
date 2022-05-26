package com.task.appiness.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.appiness.data.network.response.Hierarchy
import com.task.appiness.databinding.ItemHierarchyBinding
import com.task.appiness.ui.interfaces.ItemClickListener


class MyHierarchyAdapter(
    var context: Context,
    private val clickListAdapter: ItemClickListener,
    private var dataList: ArrayList<Hierarchy>
) :
    RecyclerView.Adapter<MyHierarchyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHierarchyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], clickListAdapter)

    }

    class ViewHolder(val binding: ItemHierarchyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hierarchy: Hierarchy, clickListAdapter: ItemClickListener) {
            binding.tvName.text = "${hierarchy.firstName} ${hierarchy.lastName}"
            binding.tvCategory.text = hierarchy.categoryName

            binding.ivCall.setOnClickListener {
                clickListAdapter.onItemClick(it, hierarchy, "CALL")
            }
            binding.ivSMS.setOnClickListener {
                clickListAdapter.onItemClick(it, hierarchy, "SMS")
            }
        }
    }

}