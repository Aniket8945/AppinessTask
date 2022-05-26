package com.task.appiness.data.network.response

import com.google.gson.annotations.SerializedName


data class MyHierarchy (

  @SerializedName("statusCode" ) var statusCode : Int?                  = null,
  @SerializedName("status"     ) var status     : Boolean?              = null,
  @SerializedName("dataObject" ) var dataObject : ArrayList<DataObject> = arrayListOf()

)