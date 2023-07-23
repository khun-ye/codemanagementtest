package com.example.codemanagementtest.domain.repository

import com.example.codemanagementtest.common.Resource
import com.example.codemanagementtest.data.remote.genre.GenreResponse
import com.example.codemanagementtest.data.remote.movie.MovieResponse
import com.example.codemanagementtest.domain.model.HomeType
import com.example.codemanagementtest.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow


interface NetworkRepository {

    suspend fun getPopularMovies(page: Int): MovieResponse

    suspend fun getGenres(): GenreResponse

    suspend fun getUpCommingMovies(page: Int): MovieResponse

    fun getMovieById(id: Int): Flow<Resource<MovieDetail>>

    fun getHomeMovies(): Flow<Resource<List<HomeType>>>
}