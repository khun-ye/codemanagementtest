package com.example.codemanagementtest.data.repository


import com.example.codemanagementtest.common.Resource
import com.example.codemanagementtest.data.remote.ApiService
import com.example.codemanagementtest.data.remote.genre.GenreResponse
import com.example.codemanagementtest.data.remote.movie.MovieResponse
import com.example.codemanagementtest.data.remote.movie_detail.toMovieDetail
import com.example.codemanagementtest.domain.model.HomeType
import com.example.codemanagementtest.domain.model.MovieDetail
import com.example.codemanagementtest.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val api: ApiService,
) : NetworkRepository {

    override suspend fun getPopularMovies(page: Int): MovieResponse {
        return api.getPopularMovies(page = page)
    }

    override suspend fun getGenres(): GenreResponse {
        return api.getGenres()
    }

    override suspend fun getUpCommingMovies(page: Int): MovieResponse {
        return api.getUpCommingMovies(page = page)
    }

    override fun getMovieById(id: Int): Flow<Resource<MovieDetail>> = flow {
        emit(Resource.Loading())
        try {
            val movie = api.getMovieDetail(movieId = id).toMovieDetail()
            emit(Resource.Success(movie))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage  ?: "Error"))
        }
    }


    override fun getHomeMovies(): Flow<Resource<List<HomeType>>> = flow {
        emit(Resource.Loading())
        try {
            val upcomming = api.getUpCommingMovies(page = 1).results
            val popular = api.getPopularMovies(page = 1).results

            val list = listOf(HomeType.Popular(popular), HomeType.UpComming(upcomming))

            emit(Resource.Success(list))
        }catch (e: Exception){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }
}




