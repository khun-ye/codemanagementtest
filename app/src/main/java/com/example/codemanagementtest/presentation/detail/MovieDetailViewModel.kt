package com.example.codemanagementtest.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codemanagementtest.common.Resource
import com.example.codemanagementtest.domain.repository.LocalRepository
import com.example.codemanagementtest.domain.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val localRepository: LocalRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> get() = _state

    init {
        savedStateHandle.get<String>("movie_id")?.let {
            getMovieById(it.toInt())
        }
    }

    private val _isFavourite = mutableStateOf(false)
    val isFavourite: State<Boolean> get() = _isFavourite


    fun onEvent(event: MoviesEvent) {
        when (event) {
            is MoviesEvent.FavouriteMovie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    localRepository.insert(event.movie)
                    withContext(Dispatchers.Main){
                        _isFavourite.value = true
                    }
                }
            }
            is MoviesEvent.DeleteMovie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    localRepository.delete(event.movie)
                    withContext(Dispatchers.Main){
                        _isFavourite.value = false
                    }
                }
            }
            is MoviesEvent.IsFavourited ->{
                viewModelScope.launch(Dispatchers.IO) {
                    val movie = localRepository.getMovieById(id = event.id)
                    withContext(Dispatchers.Main) {
                        _isFavourite.value = movie != null
                    }
                }
            }
        }
    }

    private fun getMovieById(id: Int) {
        viewModelScope.launch {
            networkRepository.getMovieById(id = id)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = MovieDetailState(movie = result.data)
                        }
                        is Resource.Loading -> {
                            _state.value = _state.value.copy(isLoading = true)
                        }
                        is Resource.Error -> {
                            _state.value = _state.value.copy(error = result.message ?: "Error!")
                        }
                    }
                }
        }
    }
}
