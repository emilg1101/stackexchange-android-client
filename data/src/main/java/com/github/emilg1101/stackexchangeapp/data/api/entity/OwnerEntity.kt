package com.github.emilg1101.stackexchangeapp.data.api.entity

import com.google.gson.annotations.SerializedName

class OwnerEntity(
    @field:SerializedName("reputation")
    val reputation: Int,
    @field:SerializedName("user_id")
    val userId: Int,
    @field:SerializedName("user_type")
    val userType: String,
    @field:SerializedName("profile_image")
    val profileImage: String,
    @field:SerializedName("display_name")
    val displayName: String
)
