package com.example.codemanagementtest.presentation.detail

import com.example.codemanagementtest.data.local.entities.MovieEntity

sealed class MoviesEvent {
    data class FavouriteMovie(val movie: MovieEntity) : MoviesEvent()
    data class DeleteMovie(val movie: MovieEntity): MoviesEvent()
    data class IsFavourited(val id: Int): MoviesEvent()
}