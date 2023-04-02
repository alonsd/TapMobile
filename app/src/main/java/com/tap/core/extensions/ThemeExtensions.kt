package com.tap.core.extensions

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.tap.ui.theme.TapTheme

fun ComponentActivity.setTapContent(content: @Composable () -> Unit){
    setContent {
        TapTheme {
            content()
        }
    }
}