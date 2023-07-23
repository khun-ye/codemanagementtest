package com.example.codemanagementtest.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.codemanagementtest.presentation.detail.MovieDetailScreen
import com.example.codemanagementtest.presentation.genres.GenresScreen
import com.example.codemanagementtest.presentation.home.HomeScreen
import com.example.codemanagementtest.presentation.popular.PopularScreen
import com.example.codemanagementtest.presentation.upcomming.UpCommingMoviesScreen
import com.example.codemanagementtest.presentation.watch_list.WatchListScreen

@Composable
fun NavigateScreens(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController, startDestination = Screen.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Popular.route) {
            PopularScreen(navController = navController)
        }
        composable(Screen.UpComming.route) {
            UpCommingMoviesScreen(navController = navController)
        }
        composable(Screen.MovieDetail.route + "/{movie_id}") {
            MovieDetailScreen(navController = navController)
        }
        composable(Screen.Genres.route) {
            GenresScreen(navController = navController)
        }
        composable(Screen.WatchList.route) {
            WatchListScreen()
        }

    }
}