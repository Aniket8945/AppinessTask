package com.task.appiness.data.network.response

import com.google.gson.annotations.SerializedName


data class Hierarchy (

  @SerializedName("firstName"    ) var firstName    : String? = null,
  @SerializedName("lastName"     ) var lastName     : String? = null,
  @SerializedName("categoryName" ) var categoryName : String? = null,
  @SerializedName("phoneNumber"  ) var phoneNumber  : String? = null

)