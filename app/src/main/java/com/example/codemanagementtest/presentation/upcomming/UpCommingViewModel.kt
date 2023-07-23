package com.example.codemanagementtest.presentation.upcomming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.codemanagementtest.data.paging.MoviePagingSource
import com.example.codemanagementtest.domain.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpCommingViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {
    val upcommingMovies = Pager(
        config = PagingConfig(pageSize = 20)
    ) {
        MoviePagingSource(
            networkRepository = networkRepository,
            source = MoviePagingSource.Source.UpComming
        )
    }.flow.cachedIn(viewModelScope)

}