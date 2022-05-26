package com.task.appiness.data.network.response

import com.google.gson.annotations.SerializedName


data class HierarchyList (

  @SerializedName("hierarchy" ) var hierarchy : ArrayList<Hierarchy> = arrayListOf()

)