package com.github.emilg1101.stackexchangeapp.data.repository

import com.github.emilg1101.stackexchangeapp.data.api.StackExchangeService
import com.github.emilg1101.stackexchangeapp.data.api.entity.CommentsResultEntityMapper
import com.github.emilg1101.stackexchangeapp.domain.entity.Comment
import com.github.emilg1101.stackexchangeapp.domain.repository.CommentsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CommentsRepositoryImpl internal constructor(
    private val stackExchangeService: StackExchangeService
) : CommentsRepository {

    override fun getComments(postId: Int, page: Long): Flow<List<Comment>> {
        return flow { emit(stackExchangeService.getComments(postId, page.toInt())) }
            .map(CommentsResultEntityMapper)
    }
}
