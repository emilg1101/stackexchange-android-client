package com.github.emilg1101.stackexchangeapp.profiledetails.model

import com.github.emilg1101.stackexchangeapp.domain.entity.User

data class ProfileDetailsModel(val name: String, val image: String)

val ProfileDetailsModelMapper: suspend User.() -> ProfileDetailsModel = {
    ProfileDetailsModel(name, profileImage)
}
