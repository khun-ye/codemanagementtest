package com.example.codemanagementtest.presentation.home

import com.example.codemanagementtest.domain.model.HomeType

data class HomeState(
    val homeList : List<HomeType> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)