package com.example.codemanagementtest.presentation.detail

import com.example.codemanagementtest.domain.model.MovieDetail

data class MovieDetailState(
    val movie: MovieDetail? = null,
    val isLoading: Boolean = false,
    val error: String = "",
    val isBookmarked: Boolean = false
)
