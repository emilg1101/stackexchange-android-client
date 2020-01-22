package com.github.emilg1101.stackexchangeapp.data.api

import com.github.emilg1101.stackexchangeapp.data.api.entity.QuestionsResultEntity
import com.github.emilg1101.stackexchangeapp.data.api.entity.TagsResultEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StackExchangeService {

    @GET("2.2/questions")
    suspend fun getQuestions(
        @Query("page") page: Int,
        @Query("tagged") tags: String = "",
        @Query("pagesize") pageSize: Int = 10,
        @Query("order") order: String = "desc",
        @Query("sort") sort: String? = "hot",
        @Query("site") site: String = "stackoverflow"
    ): QuestionsResultEntity

    @GET("2.2/tags")
    suspend fun getTags(
        @Query("pagesize") pageSize: Int = 10,
        @Query("sort") sort: String = "popular",
        @Query("order") order: String = "desc",
        @Query("site") site: String = "stackoverflow"
    ): TagsResultEntity

    @GET("2.2/questions/{questionId}?filter=!9Z(-wwK*8")
    suspend fun getQuestionById(
        @Path("questionId") questionId: Int,
        @Query("site") site: String = "stackoverflow"
    ): QuestionsResultEntity
}
