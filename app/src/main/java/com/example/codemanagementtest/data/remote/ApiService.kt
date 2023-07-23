package com.example.codemanagementtest.data.remote

import com.example.codemanagementtest.common.Constants
import com.example.codemanagementtest.data.remote.genre.GenreResponse
import com.example.codemanagementtest.data.remote.movie.MovieResponse
import com.example.codemanagementtest.data.remote.movie_detail.MovieDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET(Constants.GENRE_MOVIE)
    suspend fun getGenres(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = "tr-TR"
    ): GenreResponse

    @GET(Constants.POPULAR)
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String = Constants.API_KEY,
        @Query("language") language: String = "tr-TR",
        @Query("page") page: Int
    ): MovieResponse

    @GET(Constants.UPCOMMING)
    suspend fun getUpCommingMovies(
        @Query("api_key") api_key: String = Constants.API_KEY,
        @Query("language") language: String = "tr-TR",
        @Query("page") page: Int
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = "tr-TR",
        @Query("append_to_response") appendToResponse: String = "credits,similar"
    ): MovieDetailDto
}