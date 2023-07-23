package com.example.codemanagementtest.presentation.detail.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.codemanagementtest.data.remote.genre.Genre
import com.example.codemanagementtest.presentation.navigation.Screen
import com.example.codemanagementtest.ui.theme.genreBgColor
import com.example.codemanagementtest.ui.theme.genreColor
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

@Composable
fun MovieGenres(
    modifier: Modifier = Modifier,
    genres: List<Genre>,
    navController: NavController
) {
    FlowRow(
        modifier = modifier,
        mainAxisAlignment = MainAxisAlignment.Center
    ) {
        genres.forEach { genre ->
            TextButton(
                onClick = {
                    navController
                        .navigate(Screen.MovieWithGenres.route + "/${genre.id}/${genre.name}")
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = genreBgColor,
                    contentColor = genreColor
                ), modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
            ) {
                Text(text = genre.name, fontSize = 12.sp)
            }
        }
    }
}