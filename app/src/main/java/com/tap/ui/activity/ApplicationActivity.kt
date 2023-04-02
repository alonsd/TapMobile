package com.tap.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.tap.core.extensions.setTapContent
import com.ramcosta.composedestinations.DestinationsNavHost
import com.tap.ui.screens.dashboard.screen.NavGraphs
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialNavigationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class ApplicationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTapContent {
            DestinationsNavHost(navGraph = NavGraphs.root)
        }
    }
}