package com.tap.ui.screens.dashboard.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.tap.ui.screens.dashboard.viewmodel.DashboardViewModel

@RootNavGraph(start = true)
@ExperimentalComposeUiApi
@Destination
@Composable
fun DashboardScreen(
    navigator: DestinationsNavigator,
    viewModel : DashboardViewModel = hiltViewModel()
) {

    Text(text = "Tap", fontSize = 36.sp)
}