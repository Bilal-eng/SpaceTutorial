package com.bilal.spacetutorial

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bilal.spacetutorial.entity.RocketLaunch
import kotlinx.coroutines.launch

data class RocketLaunchScreenState(
    val isLoading: Boolean = false,
    val launches: List<RocketLaunch> = emptyList(),
)

class RocketLaunchViewModel(
    private val sdk: SpaceSDK,
) : ViewModel() {

    private val _state = mutableStateOf(RocketLaunchScreenState())
    val state: State<RocketLaunchScreenState> = _state

    init {
        loadLaunches()
    }

    fun loadLaunches() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, launches = emptyList())
            try {
                val launches = sdk.getLaunches(forceReload = true)
                _state.value = _state.value.copy(isLoading = false, launches = launches)
            } catch (_: Exception) {
                _state.value = _state.value.copy(isLoading = false, launches = emptyList())
            }
        }
    }
}