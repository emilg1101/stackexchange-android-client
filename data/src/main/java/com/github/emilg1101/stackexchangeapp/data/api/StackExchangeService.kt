package com.github.emilg1101.stackexchangeapp.data.api

import com.github.emilg1101.stackexchangeapp.data.api.entity.Answer
import com.github.emilg1101.stackexchangeapp.data.api.entity.Comment
import com.github.emilg1101.stackexchangeapp.data.api.entity.Question
import com.github.emilg1101.stackexchangeapp.data.api.entity.ResponseWrapper
import com.github.emilg1101.stackexchangeapp.data.api.entity.Tag
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StackExchangeService {

    companion object {
        private const val ACCESS_TOKEN = "key=8EnhMIUq0ISUrI1tg7VDmA(("
    }

    @GET("2.2/questions?$ACCESS_TOKEN")
    fun getQuestions(
        @Query("page") page: Int,
        @Query("tagged") tags: String? = "",
        @Query("pagesize") pageSize: Int = 10,
        @Query("order") order: String = "desc",
        @Query("sort") sort: String? = "hot",
        @Query("site") site: String = "stackoverflow"
    ): Flow<ResponseWrapper<Question>>

    @GET("2.2/tags?$ACCESS_TOKEN")
    fun getTags(
        @Query("pagesize") pageSize: Int = 50,
        @Query("sort") sort: String = "popular",
        @Query("order") order: String = "desc",
        @Query("site") site: String = "stackoverflow"
    ): Flow<ResponseWrapper<Tag>>

    @GET("2.2/questions/{questionId}?filter=!LVBj2(Le6NjBDBn2194EGT&$ACCESS_TOKEN")
    fun getQuestionById(
        @Path("questionId") questionId: Int,
        @Query("site") site: String = "stackoverflow"
    ): Flow<ResponseWrapper<Question>>

    @GET("2.2/questions/{questionId}/answers?filter=!3ykawN*7Hu(G7tpx*&$ACCESS_TOKEN")
    fun getQuestionAnswers(
        @Path("questionId") questionId: Int,
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int = 10,
        @Query("site") site: String = "stackoverflow"
    ): Flow<ResponseWrapper<Answer>>

    @GET("2.2/posts/{postId}/comments?filter=!-*jbN.x(pn3w&$ACCESS_TOKEN")
    fun getComments(
        @Path("postId") postId: Int,
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int = 10,
        @Query("site") site: String = "stackoverflow"
    ): Flow<ResponseWrapper<Comment>>

    @GET("2.2/answers/{answerId}?filter=!3ykawN*7Hu(G7tpx*&$ACCESS_TOKEN")
    fun getAnswerById(
        @Path("answerId") answerId: Int,
        @Query("site") site: String = "stackoverflow"
    ): Flow<ResponseWrapper<Answer>>
}
