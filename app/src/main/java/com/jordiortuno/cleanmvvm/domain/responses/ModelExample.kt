package com.jordiortuno.cleanmvvm.domain.responses

import com.google.gson.annotations.SerializedName

data class ModelExample(
    @field:SerializedName("name_example")
    val nameExample: String? = null
)