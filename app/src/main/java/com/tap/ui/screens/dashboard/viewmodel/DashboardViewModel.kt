package com.tap.ui.screens.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.tap.core.ui.SearchState
import com.tap.data.repository.DashboardRepository
import com.tap.model.YouTubeListItemModel
import com.tap.model.YoutubeSearchResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiAction = MutableSharedFlow<UiAction>()
    private val uiAction = _uiAction.asSharedFlow()

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    private val uiEvent = _uiEvent.asSharedFlow()

    init {
        onQueryChanged(_uiState.value.searchState.query)
        observeUiEvents()
        observeUiAction()
    }

    private fun observeUiAction() = viewModelScope.launch {
        uiAction.collect { action ->
            when (action) {
                is UiAction.ListItemClicked -> {

                }
            }
        }
    }

    private fun observeUiEvents() = viewModelScope.launch {
        uiEvent.collect { event ->
            when (event) {
                is UiEvent.OnQueryChanged -> {
                    if (event.newQuery == "") {
                        clearListToDefaultState()
                        return@collect
                    }
                    submitUiState(
                        _uiState.value.copy(
                            searchState = _uiState.value.searchState.copy(query = event.newQuery, searching = true),
                        )
                    )
                    onQueryChanged(event.newQuery)
                }
                is UiEvent.OnSearchFocusChange -> {
                    submitUiState(
                        _uiState.value.copy(
                            searchState = _uiState.value.searchState.copy(focused = event.focused)
                        )
                    )
                }
                UiEvent.OnClearQueryClicked -> {
                    clearListToDefaultState()
                }
            }
        }
    }

    private fun onQueryChanged(newQuery: String) = viewModelScope.launch {
        when (val result = dashboardRepository.getSearchTermResults(newQuery)) {
            is NetworkResponse.Success -> {
                val youtubeListItems = parseResultsModelToUiModel(result)
                submitUiState(
                    _uiState.value.copy(
                        state = UiState.State.Data,
                        searchState = _uiState.value.searchState.copy(searching = false),
                        youtubeListItems = youtubeListItems
                    )
                )
            }
            is NetworkResponse.Error -> {
                submitUiState(
                    _uiState.value.copy(
                        state = UiState.State.Error,
                        searchState = _uiState.value.searchState.copy(searching = false)
                    )
                )
            }
            else -> Unit
        }
    }

    private fun parseResultsModelToUiModel(result: NetworkResponse.Success<YoutubeSearchResultModel>): MutableList<YouTubeListItemModel> {
        val youtubeListItemModels = mutableListOf<YouTubeListItemModel>()
        result.body.items.forEach { item ->
            /*
            Some values where missing, therefore there are some default hard coded values.
            Couldn't investigate anymore due to exceeding the API daily limit.
             */
            val title = item.snippet.title ?: "title"
            val thumbnail = item.snippet.thumbnails.default.url ?: ""
            val videoId = item.id.videoId ?: "1"
            youtubeListItemModels.add(
                YouTubeListItemModel(title, thumbnail, videoId)
            )
        }
        return youtubeListItemModels
    }

    private fun clearListToDefaultState() = viewModelScope.launch {
        submitUiState(
            _uiState.value.copy(
                state = UiState.State.Data,
                youtubeListItems = emptyList(),
                searchState = _uiState.value.searchState.copy(query = "", searching = false)
            )
        )
    }

    /**
     * Actions would be used for navigation after the user clicked on a list item
     */
    private fun submitAction(uiAction: UiAction) = viewModelScope.launch {
        _uiAction.emit(uiAction)
    }

    private fun submitUiState(uiState: UiState) {
        _uiState.update { uiState }
    }

    fun submitEvent(uiEvent: UiEvent) = viewModelScope.launch {
        _uiEvent.emit(uiEvent)
    }

    sealed interface UiEvent {
        data class OnQueryChanged(val newQuery: String) : UiEvent
        data class OnSearchFocusChange(val focused: Boolean) : UiEvent
        object OnClearQueryClicked : UiEvent
    }

    data class UiState(
        val state: State = State.Initial,
        val searchState: SearchState = SearchState(
            "Apple", //Default search query for fast loading of the UI 
            focused = false,
            searching = true
        ),
        val youtubeListItems: List<YouTubeListItemModel> = emptyList()
    ) {
        enum class State {
            Data,
            Error,
            Initial
        }
    }

    sealed interface UiAction {
        data class ListItemClicked(val videoId: String) : UiAction
    }
}