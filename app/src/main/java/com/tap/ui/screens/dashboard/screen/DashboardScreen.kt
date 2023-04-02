package com.tap.ui.screens.dashboard.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.tap.R
import com.tap.core.ui.SearchBar
import com.tap.ui.screens.dashboard.list_item.DashboardListItem
import com.tap.ui.screens.dashboard.viewmodel.DashboardViewModel

@RootNavGraph(start = true)
@ExperimentalComposeUiApi
@Destination
@Composable
fun DashboardScreen(
    navigator: DestinationsNavigator,
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    val focusRequester = remember { FocusRequester() }

    Column(Modifier.fillMaxSize()) {
        SearchBar(
            searchState = uiState.searchState,
            onSearchQueryChanged = { newQuery ->
                viewModel.submitEvent(DashboardViewModel.UiEvent.OnQueryChanged(newQuery))
            },
            onSearchFocusChange = { focused ->
                viewModel.submitEvent(DashboardViewModel.UiEvent.OnSearchFocusChange(focused))
            },
            onClearQueryClicked = {
                viewModel.submitEvent(DashboardViewModel.UiEvent.OnClearQueryClicked)
            },
            onBack = { },
            focusRequester = focusRequester
        )
        when (uiState.state) {
            DashboardViewModel.UiState.State.Data -> {
                LazyColumn {
                    items(uiState.youtubeListItems) { model ->
                        DashboardListItem(model = model)
                    }
                }
            }
            DashboardViewModel.UiState.State.Error -> {
                Text(text = stringResource(R.string.general_server_error), fontSize = 20.sp)
            }
            DashboardViewModel.UiState.State.Initial -> Unit
        }
    }

}