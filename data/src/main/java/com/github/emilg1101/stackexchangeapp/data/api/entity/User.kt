package com.github.emilg1101.stackexchangeapp.data.api.entity

import com.github.emilg1101.stackexchangeapp.domain.entity.User
import com.github.emilg1101.stackexchangeapp.domain.exceptions.UserNotFoundException
import com.google.gson.annotations.SerializedName

typealias ApiUser = com.github.emilg1101.stackexchangeapp.data.api.entity.User

data class User(
    @field:SerializedName("about_me")
    val aboutMe: String?,
    @field:SerializedName("accept_rate")
    val acceptRate: Int?,
    @field:SerializedName("account_id")
    val accountId: Int?,
    @field:SerializedName("age")
    val age: Int?,
    @field:SerializedName("answer_count")
    val answerCount: Int?,
    @field:SerializedName("badge_counts")
    val badgeCounts: BadgeCounts?,
    @field:SerializedName("creation_date")
    val creationDate: Long?,
    @field:SerializedName("display_name")
    val displayName: String?,
    @field:SerializedName("down_vote_count")
    val downVoteCount: Int?,
    @field:SerializedName("is_employee")
    val isEmployee: Boolean?,
    @field:SerializedName("last_access_date")
    val lastAccessDate: Long?,
    @field:SerializedName("last_modified_date")
    val lastModifiedDate: Long?,
    @field:SerializedName("link")
    val link: String?,
    @field:SerializedName("location")
    val location: String?,
    @field:SerializedName("profile_image")
    val profileImage: String?,
    @field:SerializedName("question_count")
    val questionCount: Int?,
    @field:SerializedName("reputation")
    val reputation: Int?,
    @field:SerializedName("reputation_change_day")
    val reputationChangeDay: Int?,
    @field:SerializedName("reputation_change_month")
    val reputationChangeMonth: Int?,
    @field:SerializedName("reputation_change_quarter")
    val reputationChangeQuarter: Int?,
    @field:SerializedName("reputation_change_week")
    val reputationChangeWeek: Int?,
    @field:SerializedName("reputation_change_year")
    val reputationChangeYear: Int?,
    @field:SerializedName("timed_penalty_date")
    val timedPenaltyDate: Long?,
    @field:SerializedName("up_vote_count")
    val upVoteCount: Int?,
    @field:SerializedName("user_id")
    val userId: Int?,
    @field:SerializedName("user_type")
    val userType: String?,
    @field:SerializedName("view_count")
    val viewCount: Int?,
    @field:SerializedName("website_url")
    val websiteUrl: String?
)

val UserEntityMapper: ApiUser.() -> User = {
    User(userId ?: 0, profileImage ?: "", displayName ?: "")
}

val UserResultEntityMapper: suspend ResponseWrapper<ApiUser>.() -> User = {
    items.map(UserEntityMapper).firstOrNull() ?: throw UserNotFoundException()
}
