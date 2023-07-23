package com.example.codemanagementtest.data.remote.movie

import com.example.codemanagementtest.data.remote.movie.Movie


data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int,
)
