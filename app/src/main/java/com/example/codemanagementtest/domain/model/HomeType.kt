package com.example.codemanagementtest.domain.model

import com.example.codemanagementtest.data.remote.movie.Movie

sealed class HomeType {
    data class UpComming(val upcomming: List<Movie>) : HomeType()
    data class Popular(val popular: List<Movie>) : HomeType()
}