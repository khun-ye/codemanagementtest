package com.example.codemanagementtest.presentation.navigation

sealed class Screen(val route: String){
    object Home: Screen(route = "home")
    object Popular: Screen(route = "popular")
    object UpComming: Screen(route = "upcomming")
    object MovieDetail: Screen(route = "movie_detail")
    object Genres: Screen(route = "genres")
    object WatchList: Screen(route = "watchlist")
    object MovieWithGenres: Screen(route = "movie_with_genres")
}
