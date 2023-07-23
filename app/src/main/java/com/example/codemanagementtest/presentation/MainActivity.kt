package com.example.codemanagementtest.presentation


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.codemanagementtest.presentation.navigation.AppBottomNavigation
import com.example.codemanagementtest.presentation.navigation.NavigateScreens
import com.example.codemanagementtest.presentation.navigation.Screen
import com.example.codemanagementtest.ui.theme.CodeManagementTestTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : androidx.activity.ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CodeManagementTestTheme {
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = MaterialTheme.colors.isLight

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = useDarkIcons
                    )
                }

                val navController = rememberNavController()
                val showBottomBar = rememberSaveable { mutableStateOf(true) }

                MainScreen(
                    showBottomBar = showBottomBar,
                    navController = navController
                )
            }
        }

    }
}

@Composable
fun MainScreen(
    showBottomBar: MutableState<Boolean>,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        bottomBar = {
            showBottomBar.value =
                when (currentDestination?.route?.substringBeforeLast("/")) {
                    Screen.MovieDetail.route -> false
                    else -> true
                }
            AnimatedVisibility(
                visible = showBottomBar.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
            ) {
                AppBottomNavigation(
                    navController = navController,
                    currentDestination = currentDestination
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        NavigateScreens(navController = navController, paddingValues = it)
    }
}

