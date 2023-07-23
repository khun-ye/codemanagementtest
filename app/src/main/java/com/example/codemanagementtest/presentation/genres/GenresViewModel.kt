package com.example.codemanagementtest.presentation.genres

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codemanagementtest.data.remote.genre.Genre
import com.example.codemanagementtest.domain.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _stateGenres = mutableStateOf<List<Genre>>(emptyList())
    val stateGenres: State<List<Genre>> get() = _stateGenres

    init {
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            _stateGenres.value = networkRepository.getGenres().genres
        }
    }

}