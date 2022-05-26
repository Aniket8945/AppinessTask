package com.task.appiness.ui.activity.main_activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.task.appiness.data.network.Resource
import com.task.appiness.data.network.response.Hierarchy
import com.task.appiness.databinding.ActivityMainBinding
import com.task.appiness.ui.activity.BaseActivity
import com.task.appiness.ui.adapters.MyHierarchyAdapter
import com.task.appiness.ui.interfaces.ItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : BaseActivity(), ItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mContext: Context
    private val viewModel: MainViewModel by viewModels()
    private var hierarchyList: ArrayList<Hierarchy> = ArrayList()
    private var searchedHierarchy: ArrayList<Hierarchy> = ArrayList()

    override fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mContext = this
    }

    override fun initView() {
        viewModel.getHierarchy()
        observeViewModel()
        searchHierarchyList()
    }

    override fun initListener() {
    }

    override fun observeViewModel() {
        viewModel.hierarchyResponse.observe(this, {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.ValidationError -> {

                }
                is Resource.Success -> {
                    lifecycleScope.launch {
                    }
                    hierarchyList = it.value.dataObject[0].hierarchyList[0].hierarchy
                    setRecyclerView(hierarchyList)
                }
                is Resource.Failure -> {
                }

            }
        })
    }

    private fun setRecyclerView(hierarchy: ArrayList<Hierarchy>) {
        binding.rvMyHierarchy.adapter = MyHierarchyAdapter(mContext, this, hierarchy)
    }

    override fun onClick(v: View?) {
    }


    override fun onItemClick(view: View?, hierarchy: Hierarchy, type: String) {
        when (type) {
            "CALL" -> {
                openDialer(hierarchy.phoneNumber.toString())
            }
            "SMS" -> {
                openSMS(hierarchy.firstName.toString())
            }
        }
    }

    override fun getPosition(position: Int) {

    }

    private fun searchHierarchyList() {
        binding.etSearch.doOnTextChanged { text, start, before, count ->
            if (text!!.isNotEmpty()) {
                searchedHierarchy = ArrayList()
                for (hierarchy in hierarchyList) {
                    if (hierarchy.firstName!!.contains(
                            text,
                            ignoreCase = true
                        ) || hierarchy.lastName!!.contains(text, ignoreCase = true)
                    ) {
                        searchedHierarchy.add(hierarchy)
                        setRecyclerView(searchedHierarchy)
                    }
                }
            } else {
                setRecyclerView(hierarchyList)
            }
        }
    }

    private fun openDialer(mobileNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$mobileNumber")
        startActivity(intent)
    }

    private fun openSMS(firstName: String) {
        val sendIntent = Intent(Intent.ACTION_VIEW)
        sendIntent.data = Uri.parse("sms:")
        sendIntent.putExtra("sms_body", "Hi I'm $firstName")
        startActivity(sendIntent)

    }
}