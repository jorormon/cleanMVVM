package com.jordiortuno.cleanmvvm.domain.responses

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("id")
    val id: String,
    @field:SerializedName("fiscal_name")
    val fiscalName: String,
    @field:SerializedName("email")
    val email: String,
)
