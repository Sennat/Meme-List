package com.project.memelist.model

// "Sealed Class" <- Group of related objects
sealed class UIState {
    object Loading: UIState()
    class Error(val error: Exception): UIState()
    class Success(val response: MemeResponse) : UIState()
}

// "Enum" <- Group of related constants
enum class StateUI {
    LOADING,
    ERROR,
    SUCCESS
}