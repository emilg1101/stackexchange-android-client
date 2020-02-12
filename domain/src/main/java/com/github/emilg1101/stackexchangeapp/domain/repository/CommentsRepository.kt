package com.github.emilg1101.stackexchangeapp.domain.repository

import com.github.emilg1101.stackexchangeapp.domain.entity.Comment
import kotlinx.coroutines.flow.Flow

interface CommentsRepository {

    suspend fun getComments(postId: Int, page: Long): Flow<List<Comment>>
}
