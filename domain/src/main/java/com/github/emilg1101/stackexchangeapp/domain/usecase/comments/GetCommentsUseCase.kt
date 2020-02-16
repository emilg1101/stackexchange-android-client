package com.github.emilg1101.stackexchangeapp.domain.usecase.comments

import com.github.emilg1101.stackexchangeapp.domain.FlowUseCase
import com.github.emilg1101.stackexchangeapp.domain.entity.Comment
import com.github.emilg1101.stackexchangeapp.domain.repository.CommentsRepository
import kotlinx.coroutines.flow.Flow

class GetCommentsUseCase(
    private val commentsRepository: CommentsRepository
) : FlowUseCase<List<Comment>, GetCommentsUseCase.Params>() {

    override suspend fun run(params: Params): Flow<List<Comment>> =
        commentsRepository.getComments(params.postId, params.page)

    data class Params(val postId: Int, val page: Long = 1)
}
