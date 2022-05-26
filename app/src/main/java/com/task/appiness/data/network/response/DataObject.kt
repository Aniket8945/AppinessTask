package com.task.appiness.data.network.response

import com.google.gson.annotations.SerializedName


data class DataObject (

  @SerializedName("hierarchyList" ) var hierarchyList : ArrayList<HierarchyList> = arrayListOf()

)